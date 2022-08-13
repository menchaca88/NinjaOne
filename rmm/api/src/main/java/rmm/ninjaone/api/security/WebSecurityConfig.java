package rmm.ninjaone.api.security;

import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.context.annotation.SessionScope;

import lombok.RequiredArgsConstructor;
import rmm.ninjaone.api.support.exceptions.handlers.PipelineExceptionsHandler;
import rmm.ninjaone.api.support.setup.ApiUrls;
import rmm.ninjaone.buildingblocks.application.support.UserContext;

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
    @SessionScope
    public UserContext userContext() {
        var context = new UserContext();

        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getDetails() instanceof UUID) {
            var userId = (UUID)authentication.getDetails();
            var authorities = authentication.getAuthorities()
                .stream()
                .map(a -> a.getAuthority())
                .toArray(String[]::new);

            context.setUserId(userId);
            context.setAuthorities(authorities);
        }

        return context;
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
                .antMatchers(allPaths(apiUrls.getInventory()), allPaths(apiUrls.getInvoices()), allPaths(apiUrls.getPayments()))
                    .hasAuthority(Authorities.User)
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
