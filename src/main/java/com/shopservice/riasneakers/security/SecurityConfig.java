package com.shopservice.riasneakers.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer
   {
    @Autowired
    private JwtFilter jwtFilter;



 @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
              //  .antMatchers("/api/admin/register").hasRole("ADMIN")
              //  .antMatchers(HttpMethod.POST,"/api/card").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/api/short").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/api/card/*").hasRole("USER")
                .antMatchers(HttpMethod.POST,"/api/photo").hasRole("USER")
                .antMatchers( "/api/item","/api/admin/register","/api/user/register","/api/photo/*","/api/short/*","/api/auth/", "/api/files/*").permitAll()
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedOrigins("https://vcard-v3-test.herokuapp.com/")
                .allowedHeaders("*")
                .allowedMethods("*");
    }

}
