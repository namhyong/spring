package backend_project.spring.controller;

import backend_project.spring.dto.MemberDto;
import backend_project.spring.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Tag(name = "Auth", description = "로그인 관련 api")
@Controller
public class MemberController {
    @Autowired
    MemberService memberservice;
    @Operation(summary = "메인 페이지", description = "메인 페이지 메서드 입니다.")
@GetMapping("/")
    public String viewMain(){
    return "/index";
}
    @Operation(summary = "회원가입 페이지", description = "회원가입페이지 렌더 요청입니다.")
@GetMapping("/user/signup")
    public String dispSignUp(){
    return "/signup";
}
    @Operation(summary = "회원가입 요청", description = "회원가입 요청입니다.")
@PostMapping("/user/user/signup")
    public String exeSignup(MemberDto memberDto){
    memberservice.joinUser(memberDto);
    return "redirect:/user/login";
}
    @Operation(summary = "로그인 페이지", description = "로그인 페이지 렌더 요청입니다.")
@GetMapping("/user/login")
public String dispLogin(){
    return "/login";
}
    @Operation(summary = "로그인 성공", description = "로그인 성공 페이지 렌더 요청")
@GetMapping("/user/login/result")
public String dispLoginResult(){
    return "/loginSuccesful";
}
    @Operation(summary = "로그아웃 페이지", description = "로그아웃 요청입니다.")
    @GetMapping("/user/logout/result")
    public String dispLogout() {
        return "/logout";
    }
    @Operation(summary = "거부 페이지", description = "페이지 접근이 거부 되었을때 페이지 ")
@GetMapping("/user/denied")
public String dispDenied() {
        return "/denied";
}

    @Operation(summary = "유저 정보 페이지", description = "유저 정보 요청입니다.")
@GetMapping("/user/info")
    public String dispMyInfo(){
    return "/myinfo";
}
    @Operation(summary = "관리자 페이지", description = "관리자 페이지 입니다.")
@GetMapping("/admin")
    public String dispAdmin(){
    return "/admin";
}
}
