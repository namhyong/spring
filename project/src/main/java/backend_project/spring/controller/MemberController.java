package backend_project.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
    @Autowired
    MainService mainService;
@GetMapping("/")
    public String viewMain(){
    return "Main";
}
}
