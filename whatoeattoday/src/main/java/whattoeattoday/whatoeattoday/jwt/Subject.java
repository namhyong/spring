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

    public static Subject atk(String email){
        Subject sub = new Subject();
        sub.setEmail(email);
        sub.setType("ATK");
    return sub;
        }
    public static Subject rtk(String email) {
        Subject sub2 = new Subject();
        sub2.setEmail(email);
        sub2.setType("RTK");
        return sub2;
    }
}


