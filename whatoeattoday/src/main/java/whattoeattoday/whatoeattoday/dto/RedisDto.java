package whattoeattoday.whatoeattoday.dto;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class RedisDto {
    public  final RedisTemplate<String,String> redisTemplate;

    public RedisDto(RedisTemplate<String,String> redisTemplate){
        this.redisTemplate = redisTemplate;
    }
    public void setValue(String key, String data){
        ValueOperations<String,String> values = redisTemplate.opsForValue();
        values.set(key, data);
    }
    public void setValues(String key, String data, Duration duration){
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        values.set(key,data,duration);
    }
    public String getValues(String key){
        ValueOperations<String,String> values = redisTemplate.opsForValue();
        return values.get(key);
    }
    public void deleteValue(String key){
        redisTemplate.delete(key);
    }
}
