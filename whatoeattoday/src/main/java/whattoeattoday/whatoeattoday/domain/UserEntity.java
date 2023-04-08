package whattoeattoday.whatoeattoday.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Getter
@Setter
@RedisHash(value = "people", timeToLive = 30)
public class UserEntity {
    @Id
    private String email;
    private String name;
    private String password;
    private LocalDateTime createdAt;


}
