package study.springboot.security.oauth.jwttutorial.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import study.springboot.security.oauth.jwttutorial.jwt.JwtAccessDeniedHandler;
import study.springboot.security.oauth.jwttutorial.jwt.JwtAuthenticationEntryPoint;
import study.springboot.security.oauth.jwttutorial.jwt.TokenProvider;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  private final TokenProvider tokenProvider;
  private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
  private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

  public SecurityConfig(TokenProvider tokenProvider,
                        JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
                        JwtAccessDeniedHandler jwtAccessDeniedHandler) {
    this.tokenProvider = tokenProvider;
    this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web
      .ignoring()
      .antMatchers("/favicon.ico");
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http
      // 토큰사용하므로 csrf disable
      .csrf().disable()

      // 커스텀 설정한 예외처리 및 entrypoint 관련 설정 추가
      .exceptionHandling()
      .authenticationEntryPoint(jwtAuthenticationEntryPoint)
      .accessDeniedHandler(jwtAccessDeniedHandler)

      // 헤더
      .and()
      .headers()
      .frameOptions()
      .sameOrigin()

      // session 정책 -> 사용x
      .and()
      .sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

      // 허용 api 설정
      .and()
      .authorizeRequests()
      .antMatchers("/api/hello").permitAll()
      .antMatchers("/api/authenticate").permitAll()
      .antMatchers("/api/signup").permitAll()
      .anyRequest().authenticated()

      // 설정 적용추가
      .and()
      .apply(new JwtSecurityConfig(tokenProvider));
  }


}
