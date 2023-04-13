package whattoeattoday.whatoeattoday.jwt;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.servlet.HandlerInterceptor;
import whattoeattoday.whatoeattoday.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
public class UserInterceptor implements HandlerInterceptor {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Override
public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
    String jwt = jwtAuthenticationFilter.toString();
    }
}
