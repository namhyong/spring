package whattoeattoday.whatoeattoday.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.ObjectUtils;
import whattoeattoday.whatoeattoday.dto.RedisDto;
import whattoeattoday.whatoeattoday.dto.Response;
import whattoeattoday.whatoeattoday.exception.ApiException;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class JwtProvider {
private ObjectMapper objectMapper;
private final RedisDto redisDto;

private final RedisTemplate redisTemplate;
    @Value("${spring.jwt.secret}")
    private String jwtSecret;
    @Value("${spring.jwt.access-token-validity-seconds}")
    private Long atkValidity;
    @Value("${spring.jwt.refresh-token-validity-seconds}")
    private Long rtkValidity;

@PostConstruct
    protected void init(){
        jwtSecret = Base64.getEncoder().encodeToString(jwtSecret.getBytes());
    }
    public TokenResponse createTokenByLoigin(Response response) throws JsonProcessingException {
        Subject atkJwt = Subject.atk(response.getId(),response.getEmail(),response.getName());
        Subject rtkJwt = Subject.rtk(response.getId(),response.getEmail(),response.getName());
        String atk = createToken(atkJwt, atkValidity);
        String rtk = createToken(rtkJwt, rtkValidity);
        redisTemplate.opsForValue().set(atk,rtk, rtkValidity, TimeUnit.MILLISECONDS);
        return new TokenResponse(atk, rtk);
    }
    private String createToken(Subject subject, Long atkValidity) throws  JsonProcessingException{

        //        String JwtStr = objectMapper.writeValueAsString(subject);
        Claims claims = Jwts.claims()
                .setSubject(subject.getEmail())
                .setIssuer(subject.getName())
                .setId(String.valueOf(subject.getId()));
        Date date = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(date)
                .setExpiration(new Date(date.getTime() + atkValidity))
                .signWith(SignatureAlgorithm.HS256, jwtSecret.getBytes())
                .compact();
    }
    public String extractToken(String authorizationHeader){
        return authorizationHeader.equals("")
                ? authorizationHeader : authorizationHeader.substring("Bearer ".length());

    }
    public boolean isUsable(String token){
        if(token.equals("")){
            throw new ApiException();
        }
        try{
            Jwts.parser().setSigningKey(jwtSecret.getBytes()).parseClaimsJws(token);
            return true;
        }
        /* 토크이 유효하지 않을 때*/
        catch (MalformedJwtException e){
            throw new ApiException();
        }
        /* 토크이 만료 됐을 때*/
        catch (ExpiredJwtException e){
            return false;
        }
    }
    public Subject extractAllClaims(String token) throws ExpiredJwtException {
    System.out.println("토큰"+token);
        String claims = Jwts.parser().setSigningKey(jwtSecret.getBytes())
                .parseClaimsJws(token).getBody().getSubject();
        Subject subject = new Subject();
        subject.setEmail(claims);
        System.out.println("섭젝"+ subject.getEmail());
//        subject.setName((String) claims.get("iss"));
        //subject.setEmail();
        //System.out.println((String)claims.get("id"));
//        subject.setId(Integer.valueOf((String)claims.get("jti")));
        return subject;

    }
    public TokenResponse reissuedAtk(String atk)throws JsonProcessingException{
        if(Objects.isNull(atk)) throw new Error("인증 정보가 만료되었습니다.");
        System.out.println("atk "+atk);
        String refreshToken = (String)redisTemplate.opsForValue().get(atk);
        System.out.println("rtk "+refreshToken);
        if(refreshToken  == null) throw new Error("리프레시 토큰이 없습니다.");
        Subject rtkInfo = extractAllClaims(refreshToken);
        Subject newAtkInfo = Subject.atk(rtkInfo.getId(),rtkInfo.getEmail(),rtkInfo.getName());
        String newAtk = createToken(newAtkInfo, atkValidity);
        System.out.println(newAtk);
        redisTemplate.rename(atk, newAtk);
        System.out.println(redisTemplate.opsForValue().get(newAtk));
        return new TokenResponse(newAtk, refreshToken);
    }
}
