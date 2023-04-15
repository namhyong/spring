package whattoeattoday.whatoeattoday.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import whattoeattoday.whatoeattoday.dto.Response;
import whattoeattoday.whatoeattoday.exception.ApiException;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {
private ObjectMapper objectMapper;
    @Value("${spring.jwt.secret}")
    private String jwtSecret;
    @Value("${spring.jwt.access-token-validity-seconds}")
    private Long atkValidity;

    @PostConstruct
    protected void init(){
        jwtSecret = Base64.getEncoder().encodeToString(jwtSecret.getBytes());
    }
    public TokenResponse createTokenByLoigin(Response response) throws JsonProcessingException {
        Subject atkJwt = Subject.atk(response.getId(),response.getEmail(),response.getName(),"ATK");
        String atk = createToken(atkJwt, atkValidity);
        return new TokenResponse(atk, null);
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
}
