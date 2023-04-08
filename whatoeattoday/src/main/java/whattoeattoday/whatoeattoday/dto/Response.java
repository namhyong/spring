package whattoeattoday.whatoeattoday.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import whattoeattoday.whatoeattoday.domain.UserEntity;

@Getter
@AllArgsConstructor
public class Response {
    private  int id;
    private String email;
    private String name;

    public static Response of(UserEntity userEntity){
        return new Response(
                userEntity.getId(),
                userEntity.getEmail(),
                userEntity.getName()
        );
    }

}
