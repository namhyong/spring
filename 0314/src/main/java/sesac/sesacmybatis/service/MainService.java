package sesac.sesacmybatis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sesac.sesacmybatis.domain.User;
import sesac.sesacmybatis.dto.UserDTO;
import sesac.sesacmybatis.mapper.MainMapper;

import java.util.ArrayList;
import java.util.List;
@Service
public class MainService {

    @Autowired
    private MainMapper mainMapper;


    public List<UserDTO> getUserList(){
        //mainMapper에서 retrieveALL 즉, xml파일의 retrieveAll을 찾아서 해당 쿼리문을 실행(select)
        List<User> result = mainMapper. retrieveAll();
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
    public void addUser(User user) {mainMapper.insertUser(user);}

    public void setUser(UserDTO dto) {
        User user = new User();
        user.setUserId(dto.getUserId());
        user.setPassword(dto.getPassword());
        mainMapper.signin(user);
    }
}
