package JPA.demo.service;

import JPA.demo.domain.Post;
import JPA.demo.domain.PostEntity;
import JPA.demo.dto.PostDTO;
import JPA.demo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MainService {
    @Autowired
    private PostRepository postRepository;

    public void insertPost(PostDTO postDTO){
        PostEntity postEntity = new PostEntity();
        postEntity.setMaintext(postDTO.getMaintext());
        postEntity.setTitle(postDTO.getTitle());
        postEntity.setWriter(postDTO.getWriter());
        postRepository.save(postEntity);
    }

    public List<PostDTO> findPost(){
        List<PostEntity> result = postRepository.findAll();
        List<PostDTO> postList = new ArrayList<>();


        for(int i = 0; i<result.size(); i++){
            PostDTO post = new PostDTO();
            post.setIndex_number(result.get(i).getIndex_number());
            post.setTitle(result.get(i).getTitle());
            post.setMaintext(result.get(i).getMaintext());
            post.setWriter(result.get(i).getWriter());
            postList.add(post);
        }
        return postList;
    }
}
