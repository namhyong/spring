package JPA.demo.service;

import JPA.demo.domain.Post;
import JPA.demo.domain.PostEntity;
import JPA.demo.dto.PostDTO;
import JPA.demo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public PostDTO showDetail(int index_number){
        Optional<PostEntity> post = postRepository.findById(index_number);
        PostDTO postDTO = new PostDTO();
        postDTO.setWriter(post.get().getWriter());
        postDTO.setMaintext(post.get().getMaintext());
        postDTO.setTitle(post.get().getTitle());
        postDTO.setIndex_number(post.get().getIndex_number());
      return postDTO;

    }

    public void editPost(PostDTO postDTO){
        PostEntity post = new PostEntity();
        post.setIndex_number(postDTO.getIndex_number());
        post.setWriter(postDTO.getWriter());
        post.setMaintext(postDTO.getMaintext());
        post.setTitle(postDTO.getTitle());
        postRepository.save(post);
    }

    public void deletePost(PostDTO postDTO){
    postRepository.deleteById(postDTO.getIndex_number());
    }

}
