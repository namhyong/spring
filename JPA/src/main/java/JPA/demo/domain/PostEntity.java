package JPA.demo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity@Table(name="post")
@Getter
@Setter
public class PostEntity {
    @Id
    @GeneratedValue
    private int index_number;

    @Column(length=25,nullable=false)
    private String title;

    @Column(length=3000,nullable=false)
    private String maintext;

    @Column(length=10,nullable=false)
    private String writer;
}
