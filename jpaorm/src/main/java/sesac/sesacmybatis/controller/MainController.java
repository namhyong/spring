package sesac.sesacmybatis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sesac.sesacmybatis.domain.User;
import sesac.sesacmybatis.domain.UserEntity;
import sesac.sesacmybatis.dto.PersonDTO;
import sesac.sesacmybatis.dto.UserDTO;
import sesac.sesacmybatis.service.MainService;

import java.awt.*;
import java.util.ArrayList;
@Controller
public class MainController {
@Autowired
MainService mainService;

@GetMapping("/users")
    public String getUsers(Model model){
    //db에서 가져온 것을 for문 돌려 넣은 users라는 ArrayList 리턴하는 함수 getUserList
    ArrayList<UserDTO> userList = (ArrayList<UserDTO>) mainService.getUserList();
    model.addAttribute("list",userList);
    return "user";
}
//view단에서 받아온 이름을 domain을 통해 Mapper로 전달해서  db에 insert문을 사용하겠다.
    //하지만, 원래는 dto로 받아서 service에서 domain으로 보내줬어야함, domain은 controller에서 다루면 안됨
@GetMapping("/user/insert")
    public String getInsertUser(@RequestParam String name, @RequestParam String nickname, Model model){
    UserEntity user = new UserEntity();
    user.setName(name);
    user.setNickname(nickname);
    mainService.addUser(user);
    model.addAttribute("list", null);
    return "user";
}
@GetMapping("/user")
//MainService의 getUserName안에 findByName을 통해 이름이 검색되어 나타남
    public String getUser(@RequestParam String name, Model model){
    ArrayList<UserDTO> userList = mainService.getUserName(name);

    model.addAttribute("list", userList);
    return "user";

}


}
