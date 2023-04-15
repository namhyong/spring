package whattoeattoday.whatoeattoday.exception;

public class ApiException extends RuntimeException{
    public ApiException(){
        super("토큰을 찾을 수 없습니다 로그인을 확인해 주세요");
    }
}