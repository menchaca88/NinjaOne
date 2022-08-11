package rmm.ninjaone.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;

import lombok.RequiredArgsConstructor;
import rmm.ninjaone.api.support.exceptions.handlers.PipelineExceptionsHandler;
import rmm.ninjaone.api.support.setup.ApiUrls;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final TokenAuthenticationFilter tokenFilter;
    private final PipelineExceptionsHandler exceptionHandler;
    private final ApiUrls apiUrls;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        var provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userDetailsService);

        return new ProviderManager(provider);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilterAt(tokenFilter, AnonymousAuthenticationFilter.class)
            .addFilterBefore(exceptionHandler, LogoutFilter.class)
            .authorizeRequests()
                .antMatchers(apiUrls.getLogin(), apiUrls.getRegister())
                    .permitAll()
                .antMatchers(allPaths(apiUrls.getDocs()))
                    .permitAll()
                .antMatchers(allPaths(apiUrls.getUsers()), allPaths(apiUrls.getCatalog()))
                    .hasAuthority(Authorities.Admin)
                .anyRequest()
                    .hasAnyAuthority(Authorities.All)
            .and()
            .exceptionHandling()
                .authenticationEntryPoint(exceptionHandler)
            .and()
            .cors().and()
            .csrf().disable()
            .formLogin().disable()
            .httpBasic().disable()
            .logout().disable();
        
        return http.build();
    }

    private String allPaths(String path) {
        return String.format("%s**", path);
    }
}
