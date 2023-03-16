package sesac.sesacmybatis.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity // 해당 클래스가 Entitiy 클래스라는 것을 알려준다
@Table(name="user") // 테이블 이름 명시
@Getter
@Setter
public class UserEntity {

    @Id // PK 설정
    @GeneratedValue // auto_increment 설정
    private int id; // id primaru key auto_increment

    @Column(length=10, nullable=false)
    private String name;

    @Column(length=10, nullable=false)
    private String nickname;

}
