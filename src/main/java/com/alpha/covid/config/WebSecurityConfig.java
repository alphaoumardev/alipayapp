package com.alpha.covid.config;

import com.alpha.covid.services.CustomService;
import com.alpha.covid.structor.filter.JwtAuthoraztionFilter;
import com.alpha.covid.structor.filter.SecurityLoginFilter;
import com.alpha.covid.structor.handler.SystemAccessDeniedHandler;
import com.alpha.covid.structor.handler.SystemAuthEntryPoint;
import com.alpha.covid.structor.handler.SystemAuthFailureHandler;
import com.alpha.covid.structor.handler.SystemAuthSuccessHandler;
import com.alpha.covid.structor.security.SysUserDetailsServices;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
    private final SysUserDetailsServices sysUserDetailsServices;
    private final SystemAuthFailureHandler authFailureHandler;
    private final SystemAuthSuccessHandler authSuccessHandler;
    private final CustomService customService;
    private final SystemAccessDeniedHandler accessDeniedHandler;
    private final SystemAuthEntryPoint authEntryPoint;
    private final JwtAuthoraztionFilter jwtAuthoraztionFilter;

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityLoginFilter securityLoginFilter() throws Exception
    {
        SecurityLoginFilter securityLoginFilter = new SecurityLoginFilter(customService);
        securityLoginFilter.setAuthenticationFailureHandler(authFailureHandler);
        securityLoginFilter.setAuthenticationSuccessHandler(authSuccessHandler);
        securityLoginFilter.setAuthenticationManager(authenticationManager());
        return securityLoginFilter;
    }

    @Override
    public void configure( AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(sysUserDetailsServices).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure( WebSecurity webSecurity)
    {
        webSecurity.ignoring().antMatchers(HttpMethod.GET,
                "/favicon.ico",
                "/**/*.png",
                "/**/*.ttf",
                "/*.html",
                "/**/*.css",
                "/**/*.js");
    }
//    @Override
//    protected void configure(HttpSecurity http) throws Exception
//    {
//        http.csrf().disable();
//    }

    @Override
    protected void configure( HttpSecurity http) throws Exception
    {
        http.csrf().disable();
        http.authorizeRequests().requestMatchers(CorsUtils::isPreFlightRequest).permitAll();

        http.csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().headers().cacheControl();

        http.headers().frameOptions().disable();

        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/user/avatar").permitAll()
                .antMatchers(HttpMethod.GET, "/user/captchaImage/*").permitAll()
                .antMatchers(HttpMethod.GET,"/swagger-ui.html").permitAll()
                .antMatchers(HttpMethod.GET,"/doc.html/**").permitAll()
                .antMatchers(HttpMethod.POST,"/druid/**").permitAll() // 放行druid
                .antMatchers(HttpMethod.GET,"/druid/**").permitAll() // 放行druid
                .antMatchers(HttpMethod.GET,"/webjars/**").permitAll()
                .antMatchers(HttpMethod.GET,"/v2/**").permitAll()
                .antMatchers(HttpMethod.GET,"/swagger-resources/**").permitAll()
                .anyRequest().authenticated();
//        http.authorizeRequests()
//                .antMatchers("/**").permitAll()
//                .anyRequest().authenticated();

        http.addFilterAt(jwtAuthoraztionFilter, BasicAuthenticationFilter.class);

        http.exceptionHandling()
                .authenticationEntryPoint(authEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);

        http.addFilterAt(securityLoginFilter(), UsernamePasswordAuthenticationFilter.class);

        http.formLogin().loginProcessingUrl("/user/login");
    }
}
