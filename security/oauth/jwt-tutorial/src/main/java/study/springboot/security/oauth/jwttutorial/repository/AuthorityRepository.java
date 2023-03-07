package study.springboot.security.oauth.jwttutorial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.springboot.security.oauth.jwttutorial.entity.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
