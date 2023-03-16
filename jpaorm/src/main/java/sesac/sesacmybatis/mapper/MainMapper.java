package sesac.sesacmybatis.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sesac.sesacmybatis.domain.Person;
import sesac.sesacmybatis.domain.User;
import sesac.sesacmybatis.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface MainMapper {
//mapper 참고하기: 만들 xml을 참고해서 작업 하겠다.
    List<User> retrieveAll();

    //mapper 참고 안하기: mainmapper interface 안에서 작업을 끝내겠다 즉. @insert()안에 있는 sql문으로 실행하겠다.
    //insertUser(User user)로 user를 받아와서 전달 받은 name과 nickname을 매핑해서 꺼내와서 사용
    //@Insert 어노테이션으로 insert문이다라는 것을 알려줘야 함
@Insert("insert into user(name,nickname) values(#{name},#{nickname})")
    void insertUser(User user);


}
