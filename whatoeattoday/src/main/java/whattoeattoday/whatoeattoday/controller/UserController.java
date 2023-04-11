package whattoeattoday.whatoeattoday.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
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
import whattoeattoday.whatoeattoday.service.UserService;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProvider jwtProvider;

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
    return jwtProvider.createTokenByLoigin(response);
    }
}
