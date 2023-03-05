package study.springboot.db.redis.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.springboot.db.redis.application.port.in.RedisUscCase;
import study.springboot.db.redis.application.port.out.RedisStringPort;

@Service
@RequiredArgsConstructor
public class RedisService implements RedisUscCase<String> {
  private final RedisStringPort redisStringPort;

  @Override
  public String getValue(String key) {
    return redisStringPort.getValue(key);
  }

  @Override
  public void setValue(String key, String value) {
    redisStringPort.setValue(key, value);
  }
}
