package whattoeattoday.whatoeattoday.dto;

import lombok.*;
import whattoeattoday.whatoeattoday.domain.UserEntity;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private String email;
    private String password;
    private String name;

//    public UserEntity toEntity(){
//        return UserEntity.builder()
//                .email(email)
//                .password(password)
//                .name(name)
//                .build();
//    }
//    @Builder
//    public UserDto( String email, String password, String name){
//        this.email = email;
//        this.password = password;
//        this.name = name;
//    }
}
