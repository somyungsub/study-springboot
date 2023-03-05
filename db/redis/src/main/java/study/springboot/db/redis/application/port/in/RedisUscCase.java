package study.springboot.db.redis.application.port.in;

public interface RedisUscCase<T> {
  T getValue(T key);

  void setValue(T key, T value);
}
