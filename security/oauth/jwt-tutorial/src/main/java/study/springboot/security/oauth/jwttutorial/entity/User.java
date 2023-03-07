package study.springboot.security.oauth.jwttutorial.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Table(name = "user")
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="user_id")
  private Long userId;

  @Column(name = "user_name")
  private String userName;

  @Column(name = "password")
  private String password;

  @Column(name = "nick_name")
  private String nickName;

  @Column(name = "activated")
  private boolean activated;

  @ManyToMany
  @JoinTable(
    name = "user_authority",
    joinColumns = {@JoinColumn(name="user_id", referencedColumnName = "user_id")},
    inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")}
  )
  private Set<Authority> authorities;

}
