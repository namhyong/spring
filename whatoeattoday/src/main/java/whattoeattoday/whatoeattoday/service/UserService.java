package whattoeattoday.whatoeattoday.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import whattoeattoday.whatoeattoday.domain.UserEntity;
import whattoeattoday.whatoeattoday.dto.Response;
import whattoeattoday.whatoeattoday.dto.UserDto;
import whattoeattoday.whatoeattoday.repository.UserRepository;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class UserService  {
    private UserRepository userRepository;
    @Transactional
    public Response joinUser(UserDto userDto){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userDto.getName());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPassword(userDto.getPassword());
        userRepository.save(userEntity);
        return Response.of(userEntity);
    }
}
