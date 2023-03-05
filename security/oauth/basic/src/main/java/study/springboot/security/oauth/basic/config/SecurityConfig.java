package study.springboot.security.oauth.basic.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

//  @Override
//  public void init(SecurityBuilder builder) throws Exception {
//
//  }
//
//  @Override
//  public void configure(SecurityBuilder builder) throws Exception {
//
//  }

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.authorizeHttpRequests().anyRequest().authenticated();
    httpSecurity.formLogin();

    // 커스텀
    CustomSecurityConfigurer configurer = new CustomSecurityConfigurer();
    httpSecurity.apply(configurer.secure(true));

    return httpSecurity.build();
  }
}
