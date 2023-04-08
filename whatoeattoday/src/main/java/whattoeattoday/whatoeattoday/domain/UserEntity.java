package whattoeattoday.whatoeattoday.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="user")
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length=100,nullable=false)
    private String email;
    @Column(length=100,nullable=false)
    private String name;
    @Column(length=100,nullable=false)
    private String password;

//    @Builder
//    public UserEntity( String email, String password,String name) {
//
//        this.email = email;
//        this.password = password;
//        this.name = name;
//    }

}
