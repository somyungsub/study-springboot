package study.springboot.db.redis.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import study.springboot.db.redis.application.port.in.RedisUscCase;

@RestController
@RequiredArgsConstructor
@RequestMapping("/redis")
public class RedisController {
  private final RedisUscCase<String> redisUscCase;

  @GetMapping("/test/{key}")
  @ResponseBody
  public String get(@PathVariable(name = "key") String key) {
    return redisUscCase.getValue(key);
  }

  @PostMapping("/test/{key}/{value}")
  @ResponseBody
  public String set(@PathVariable(name = "key") String key,
                    @PathVariable(name = "value") String value) {
    redisUscCase.setValue(key, value);
    return "success";
  }
}
