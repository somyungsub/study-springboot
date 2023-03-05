package study.springboot.security.oauth.basic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(
  excludeFilters = {
    @ComponentScan.Filter(type = FilterType.REGEX, pattern = "study.springboot.security.oauth.basic.ignore.*")
  }
)
public class BasicApplication {

  public static void main(String[] args) {
    SpringApplication.run(BasicApplication.class, args);
  }

}
