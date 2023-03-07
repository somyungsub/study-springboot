package study.springboot.security.oauth.jwttutorial.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import study.springboot.security.oauth.jwttutorial.entity.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
  @NotNull
  @Size(min = 3, max = 50)
  private String userName;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @NotNull
  @Size(min = 3, max = 50)
  private String password;
  @NotNull
  @Size(min = 3, max = 50)
  private String nickName;

  private Set<AuthorityDto> authorityDtoSet;

  public static UserDto from(User user) {
    if(user == null) return null;

    return UserDto.builder()
      .userName(user.getUserName())
      .nickName(user.getNickName())
      .authorityDtoSet(user.getAuthorities().stream()
        .map(authority -> AuthorityDto.builder().authorityName(authority.getAuthorityName()).build())
        .collect(Collectors.toSet()))
      .build();
  }
}
