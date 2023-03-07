package study.springboot.security.oauth.jwttutorial.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import study.springboot.security.oauth.jwttutorial.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
  @EntityGraph (attributePaths = "authorities")
  Optional<User> findOneWithAuthoritiesByUserName(String userName);
}
