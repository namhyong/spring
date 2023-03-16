package sesac.sesacmybatis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sesac.sesacmybatis.dto.PersonDTO;
import sesac.sesacmybatis.service.MainService;
import sesac.sesacmybatis.service.PersonService;

@Controller
//@RestController // personController 안에 있는 함수들이 @ResponseBody가 붙는다.
//@RequestMapping("/person") // class mapping위에 적으면 아래있는 모든 주소들 앞에 /person이 붙는다. ex)/person/signin이 되는 형태
@RequestMapping("/person")
public class PersonController {
   @Autowired
    PersonService personService;
    @GetMapping("/signin")
    public String signin(){
        return "signin";
    }
    @PostMapping("/signin")
    @ResponseBody
    public String postsignin(@RequestBody PersonDTO personDTO){
        personService.insertPerson(personDTO);

        return "";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public boolean postlogin(@RequestBody PersonDTO personDTO){
        PersonDTO person = personService.getPerson(personDTO);
        // service에서 리턴 한 값이 person에 담기는데 그 값이 null 이면 html로 false로 return 아니라면(값이 있다며) true로 return
        if(person == null) return false;
        else return true;
    }
    @PostMapping("/info")
    public String postInto(PersonDTO personDTO, Model model){
        PersonDTO person = personService.getPerson(personDTO);
        model.addAttribute("person", person);
        return"info";
    }

    @PostMapping("/info/edit")
    @ResponseBody
    public String infoEdit(@RequestBody PersonDTO personDTO ){
        personService.udatePerson(personDTO);
        return "";
    }

    @PostMapping("/info/delete")
    @ResponseBody
    public String infodelete(@RequestBody PersonDTO personDTO){
        personService.deletePerson(personDTO);
        return"";
    }
}
