package com.ironhack.sporttracker.authservice.security;
import com.ironhack.sporttracker.authservice.service.UserEntityService;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserEntityService userEntityService;
    private final BCryptPasswordEncoder encoder;
    private final Environment environment;

    public WebSecurityConfiguration(UserEntityService userEntityService, BCryptPasswordEncoder encoder, Environment environment) {
        this.userEntityService = userEntityService;
        this.encoder = encoder;
        this.environment = environment;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
                .authorizeRequests()
                .antMatchers("/users")
                .permitAll()
                .antMatchers("/user_details")
                .permitAll()
                .antMatchers("/auth")
                .authenticated()
                .and()
                .addFilter(getAuthenticationFilter());
    }

    private AuthenticationFilter getAuthenticationFilter() throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(userEntityService, environment, authenticationManager());
        authenticationFilter.setFilterProcessesUrl(environment.getProperty("login.url.path"));
        return authenticationFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userEntityService).passwordEncoder(encoder);
    }
}
