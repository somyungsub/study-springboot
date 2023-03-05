package study.springboot.db.redis.application.port.out;

public interface RedisStringPort {
  String getValue(String key);
  void setValue(String key, String value);
}
