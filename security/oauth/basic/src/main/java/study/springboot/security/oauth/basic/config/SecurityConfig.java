package study.springboot.security.oauth.basic.config;


import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

  @Bean
  SecurityFilterChain defaultSecurityFilter(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.authorizeHttpRequests().anyRequest().authenticated();
    httpSecurity.formLogin();
    return httpSecurity.build();
  }
}
