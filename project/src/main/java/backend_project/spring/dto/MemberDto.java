package backend_project.spring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {
    private String id;
    private String pw;
    private String name;
    private String email;
}
