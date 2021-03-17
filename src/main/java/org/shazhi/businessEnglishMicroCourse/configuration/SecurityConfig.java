package org.shazhi.businessEnglishMicroCourse.configuration;

import org.shazhi.businessEnglishMicroCourse.entity.UserEntity;
import org.shazhi.businessEnglishMicroCourse.repository.UserRepository;
import org.shazhi.businessEnglishMicroCourse.util.IdUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;


import javax.transaction.Transactional;
import java.util.ArrayList;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
//                    .antMatchers("/register").permitAll()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler((request, response, authentication) -> {
                    response.getWriter().write("success");
                    response.getWriter().flush();
                    response.getWriter().close();
                })
                .failureHandler((request, response, exception) -> {
                    response.getWriter().write("fail");
                    response.getWriter().flush();
                    response.getWriter().close();
                })
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Autowired
            UserRepository userRepository;

            @Override
            @Transactional
            public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
                UserEntity userEntity = userRepository.getUserEntityByUserName(userName);
                try {
                    ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    userEntity.getUros().forEach(uro -> authorities.add(new SimpleGrantedAuthority(uro.getRole().getRoleName())));
                    return new IdUser(userName, userEntity.getPassword(), userEntity.getUserEnable(), true, true, true, authorities).setUserInfo(userEntity);
                } catch (Exception e) {
                    return new IdUser(userName, null, false, false, false, false, null);
                }
            }
        };
    }

}
