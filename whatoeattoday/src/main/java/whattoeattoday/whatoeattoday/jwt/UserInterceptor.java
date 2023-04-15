package whattoeattoday.whatoeattoday.jwt;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.WebUtils;
import whattoeattoday.whatoeattoday.exception.ApiException;
import whattoeattoday.whatoeattoday.exception.BadRequestException;
import whattoeattoday.whatoeattoday.repository.UserRepository;
import whattoeattoday.whatoeattoday.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.http.HttpHeaders;

@RequiredArgsConstructor
public class UserInterceptor implements HandlerInterceptor {
    private final JwtProvider jwtProvider;
    private final UserService userService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorization = jwtProvider.extractToken(request.getHeader("Authorization"));
        if (authorization != null) {
            boolean flag = jwtProvider.isUsable(authorization);
            if (flag) {
                Subject subject = jwtProvider.extractAllClaims(authorization);
                UserDetails userDetails = userService.loadUserByUsername(subject.getEmail());
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                        userDetails, "", userDetails.getAuthorities())
                );
            }
//            else{
//                Cookie refreshCookie = WebUtils.getCookie(request,"X-REFRESH=TPKEN");
//                if(refreshCookie != null){
//                    String refreshcookie = URLDecoder.decode(refreshCookie.getValue(),"UTF-8");
//                    if (!jwtAuthenticationFilter.isUsable(refreshToken)) {
//                        throw new ApiException();
//                    }
//                    Subject subject = jwtAuthenticationFilter.extractAllClaims(refreshToken);
//                    response.setStatus("X-AUTH-TOKEN",jwtProvider.generate);
//                }
            }
        return true;
        }

}
