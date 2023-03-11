package Spring.api.spirng.api.controller;

import Spring.api.spirng.api.dto.UserDto;
import Spring.api.spirng.api.vo.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
    @GetMapping("/")
    public String home(){
        return "request";
    }
    @GetMapping("/post")
    public String main(){
        return "form";
    }
    @GetMapping("/dto")
    public String dto(){
        return "dtoform";
    }
    @GetMapping("/vo")
    public String vo(){
        return "voform";
    }
    @GetMapping("/axiosdto")
    public String axiosdto(){
        return "axiosdto";
    }
    @GetMapping("/axiosvo")
    public String axiosvo(){
        return "axiosvo";
    }
    @GetMapping("/signin")
    public String signin(){
        return "siginin";
    }
    @GetMapping("/update")
    public String update(){
        return "update";
    }

    @GetMapping("/get/response1")
    //들어오는 param이 있다면 name에 담아라 보내는 value와 name이 같아야 한다.
    //@RequestParam(name) String name2와 같이 다르게 사용 할 수도 있다.
    //required true이면 ?name=이 오고 빈값이 보내진다.
    public String getAPI(@RequestParam(required = true) String name , Model model){
        model.addAttribute("name",name);
        return "response";
    }

    @GetMapping("/get/response2")
    //들어오는 param이 있다면 name에 담아라 보내는 value와 name이 같아야 한다.
    //@RequestParam(name) String name2와 같이 다르게 사용 할 수도 있다.
    //required false이면, ?뒤에 값이 오지 않고, null값이 오게 된다.
    //만약 이 상황에서 required 가 true가 되면 이 api는 name값을 받아오는 곳이 없기 떄문에 무조건 name을 받아와야 하는 required =true 이기 때문에 오류페이지 나타나게 된다
    public String getAPI2(@RequestParam(required = false) String name , Model model){
        model.addAttribute("name",name);
        return "response";
    }
    @GetMapping("/get/response3/{name}/{age}")
    //{name}이 @PathVariable String name이 된다.
    public String getAPI3(@PathVariable String name, @PathVariable("age") String abc, Model model){
        model.addAttribute("name", name);
        model.addAttribute("age",abc);
        return "response";
    }
    //경로를 여러개 만들고 싶다면 배열로 만들어야 한다. 아래의 어떤 경로가 와도 getAPI4로 처리하라
    @GetMapping({"/get/response4/{name}","/get/response4/{name}/{age}"})
    //{name}이 @PathVariable String name이 된다.
    //만약 age가 없을때 처리하고자 하면 age값은 required false를 사용하여 필수값이 아니게 하여야 한다.
    public String getAPI4(@PathVariable String name, @PathVariable(value = "age", required = false) String abc, Model model){
        model.addAttribute("name", name);
        model.addAttribute("age",abc);
        return "response";

    }
    @PostMapping("/post/response1")
    public String PostAPI1(@RequestParam String name, Model model){
    model.addAttribute("name", name);
        return "response";
    }

    @PostMapping("/post/response2")
    public String PostAPI2(@RequestParam(required = false) String name, Model model){
        model.addAttribute("name", name);
        return "response";
    }

    @PostMapping("/post/response3")
    //ResponseBody를 PostMapping아래에 붙이면 자바 객체를 json기반 값을 보내는 형태(res.send)와 비슷함
    //데이터만 전달하고 싶을때 사용한다.
    @ResponseBody
    public String PostAPI3(@RequestParam(required = false) String name, Model model){
        model.addAttribute("name", name);
        return "이름은: "+ name;
    }
    /* -------------------------------------------------------------------------------*/
    @GetMapping("/introduce/{name}")
    public String APIGET(@PathVariable String name, Model model){
        model.addAttribute("name", name);
        return "API-GET";
    }

    @GetMapping("/introduce2")
    public String APIGET2(@RequestParam String name, @RequestParam  String age , Model model){
        model.addAttribute("name",name);
        model.addAttribute("age", age);
        return "API-GET2";
    }
    @PostMapping("/post/info")
    public String info(@RequestParam String name, @RequestParam String gender,@RequestParam String birthday, @RequestParam String hobby, Model model){
        model.addAttribute("name",name);
        model.addAttribute("gender",gender);
        model.addAttribute("birthday",birthday);
        model.addAttribute("hobby",hobby);
        return "info";
    }

    /* ---------------------------------DTO----------------------------------------------*/

    //DTO 폼전송
    @GetMapping("/dto/response1")
    @ResponseBody
    public String dtoAPI1(UserDto userDto){
        String msg = userDto.getName()+" "+userDto.getAge()+"!!!";
        return msg;
    }

    @PostMapping("/dto/response2")
    @ResponseBody
    public String dtoAPI2(UserDto userDto){
        String msg = userDto.getName()+" "+userDto.getAge()+"!!!";
        return msg;
    }

    @PostMapping("/dto/response3")
    @ResponseBody
    //RequestBody는 apllication json 형태의 파일만 매핑을 시킬 수 있기 때문에 일반 폼전송일때 사용 불가능
    public String dtoAPI3(@RequestBody UserDto userDto){
        String msg = userDto.getName()+" "+userDto.getAge()+"!!!";
        return msg;
    }

    /* ----------------------------------VO---------------------------------------------*/

    //dto에는 set함수가 있기 때문에 값이 들어갓지만, vo에는 set함수가 없기 때문에 값이 들어가지 않는다.
    //왜냐하면 변수에 값을 넣는 행위를 set함수를 불러와서 넣기 때문에
    @GetMapping("/vo/response1")
    @ResponseBody
    public String voAPI1(UserVO userVO){
        String msg = "이름: "+ userVO.getName()+" \n 나이: " + userVO.getAge();
        return msg;
    }
    @PostMapping("/vo/response2")
    @ResponseBody
    public String voAPI2(UserVO userVO){
        String msg ="이름: "+  userVO.getName()+" \n 나이: " + userVO.getAge();
        return msg;
    }
    @PostMapping("/vo/response3")
    @ResponseBody
    public String voAPI3(@RequestBody UserVO userVO){
        String msg = "이름: "+ userVO.getName()+" \n 나이: " + userVO.getAge();
        return msg;
    }
/* -------------------------------------AXIOSDTO------------------------------------------*/
    @GetMapping("axios/response1")
    @ResponseBody
    public String axiosAPI1(@RequestParam(value="name") String name, @RequestParam(value="age") String age){
        String msg ="이름:" + name + "\n 나이: " + age;
        return msg;
    }

    @GetMapping("axios/response2")
    @ResponseBody
    public String axiosAPI2(UserDto userDto){
        String msg = "이름 : " + userDto.getName() + "\n 나이: " + userDto.getAge();
        return msg;
    }
    @PostMapping("axios/response3")
    @ResponseBody
    public String axiosAPI3(@RequestParam(value="name") String name, @RequestParam(value="age") String age){
        String msg ="이름: " + name + "\n 나이: " + age;
        return msg;
    }

    @PostMapping("axios/response4")
    @ResponseBody
    public String axiosAPI4(UserDto userDto){
        String msg = "이름 : " + userDto.getName() + "\n 나이: " + userDto.getAge();
        return msg;
    }
   //Axios를 통해 json 형태로 post 요청을 하면, RequestBody를 통해서만 값을 받아올 수 있다.
    @PostMapping("axios/response5")
    @ResponseBody
    public String axiosvoAPI5(@RequestBody UserDto userDto){
        String msg = "이름 : " + userDto.getName() + "\n 나이: " + userDto.getAge();
        return msg;
    }
    /* -------------------------------------AXIOSVO------------------------------------------*/
    @GetMapping("axios/vo/response1")
    @ResponseBody
    public String axiosvoAPI1(@RequestParam(value="name") String name, @RequestParam(value="age") String age){
        String msg ="이름:" + name + "\n 나이: " + age;
        return msg;
    }

    @GetMapping("axios/vo/response2")
    @ResponseBody
    public String axiosvoAPI2(UserVO userVO){
        String msg = "이름 : " + userVO.getName() + "\n 나이: " + userVO.getAge();
        return msg;
    }
    @PostMapping("axios/vo/response3")
    @ResponseBody
    public String axiosvoAPI3(@RequestParam(value="name") String name, @RequestParam(value="age") String age){
        String msg ="이름: " + name + "\n 나이: " + age;
        return msg;
    }

    @PostMapping("axios/vo/response4")
    @ResponseBody
    public String axiosvoAPI4(UserVO userVO){
        String msg = "이름 : " + userVO.getName() + "\n 나이: " + userVO.getAge();
        return msg;
    }
    //Axios를 통해 json 형태로 VOpost 요청을 하면,원래는 set함수가 없기 때문에 값이 안들어와야하지만,
    //RequestBody를 사용하면 값을 받아올 수 있다.
    @PostMapping("axios/vo/response5")
    @ResponseBody
    public String axiosAPI5(@RequestBody UserVO userVO){
        String msg = "이름 : " + userVO.getName() + "\n 나이: " + userVO.getAge();
        return msg;
    }

    @PostMapping("/axios/vo/practice1")
    @ResponseBody
    public String axiospractice1(@RequestBody UserVO userVO){
        String msg = userVO.getName();
        return msg;
    }

    @PostMapping("/signin2")
    public String signin(  UserDto userDto ,Model model){

        model.addAttribute("name",userDto.getName());
        model.addAttribute("id",userDto.getId());
        model.addAttribute("pw",userDto.getPw());
        return "login";
    }

    @PostMapping("/user/update")
    @ResponseBody
    public String update(@RequestBody UserDto userDto ,Model model){
        String msg = userDto.getName(); userDto.getPw(); userDto.getId();
        return msg;
    }

}
