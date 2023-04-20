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
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        //로그인으로 오는 요청은 필터 무시(해당 주소에대한 request,response처리)
        request.getRequestDispatcher("/user/login").forward(request, response);

        // header에서 atk 분리 과정
        String authorization = jwtProvider.extractToken(request.getHeader("Authorization"));
        System.out.println("atk" + request.getHeader("Authorization"));
        if (authorization != null && jwtProvider.isUsable(authorization)) {
            // header의 atk를 파싱하는 과정
            Subject subject = jwtProvider.extractAllClaims(authorization);
            System.out.println("파싱 데이터:" + subject.getEmail());
            UserDetails userDetails = userService.loadUserByUsername(subject.getEmail());
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                    userDetails, "", userDetails.getAuthorities())
            );
            chain.doFilter(request, response);
        }else{
            jwtProvider.reissuedAtk(authorization);
        }
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