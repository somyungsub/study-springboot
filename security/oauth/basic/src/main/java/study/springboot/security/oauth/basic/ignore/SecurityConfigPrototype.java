package study.springboot.security.oauth.basic.ignore;


import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfigPrototype {
  @Bean
  SecurityFilterChain securityFilterChainPrototype(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.authorizeHttpRequests().anyRequest().authenticated();
    httpSecurity.formLogin();

    // 커스텀
    CustomSecurityConfigurer configurer = new CustomSecurityConfigurer();
    httpSecurity.apply(configurer.secure(true));

    return httpSecurity.build();
  }

  @Bean
  SecurityFilterChain securityFilterChainPrototype2(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.authorizeHttpRequests().anyRequest().authenticated();
    httpSecurity.formLogin();
    return httpSecurity.build();

  }
}
