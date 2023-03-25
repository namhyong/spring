package backend_project.spring.controller;

import backend_project.spring.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
    @Autowired
    MainService mainService;
@GetMapping("/")
    public String viewMain(){
    return "Main";
}
}
