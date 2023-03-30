package backend_project.spring.controller;

import backend_project.spring.dto.MemberDto;
import backend_project.spring.service.MemberService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    @Autowired
    MemberService memberservice;
@GetMapping("/")
    public String viewMain(){
    return "/index";
}

@GetMapping("/user/signup")
    public String dispSignUp(){
    return "/signup";
}

@PostMapping("/user/signup")
    public String exeSignup(MemberDto memberDto){
    memberservice.joinUser(memberDto);
    return "redirect:/user/login";
}
@GetMapping("/user/login")
public String dispLogin(){
    return "/login";
}
@GetMapping("/user/login/result")
public String dispLoginResult(){
    return "/loginSuccesful";
}
    @GetMapping("/user/logout/result")
    public String dispLogout() {
        return "/logout";
    }

@GetMapping("/user/denied")
public String dispDenied() {
        return "/denied";
}

@GetMapping("/user/info")
    public String dispMyInfo(){
    return "/myinfo";
}

@GetMapping("/admin")
    public String dispAdmin(){
    return "/admin";
}
}
