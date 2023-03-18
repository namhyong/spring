package JPA.demo.controller;

import JPA.demo.dto.PostDTO;
import JPA.demo.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class MainController {
    @Autowired
    MainService mainService;
    @GetMapping("/post")
    public String post(){
        return"post";
    }

    @PostMapping("/post")
    @ResponseBody
    public String posting(@RequestBody PostDTO postDTO){
    mainService.insertPost(postDTO);
    return"";
    }

    @GetMapping("/postList")
    public String postList(Model model){
        ArrayList<PostDTO> postList = (ArrayList<PostDTO>) mainService.findPost();
        model.addAttribute("postList", postList);
        return "postList";
    }
    @GetMapping("/postDetail/{index_number}")
    public String postDetail(@PathVariable int index_number, Model model){
        PostDTO postDetail = mainService.showDetail(index_number);
        model.addAttribute("postDetail", postDetail);
        return "detail";
    }

    @PostMapping("/edit")
    @ResponseBody
    public String editPost(@RequestBody PostDTO postDTO){
        mainService.editPost(postDTO);
        return"";
    }
    @PostMapping("/delete")
    @ResponseBody
    public String deletePost(@RequestBody PostDTO postDTO){
        mainService.deletePost(postDTO);
        return "";
    }
}
