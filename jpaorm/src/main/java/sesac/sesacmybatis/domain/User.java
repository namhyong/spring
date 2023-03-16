package sesac.sesacmybatis.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//class 위에 @Getter, @Setter 어노테이션을 적으면, getter,setter를 만들지 않아도 사용 가능하다.
public class User {
    private int id;
    private String name;
    private String nickname;

    private String userId;
    private String password;

//    public int getId() {return id;}
//    public void setId(int id){this.id = id;}
//    public String getName() {return name;}
//
//    public void setName(String name){this.name = name;}
//    public  String getNickname(){return nickname;}
//    public void setNickname(String nickname){this.nickname = nickname;}

}
