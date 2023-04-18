package whattoeattoday.whatoeattoday.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import whattoeattoday.whatoeattoday.dto.LoginRequest;
import whattoeattoday.whatoeattoday.dto.Response;
import whattoeattoday.whatoeattoday.jwt.TokenResponse;
import whattoeattoday.whatoeattoday.dto.UserDto;
import whattoeattoday.whatoeattoday.jwt.JwtProvider;
import whattoeattoday.whatoeattoday.jwt.UserDeatils;
import whattoeattoday.whatoeattoday.service.UserService;

import java.net.http.HttpHeaders;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final JwtProvider jwtProvider;

@PostMapping("/user/signup")
@ResponseBody
    public Response signup(@RequestBody UserDto userDto){
    return userService.joinUser(userDto);
}
@GetMapping("/user/signup")
    public String dispSignUp(){
        return "/signup";
    }
@GetMapping("/user/login")
    public String dispLogin(){ return "/login";}
@PostMapping("/user/login")
@ResponseBody
    public TokenResponse login(@RequestBody LoginRequest loginRequest) throws JsonProcessingException {
    Response response = userService.login(loginRequest);
    userService.loadUserByUsername(loginRequest.getEmail());
    return jwtProvider.createTokenByLoigin(response);
    }

    @GetMapping(value = "/user/myPage")
    public String userDetail(){
    return "myinfo";
    }

    @GetMapping("/user/reissue")
    public TokenResponse reissue( TokenResponse tokenResponse) throws JsonProcessingException{
    return jwtProvider.reissuedAtk(tokenResponse);
    }
}
