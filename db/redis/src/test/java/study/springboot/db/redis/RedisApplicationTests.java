package study.springboot.db.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
class RedisApplicationTests {

	@Autowired
	RedisTemplate redisTemplate;
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	@Test
	void contextLoads() {
		System.out.println("redisTemplate = " + redisTemplate);
		System.out.println("stringRedisTemplate = " + stringRedisTemplate);
	}

	@Test
	void testString() {
		String key = "newKey3";
		ValueOperations<String, String> forValue = stringRedisTemplate.opsForValue();
		forValue.set(key, "1");
		System.out.println(forValue.get(key));
		forValue.increment(key);

		forValue.set(key, "2");
		System.out.println(forValue.get(key));
		forValue.increment(key);

		forValue.set(key, "3");
		System.out.println(forValue.get(key));
		forValue.increment(key);

		forValue.set(key, "0");
		System.out.println(forValue.get(key));
		forValue.increment(key);

		forValue.set(key, "1");
		System.out.println(forValue.get(key));
		forValue.increment(key);
	}

}
