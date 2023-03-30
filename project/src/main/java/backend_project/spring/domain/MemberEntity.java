package backend_project.spring.domain;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="user")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length=50,nullable=false)
    private int id;
    @Column(length=1000,nullable=false)
    private String pw;
    @Column(length=10,nullable=false)
    private String name;

    @Column(length=50, nullable = false)
    private  String email;

}
