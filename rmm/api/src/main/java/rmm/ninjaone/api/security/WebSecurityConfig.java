package rmm.ninjaone.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import rmm.ninjaone.api.support.exceptions.handlers.PipelineExceptionsHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    private SecurityProperties properties;

    @Autowired
    private TokenAuthenticationFilter tokenFilter;

    @Autowired
    private PipelineExceptionsHandler exceptionHandler;

    @Value("${api.users}")
    private String usersUrl;

    @Value("${springdoc.api-docs.path}")
    private String docsUrl;

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
                .antMatchers(properties.getLoginUrl(), properties.getRegisterUrl()).permitAll()
                .antMatchers(allPaths(docsUrl)).permitAll()
                .antMatchers(allPaths(usersUrl)).hasAuthority(Authorities.Admin)
                .anyRequest().hasAnyAuthority(Authorities.All)
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
