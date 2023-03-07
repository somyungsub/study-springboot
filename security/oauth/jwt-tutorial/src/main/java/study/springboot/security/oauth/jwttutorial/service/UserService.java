package study.springboot.security.oauth.jwttutorial.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.springboot.security.oauth.jwttutorial.dto.UserDto;
import study.springboot.security.oauth.jwttutorial.entity.Authority;
import study.springboot.security.oauth.jwttutorial.entity.User;
import study.springboot.security.oauth.jwttutorial.execption.DuplicateMemberException;
import study.springboot.security.oauth.jwttutorial.execption.NotFoundMemberException;
import study.springboot.security.oauth.jwttutorial.repository.UserRepository;
import study.springboot.security.oauth.jwttutorial.util.SecurityUtils;

import java.util.Collections;

@Service
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Transactional
  public UserDto signup(UserDto userDto) {
    if (userRepository.findOneWithAuthoritiesByUserName(userDto.getUserName()).orElse(null) != null) {
      throw new DuplicateMemberException("이미 가입되어 있는 유저입니다.");
    }

    Authority authority = Authority.builder()
      .authorityName("ROLE_USER")
      .build();

    User user = User.builder()
      .userName(userDto.getUserName())
      .password(passwordEncoder.encode(userDto.getPassword()))
      .nickName(userDto.getNickName())
      .authorities(Collections.singleton(authority))
      .activated(true)
      .build();

    return UserDto.from(userRepository.save(user));
  }

  @Transactional(readOnly = true)
  public UserDto getUserWithAuthorities(String username) {
    return UserDto.from(userRepository.findOneWithAuthoritiesByUserName(username).orElse(null));
  }

  @Transactional(readOnly = true)
  public UserDto getMyUserWithAuthorities() {
    return UserDto.from(
      SecurityUtils.getCurrentUsername()
        .flatMap(userRepository::findOneWithAuthoritiesByUserName)
        .orElseThrow(() -> new NotFoundMemberException("Member not found"))
    );
  }
}
