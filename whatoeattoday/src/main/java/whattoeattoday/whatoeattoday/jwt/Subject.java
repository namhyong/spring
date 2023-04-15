package whattoeattoday.whatoeattoday.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Subject {
    private int id;
    private String email;
    private String name;
    private String type;

    public static Subject atk( int id, String email, String name, String type){
        Subject sub = new Subject();
        sub.setId(id);
        sub.setEmail(email);
        sub.setName(name);
        sub.setType("ATK");
    return sub;
        }
    public static Subject rtk(int id, String email, String name, String type) {
        Subject sub2 = new Subject();
        sub2.setId(id);
        sub2.setEmail(email);
        sub2.setName(name);
        sub2.setType("RTK");
        return sub2;
    }
}


