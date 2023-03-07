package study.springboot.security.oauth.jwttutorial.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorityDto {
  private String authorityName;
}
