package study.springboot.security.oauth.basic.ignore;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

public class CustomSecurityConfigurer extends AbstractHttpConfigurer<CustomSecurityConfigurer, HttpSecurity> {

  private boolean isSecure;
  @Override
  public void init(HttpSecurity builder) throws Exception {
    super.init(builder);
    // 빈, 클래스 등 초기화
    System.out.println("init method starting...");
  }

  @Override
  public void configure(HttpSecurity builder) throws Exception {
    super.configure(builder);

    // 인증, 인가 설정
    System.out.println("configure method starting...");
    if (isSecure) {
      System.out.println("https is required");
    } else {
      System.out.println("https is optional");
    }
  }

  public CustomSecurityConfigurer secure(boolean secure) {
    this.isSecure = secure;
    return this;
  }
}
