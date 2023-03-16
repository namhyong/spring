package sesac.sesacmybatis.mapper;

import org.apache.ibatis.annotations.*;
import sesac.sesacmybatis.domain.Person;

@Mapper
public interface PersonMapper {
    @Insert("insert into person(id,pw,name) values(#{id},#{pw},#{name})")
    void insertPerson(Person person);

    // limit1을 걸지않으면 list 형태로 받아야 하지만, limit 1 을 걸었기 떄문에 Person 도메인에서 받아올 수 있음
    @Select("select * from person where id =#{id} and pw =#{pw} limit 1")
    Person getPerson(String id, String pw);

    @Update("update person set pw=#{pw}, name=#{name} where id=#{id}")
    void updatePerson(Person person);

    @Delete("delete from person where id=#{id}")
    void deletePerson(String id);
}
