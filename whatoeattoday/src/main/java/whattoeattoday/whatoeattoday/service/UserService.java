package whattoeattoday.whatoeattoday.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
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
import whattoeattoday.whatoeattoday.jwt.JwtProvider;
import whattoeattoday.whatoeattoday.jwt.TokenResponse;
import whattoeattoday.whatoeattoday.repository.UserRepository;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class UserService  implements UserDetailsService {
    private UserRepository userRepository;
    private final JwtProvider jwtProvider;

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
    public TokenResponse login(LoginRequest loginRequest){
        UsernamePasswordAuthenticationToken authenticationToken = loginRequest.getUsername()
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        return userRepository.findByEmail(username)
                .map(this:: createUserDetails).orElseThrow(()-> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    }
    private UserDetails createUserDetails(UserEntity userEntity){
        return new User(userEntity.getEmail(),userEntity.getPassword(),null);
    }
}
