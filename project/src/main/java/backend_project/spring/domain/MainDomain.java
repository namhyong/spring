package backend_project.spring.domain;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name="user")
public class MainDomain {
    @Id
    @Column(length=50,nullable=false)
    private String id;
    @Column(length=50,nullable=false)
    private String pw;
    @Column(length=10,nullable=false)
    private String name;


}
