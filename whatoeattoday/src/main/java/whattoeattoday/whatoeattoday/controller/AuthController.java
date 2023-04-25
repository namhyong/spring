package whattoeattoday.whatoeattoday.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import whattoeattoday.whatoeattoday.dto.LoginRequest;
import whattoeattoday.whatoeattoday.dto.Response;
import whattoeattoday.whatoeattoday.dto.UserDto;
import whattoeattoday.whatoeattoday.jwt.JwtProvider;
import whattoeattoday.whatoeattoday.jwt.TokenResponse;
import whattoeattoday.whatoeattoday.service.UserService;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtProvider jwtProvider;
    @PostMapping("/signup")
    @ResponseBody
    public Response signup(@RequestBody UserDto userDto){
        return userService.joinUser(userDto);
    }
    @GetMapping("/signup")
    public String dispSignUp(){
        return "/signup";
    }
    @GetMapping("/login")
    public String dispLogin(){ return "/login";}
    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest loginRequest){
        return userService.login(loginRequest);
    }
    //@PostMapping("/auth/login")
//@ResponseBody
//    public TokenResponse login(@RequestBody LoginRequest loginRequest) throws JsonProcessingException {
////    Response response = userService.login(loginRequest);
//    UserDetails user = userService.loadUserByUsername(loginRequest.getEmail());
//    Response response = Response.of()
//    return jwtProvider.createTokenByLoigin(user);
//    }
}
