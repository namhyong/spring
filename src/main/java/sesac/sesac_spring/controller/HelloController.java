package sesac.sesac_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sesac.sesac_spring.Person;

import java.util.ArrayList;

@Controller
public class HelloController {
    @GetMapping("/hello") // app.get("/") getmethod로 /hi에 접속하면 아래 함수 실행
    public String getHi(Model model){ //Model사용 하여 enter 또는 마우스 올리고 alt enter로 import
        model.addAttribute("msg", "메세지 입니다."); //thymeleaf에서 보낸 데이터 받는 것 attributeName
        model.addAttribute("utext", "<strong>utext입니다.</strong>");
        model.addAttribute("age1",21);
        model.addAttribute("age1msg","<span>20세는 성인 입니다.</span>");
        model.addAttribute("age2msg","<span>10세는 미성년자 입니다.</span>");
        model.addAttribute("age2",10);
        //res.render("hi", model);
        return "hi"; // 문자열 return은  res.render("hi")라는 의미
    }


    @GetMapping("/people")
    public String getPeople(Model model) {
        ArrayList<Person> people = new ArrayList<Person>();
        people.add(new Person("이름1", 10));
        people.add(new Person("이름2", 20));
        people.add(new Person("이름3", 30));
        people.add(new Person("이름4", 40));
        model.addAttribute("people", people);
        return "people";
    }

}
