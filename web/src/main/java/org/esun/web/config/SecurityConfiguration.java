package org.esun.web.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.esun.web.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@Log4j2
public class SecurityConfiguration {

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // WebSecurityCustomizer是一个类似于Consumer<WebSecurity>的接口，函数接受一个WebSecurity类型的变量，无返回值
        // 此处使用lambda实现WebSecurityCustomizer接口，web变量的类型WebSecurity，箭头后面可以对其进行操作
        // 使用requestMatchers()代替antMatchers()
        return (web) -> {
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{

        http.authorizeHttpRequests(registry ->{
            //定義那些URL 需要被保護、那些不需要被保護
            //做登入授權 把網頁保護起來
            //正向表列
            registry.requestMatchers("/login","register").permitAll()
                    .requestMatchers("/web/**").authenticated()
                    .anyRequest().permitAll();
            //負向表列: /js /css 靜態是permitall
            //其他是anyrequest
            //Error 403 沒有該資源的權限
        });




        http.formLogin(httpSecurityFormLoginConfigurer -> {
            httpSecurityFormLoginConfigurer.loginPage("/login")
                    .defaultSuccessUrl("/web/home")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .failureHandler((request, response, exception) -> {
                        log.error("密碼錯誤");
                        response.sendRedirect("/login?error=failed");
                    });
        });

        http.exceptionHandling(configurer ->{
            configurer.authenticationEntryPoint((request, response, authException) -> {
                log.error("未登入: "+ authException.getMessage());
                response.sendRedirect("/login?error=unauth");
            });
        });

        http.logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer
                .logoutUrl("/logout")
                .logoutSuccessHandler((request, response, authentication) -> {
                    log.info("登出成功");
                    response.sendRedirect("/login?logout");
                })
                .deleteCookies("JESSIONID")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .permitAll());

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsServiceImpl userDetailsService() {
        UserDetailsServiceImpl userDetailsService = new UserDetailsServiceImpl();
        return userDetailsService;
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        return daoAuthenticationProvider;
    }
}
