package study.springboot.db.redis.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;
import study.springboot.db.redis.application.port.out.RedisStringPort;

@Repository
@RequiredArgsConstructor
public class RedisStringAdapter implements RedisStringPort {
  private final StringRedisTemplate stringRedisTemplate;

  @Override
  public String getValue(String key) {
    return stringRedisTemplate.opsForValue().get(key);
  }

  @Override
  public void setValue(String key, String value) {
    stringRedisTemplate.opsForValue().set(key, value);
  }
}
