package sesac.sesacmybatis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sesac.sesacmybatis.domain.UserEntity;
import sesac.sesacmybatis.dto.UserDTO;
import sesac.sesacmybatis.mapper.MainMapper;
import sesac.sesacmybatis.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MainService {

    @Autowired
    private MainMapper mainMapper;
    @Autowired
    private UserRepository userRepository;


    public List<UserDTO> getUserList(){
        //mainMapper에서 retrieveALL 즉, xml파일의 retrieveAll을 찾아서 해당 쿼리문을 실행(select)
        List<UserEntity> result = userRepository.findAll();
        //가져온 값을 DTO에 담아서 controller에 넘겨줌
        List<UserDTO> users = new ArrayList<>();

        // mainMapper를 통해 받아온 result를 for문 돌려서 user(userDto)에 set하고, 그거를 users라는 arrayList에 add
        for(int i =0; i<result.size(); i++){
            UserDTO user = new UserDTO();
            user.setId(result.get(i).getId());
            user.setName(result.get(i).getName());
            user.setNickname(result.get(i).getNickname());
            user.setNo(i+1);

            users.add(user);
        }
        return users;
    }
    public void addUser(UserEntity user) {userRepository.save(user);} // insert

    public ArrayList<UserDTO> getUserName(String name){
        Optional<UserEntity> user = userRepository.findByName(name);
        ArrayList<UserDTO> userList = new ArrayList<>();

        if(user.isPresent()){
            UserDTO dto = new UserDTO();
            dto.setId(user.get().getId());
            dto.setNo(0);
            dto.setName(user.get().getName());
            dto.setNickname(user.get().getNickname());
            userList.add(dto);
        }
        return userList;
    }


}
