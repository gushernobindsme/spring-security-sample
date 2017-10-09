package com.inmotion.security.config;

import com.inmotion.security.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.templateresolver.TemplateResolver;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static String ROLE_USER = "USER";
    private static String ROLE_ADMIN = "ADMIN";

    @Autowired
    public TemplateResolver templateResolver;

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 静的リソースに対するアクセスはセキュリティ設定を無視する
        web.ignoring()
                .antMatchers("/*.html", "/*.css")
                .antMatchers("/bootstrap/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/user").hasAnyRole(ROLE_USER, ROLE_ADMIN)
                .antMatchers("/admin").hasRole(ROLE_ADMIN)
                .and()

                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/user")
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
                .and()

                .logout()
                .permitAll()
                .and()

                .csrf();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        UserDto support = supportUser();
        UserDto admin = adminUser();

        auth.inMemoryAuthentication()
                .withUser(support.getUsername()).password(support.getPassword()).roles(ROLE_USER)
                .and()
                .withUser(admin.getUsername()).password(admin.getPassword()).roles(ROLE_ADMIN);
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.addDialect(new SpringSecurityDialect());
        engine.setTemplateResolver(templateResolver);
        return engine;
    }

    @Bean
    @ConfigurationProperties("inmotion.admin")
    public UserDto adminUser() {
        return new UserDto();
    }

    @Bean
    @ConfigurationProperties("inmotion.user")
    public UserDto supportUser() {
        return new UserDto();
    }

}