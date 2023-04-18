//package whattoeattoday.whatoeattoday.config;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import whattoeattoday.whatoeattoday.jwt.JwtProvider;
//import whattoeattoday.whatoeattoday.jwt.JwtAuthenticationFilter;
//import whattoeattoday.whatoeattoday.service.UserService;
//
//@Configuration
//@RequiredArgsConstructor
//public class InterceptorConfig implements WebMvcConfigurer {
//    private final JwtProvider jwtProvider;
//    private final UserService userService;
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry){
//        registry.addMapping("/**").allowedOriginPatterns("*").allowedMethods("*");
//    }
//    @Override
//    public void addInterceptors(InterceptorRegistry registry){
//        registry.addInterceptor(new JwtAuthenticationFilter(jwtProvider,userService)).addPathPatterns("/user/mypage");
//
//    }
//    public  void configure(HttpSecurity http) throws  Exception{
//        http.csrf().disable();
//    }
//}
