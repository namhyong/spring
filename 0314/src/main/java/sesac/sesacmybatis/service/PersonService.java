package sesac.sesacmybatis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sesac.sesacmybatis.domain.Person;
import sesac.sesacmybatis.dto.PersonDTO;
import sesac.sesacmybatis.mapper.PersonMapper;

@Service
public class PersonService {
    @Autowired
    PersonMapper personMapper;
    public void insertPerson(PersonDTO dto) {
        Person person = new Person();
        person.setId(dto.getId());
        person.setPw(dto.getPw());
        person.setName(dto.getName());
        personMapper.insertPerson(person);
    }

    public PersonDTO getPerson(PersonDTO dto){
        //mapper에서 찾은 값을 Person 도메인에 담기
        Person person = personMapper.getPerson(dto.getId(), dto.getPw());
        //찾은 값이 없다면 null을 retrun(controller로)
        if( person== null) return null;
        //info라는 DTO를 만들어서 도메인에 담긴 것을 DTO에 setter하기
        PersonDTO info = new PersonDTO();
        info.setId(person.getId());
        info.setPw(person.getPw());
        info.setName(person.getName());
        info.setNickname(person.getId() + person.getName());
        //찾은 값이 있다면 DTO내용이 담겨있는 info를 retrun(controller로)
        return info;
    }

    public void udatePerson(PersonDTO personDTO){
    Person person = new Person();
    person.setId(personDTO.getId());
    person.setPw(personDTO.getPw());
    person.setName(personDTO.getName());
    personMapper.updatePerson(person);
    }

    public  void deletePerson(PersonDTO personDto){
        personMapper.deletePerson(personDto.getId());
    }
}
