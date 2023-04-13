package whattoeattoday.whatoeattoday.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import whattoeattoday.whatoeattoday.domain.UserEntity;
import whattoeattoday.whatoeattoday.dto.LoginRequest;
import whattoeattoday.whatoeattoday.dto.Response;
import whattoeattoday.whatoeattoday.dto.UserDto;
import whattoeattoday.whatoeattoday.exception.BadRequestException;
import whattoeattoday.whatoeattoday.jwt.UserDeatils;
import whattoeattoday.whatoeattoday.repository.UserRepository;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class UserService  implements UserDetailsService {
    private UserRepository userRepository;

    @Transactional
    public Response joinUser(UserDto userDto){
        boolean iseExist = userRepository.existsByEmail(userDto.getEmail());
        if(iseExist) throw new BadRequestException("이미 존재하는 이메일 입니다.");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userDto.getName());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPassword(userDto.getPassword());
        userRepository.save(userEntity);
        return Response.of(userEntity);
    }
    public Response login(LoginRequest loginRequest){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        UserEntity userEntity = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(()-> new BadRequestException("아이디 혹은 비밀번호를 확인하세요"));
        boolean matches = passwordEncoder.matches(loginRequest.getPassword(),userEntity.getPassword());
        if(!matches) throw new BadRequestException("아이디 혹은 비밀번호를 확인하세요");
        return Response.of(userEntity);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        UserEntity userEntity = userRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
        return new UserDeatils(userEntity);
    }
}
