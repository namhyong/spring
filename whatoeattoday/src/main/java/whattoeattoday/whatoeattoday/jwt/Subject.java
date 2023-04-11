package whattoeattoday.whatoeattoday.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Subject {
    private int id;
    private String email;
    private String name;
    private String type;

    public static Subject atk(int id, String email, String name, String type){
    return new Subject(id, email, name, "ATK");
        }
    public static Subject rtk(int id, String email, String name, String type) {
        return new Subject(id, email, name, "RTK");
    }
}


