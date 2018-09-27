package vn.hcmute.projectmanagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import vn.hcmute.projectmanagement.filter.JWTAuthenticationFilter;
import vn.hcmute.projectmanagement.filter.JWTLoginFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Qualifier("customUserDetailsServiceImpl")
    @Autowired
    private UserDetailsService customUserDetailsService;
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder (){return new BCryptPasswordEncoder();}
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    // verify user with roles each user has
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                // No need authentication.
                .antMatchers("/","/api/v1/register/**").permitAll() //
                .antMatchers("/api/v1/admin/**").hasRole("ADMIN")
                .antMatchers("/api/v1/users/**").hasAnyRole("USER","ADMIN")
//                .antMatchers("/api/v1/admin/**","/admin").hasAnyAuthority("READ_PRIVILEGE")
//                .antMatchers("/api/v1/users/**","/user").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
//                .antMatchers(HttpMethod.POST, "/login").permitAll() //
//                .antMatchers(HttpMethod.GET, "/login").permitAll() // For Test on Browser
                // Need authentication.
                .anyRequest().authenticated()
                //
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll();

                //
                // Add Filter 1 - JWTLoginFilter
                //
        http
                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                //
                // Add Filter 2 - JWTAuthenticationFilter
                //
                .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    // authenticate user
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(customUserDetailsService).passwordEncoder(bCryptPasswordEncoder());
        System.out.print(customUserDetailsService);
//          auth.inMemoryAuthentication()
//                .withUser("user").password("$2a$10$1u6kgOhEGj8kROJoBy9cme.vNLX09vFzH9mcvS2TDc13ozSGXC0W2").roles("USER")
//                .and()
//                .withUser("admin").password("$2a$10$1u6kgOhEGj8kROJoBy9cme.vNLX09vFzH9mcvS2TDc13ozSGXC0W2").roles("ADMIN");
    }
}
