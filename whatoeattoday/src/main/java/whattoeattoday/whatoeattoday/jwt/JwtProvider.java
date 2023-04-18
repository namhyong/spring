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
        Subject rtkJwt = Subject.rtk();
        String atk = createToken(atkJwt, atkValidity);
        String rtk = createToken(rtkJwt, rtkValidity);
        redisTemplate.opsForValue().set(response.getEmail(),rtk, rtkValidity, TimeUnit.SECONDS);
        return new TokenResponse(atk, rtk);
    }
    private String createToken(Subject subject, Long atkValidity) throws  JsonProcessingException{

        //        String JwtStr = objectMapper.writeValueAsString(subject);
        Claims claims = Jwts.claims()
                .setSubject(subject.getEmail())
                .setIssuer(subject.getName());
        Date date = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(date)
                .setExpiration(new Date(date.getTime() + atkValidity))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }
    public String extractToken(String authorizationHeader){
        return authorizationHeader.equals("")
                ? authorizationHeader : authorizationHeader.substring("Bearer".length());
    }
    public boolean isUsable(String token){
        if(token.equals("")){
            throw new ApiException();
        }
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
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
        Claims claims = Jwts.parser().setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        Subject subject = new Subject();
        subject.setId((Integer) claims.get("id"));
        subject.setEmail((String) claims.get("email"));
        return subject;

    }
    public TokenResponse reissuedAtk(TokenResponse tokenResponse)throws JsonProcessingException{
        Subject atk = extractAllClaims(tokenResponse.getAtk());
        if(Objects.isNull(atk)) throw new Error("인증 정보가 만료되었습니다.");
        String refreshToken = (String)redisTemplate.opsForValue().get(atk.getEmail());
        if(refreshToken  == null) throw new Error("리프레시 토큰이 없습니다.");
        if(!refreshToken.equals(tokenResponse.getRtk())) throw new Error("refresh토큰의 정보가 일치하지 않습니다.");
        Subject newAtkInfo = Subject.atk(atk.getId(),atk.getEmail(),atk.getName());
        String newAtk = createToken(newAtkInfo, atkValidity);
        redisTemplate.opsForValue().set(newAtkInfo.getEmail(),refreshToken, rtkValidity, TimeUnit.SECONDS);
        TokenResponse token = new TokenResponse(newAtk, refreshToken);
        return token;
    }
}
