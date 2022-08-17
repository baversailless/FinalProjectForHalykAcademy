package kz.halykacademy.bookstore.security;

import kz.halykacademy.bookstore.security.MyAuthenticationEntryPoint;
import kz.halykacademy.bookstore.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig  {

    private final UserDetailsServiceImpl userDetailsService;
    private final MyAuthenticationEntryPoint myAuthenticationEntryPoint;

    @Autowired
    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService,
                             MyAuthenticationEntryPoint myAuthenticationEntryPoint){
        this.userDetailsService = userDetailsService;
        this.myAuthenticationEntryPoint = myAuthenticationEntryPoint;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/books/**").hasAnyAuthority("ADMIN", "USER")
                .antMatchers("/api/books/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/authors/**").hasAnyAuthority("ADMIN", "USER")
                .antMatchers("/api/authors/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/publishers/**").hasAnyAuthority("ADMIN", "USER")
                .antMatchers("/api/publishers/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/genres/**").hasAnyAuthority("ADMIN", "USER")
                .antMatchers("/api/genres/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/users/**").hasAnyAuthority("ADMIN", "USER")
                .antMatchers("/api/genres/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/users/**").hasAnyAuthority("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/api/orders/**").hasAnyAuthority("ADMIN", "USER")
                .antMatchers(HttpMethod.PUT, "/api/orders/**").hasAnyAuthority("ADMIN", "USER")
                .antMatchers("/api/orders/**").hasAuthority("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .authenticationEntryPoint(myAuthenticationEntryPoint);
        http.authenticationProvider(authProvider());
        return http.build();

    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

}
