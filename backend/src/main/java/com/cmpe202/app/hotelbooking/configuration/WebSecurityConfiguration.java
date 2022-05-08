package com.cmpe202.app.hotelbooking.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.cmpe202.app.hotelbooking.service.MyUserDetailsService;





@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private MyUserDetailsService userDetailsService;
    
    @Autowired
    DataSource datasource;


    
    

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
               
    	System.out.println("Inside JDBC auth");
        auth.jdbcAuthentication().dataSource(datasource).passwordEncoder(new BCryptPasswordEncoder())
        .usersByUsernameQuery(
                "SELECT user_email, password, active from user where user_email = ?")
            .authoritiesByUsernameQuery(
                "select u.user_email, r.role from user u,"
                + " user_role ur, role r where u.user_id=ur.user_id and"
                + "  ur.role_id = r.role_id and u.user_email=?"
            );
    	
    	auth
                    .userDetailsService(userDetailsService)
                    .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        String loginPage = "/login";
        String logoutPage = "/logout";

      /*  http.authorizeRequests()
        .antMatchers("/login**").permitAll()
        .antMatchers("/healthcheck**").permitAll()
        .antMatchers("/registeruser").permitAll()
        .antMatchers("/updateuser/**").permitAll()
        .antMatchers("/user/**").permitAll()
        .antMatchers("/user/**").permitAll()
        .and()
            .formLogin().permitAll()
            .and()
            .logout().permitAll(); */
        
        
        http.
        authorizeRequests()
        .antMatchers("/").permitAll()
        .antMatchers(loginPage).permitAll()
        .antMatchers("/registeruser").permitAll()
        .antMatchers("/updateuser/**").permitAll()
        .antMatchers("/user/**").permitAll()
        .and().csrf().disable()
        .formLogin().defaultSuccessUrl("/user/1")
        
        .permitAll()
        .and().logout()
        .logoutRequestMatcher(new AntPathRequestMatcher(logoutPage))
        .logoutSuccessUrl(loginPage).and().exceptionHandling();
        
        /*http.
                authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(loginPage).permitAll()
                .antMatchers("/registeruser").permitAll()
                .antMatchers("/updateuser/**").permitAll()
                .antMatchers("/user/**").permitAll()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                
                .and().csrf().disable()
                .formLogin().permitAll()
                .loginPage(loginPage)
                .loginPage("/")
                .failureUrl("/login?error=true")
                .defaultSuccessUrl("/admin/home")
                .usernameParameter("email")
                .passwordParameter("password")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher(logoutPage))
                .logoutSuccessUrl(loginPage).and().exceptionHandling();*/
    }
    


    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }

}
