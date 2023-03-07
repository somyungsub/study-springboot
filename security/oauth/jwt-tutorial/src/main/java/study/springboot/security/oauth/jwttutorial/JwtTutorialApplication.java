package study.springboot.security.oauth.jwttutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class JwtTutorialApplication {

  public static void main(String[] args) {
    SpringApplication.run(JwtTutorialApplication.class, args);
  }

}
