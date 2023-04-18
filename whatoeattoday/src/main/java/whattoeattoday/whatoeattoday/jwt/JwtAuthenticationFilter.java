package whattoeattoday.whatoeattoday.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerInterceptor;
import whattoeattoday.whatoeattoday.service.UserService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;
    private final UserService userService;
    public JwtAuthenticationFilter(JwtProvider jwtProvider, UserService userService){
        this.jwtProvider = jwtProvider;
        this.userService= userService;
    }
    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException{
        String authorization = jwtProvider.extractToken(request.getHeader("Authorization"));
        if( authorization!= null && jwtProvider.isUsable(authorization)){
            Subject subject = jwtProvider.extractAllClaims(authorization);
            UserDetails userDetails = userService.loadUserByUsername(subject.getEmail());
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                        userDetails, "", userDetails.getAuthorities())
                );
        }
        chain.doFilter(request,response);
    }
}
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String authorization = jwtProvider.extractToken(request.getHeader("Authorization"));
//        if (authorization != null) {
//            boolean flag = jwtProvider.isUsable(authorization);
//            if (flag) {
//                Subject subject = jwtProvider.extractAllClaims(authorization);
//                UserDetails userDetails = userService.loadUserByUsername(subject.getEmail());
//                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
//                        userDetails, "", userDetails.getAuthorities())
//                );
//            }
////            else{
////                Cookie refreshCookie = WebUtils.getCookie(request,"X-REFRESH=TPKEN");
////                if(refreshCookie != null){
////                    String refreshcookie = URLDecoder.decode(refreshCookie.getValue(),"UTF-8");
////                    if (!jwtAuthenticationFilter.isUsable(refreshToken)) {
////                        throw new ApiException();
////                    }
////                    Subject subject = jwtAuthenticationFilter.extractAllClaims(refreshToken);
////                    response.setStatus("X-AUTH-TOKEN",jwtProvider.generate);
////                }
//        }
//        return true;
//    }