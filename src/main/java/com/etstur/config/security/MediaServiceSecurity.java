package com.etstur.config.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class MediaServiceSecurity extends WebSecurityConfigurerAdapter {

    @Bean
    JwtTokenFilter getJwtTokenFilter(){
        return new JwtTokenFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/v3/api-docs/**",
                        "/swagger-ui/**",
                        "/user/token",
                        "/media/save").permitAll()
                .anyRequest().authenticated();
        http.addFilterBefore(getJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

    }
}
