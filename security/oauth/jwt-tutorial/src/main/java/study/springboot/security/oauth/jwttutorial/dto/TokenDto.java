package study.springboot.security.oauth.jwttutorial.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenDto {
  private String token;
}
