package whattoeattoday.whatoeattoday.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import whattoeattoday.whatoeattoday.jwt.JwtProvider;
import whattoeattoday.whatoeattoday.jwt.JwtAuthenticationFilter;
import whattoeattoday.whatoeattoday.service.UserService;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig  {

    private final UserService userService;
    private final JwtProvider jwtProvider;
    private final RedisTemplate redisTemplate;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() throws Exception{
       return(web)-> web.ignoring().antMatchers("/css/**", "/js/**","img/**","/lib/**");
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws  Exception{
        http.authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .antMatchers(HttpMethod.POST,"/auth/login").permitAll()
                .anyRequest().authenticated();
        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .permitAll();
        http.csrf().disable();
        http.cors().and().csrf().disable()
                .exceptionHandling()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtProvider,userService), UsernamePasswordAuthenticationFilter.class)
                .formLogin().disable();
        return http.build();
//        http.exceptionHandling()
//                .authenticationEntryPoint(jwtAuthenticationEntryPoint);
//                .accessDeniedHandler(jwtAccessDeniedHandler);
//        http.csrf().disable()
//                        .httpBasic().disable()
//                        .formLogin().disable()
//                        .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
//                                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http.headers().frameOptions().sameOrigin();
    }
}
