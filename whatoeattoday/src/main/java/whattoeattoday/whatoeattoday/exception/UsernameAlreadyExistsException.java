package whattoeattoday.whatoeattoday.exception;

public class UsernameAlreadyExistsException extends RuntimeException{
    public UsernameAlreadyExistsException(){
        super("이미 존재하는 아이디 입니다");
    }
}
