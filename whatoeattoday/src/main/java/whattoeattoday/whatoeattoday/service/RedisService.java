package whattoeattoday.whatoeattoday.service;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class RedisService {
    private  final RedisTemplate redisTemplate;

    public String getValues(String key){
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        return values.get(key);
    }
    public void setValues(String key, String value){
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        values.set(key,value);
    }
    public void setSets(String key,String... values){
        redisTemplate.opsForSet().add(key,values);
    }
    public Set getSets(String key){
        return redisTemplate.opsForSet().members(key);
    }
}
