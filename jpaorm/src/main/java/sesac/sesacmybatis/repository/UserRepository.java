package sesac.sesacmybatis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sesac.sesacmybatis.domain.UserEntity;

import java.util.Optional;

//JPA를 상속받는 repository를 만들고 어떤 Entity에서 사용할지 <뒤에 적고, pk의 형태가 어떤 형태인지 적으면 된다>
//extends JpaRepository<사용할 entity,pk 형태>
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    //optinal: null일수도 있는 객체를 감싸는 클래스(null 여부를 확인 할수 있기 때문에 null exception오류를 예방하기 위해서 사용)
    //UserEntity를 가져올때 user.get()형태로 가져와야한다
    Optional<UserEntity> findByName(String name);
    // select ~ where name = #{name} 형태로 알아서 실행
//    Optional<User Entity> findById(String id);
//    // select ~ where id = #{id} 형태로 알아서 실행
//    Optional<UserEntity> findByIdName(String id, String name);
//    //select ~ where name = #{name} and id = #{id} 형태로 알아서 실행
//
//    boolean existByName(String name);
    //받아온 이름으로 된 데이터가 있는지 자동으로 true false로 반환 해줌

}
