package com.lizekai.wms.config;

import com.lizekai.wms.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 管理权限控制相关的配置
 */
@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//WebSecurityConfigurerAdapter是Security官方提供的类
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtAuthenticationTokenFilter filter;
    @Autowired
    AuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    AccessDeniedHandler accessDeniedHandler;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    //把官方的PasswordEncoder密码加密方式替换成BCryptPasswordEncoder
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //关闭csrf
                .csrf().disable()
                //不通过Session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 对于登录接口 允许匿名访问
                .antMatchers("/user/login").anonymous()
                //登出接口需要登录后使用
                .antMatchers("/user/logout").authenticated()
                .antMatchers("/user/userInfo").authenticated()
                //.antMatchers("/link/getAllLink").authenticated()

                //把文件上传的接口设置为需要登录才能访问
//                .antMatchers("/upload").authenticated()
                // 除上面外的所有请求全部需要认证可访问
                .anyRequest().authenticated();

        http.exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);


        http.logout().disable();

        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        //允许跨域
        http.cors();
    }

}
