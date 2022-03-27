package io.srv.userservice.security;

import io.srv.userservice.filter.CustomAuthorizationFilter;
import io.srv.userservice.filter.CustomemAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers( "/api/account/v1/login/**", "/api/account/v1/user/**" ).permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/account/v1/user/getAll")
                .hasAnyAuthority("ADMIN", "MANAGER");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/account/v1/user/getOne/**")
                .hasAnyAuthority("ADMIN", "MANAGER", "USER");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/account/v1/user/role/**")
                .hasAnyAuthority("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/account/v1/user/assignRole")
                .hasAnyAuthority("ADMIN", "MANAGER");
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(new CustomemAuthenticationFilter(authenticationManagerBean()));
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
