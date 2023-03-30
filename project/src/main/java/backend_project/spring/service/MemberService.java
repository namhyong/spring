package backend_project.spring.service;

import backend_project.spring.domain.MemberEntity;
import backend_project.spring.domain.Role;
import backend_project.spring.dto.MemberDto;
import backend_project.spring.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MemberService implements UserDetailsService {
    private MemberRepository memberRepository;

    @Transactional
    public void joinUser(MemberDto memberDto){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDto.setPw(passwordEncoder.encode(memberDto.getPw()));
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setPw(memberDto.getPw());
        memberEntity.setName(memberDto.getName());
        memberEntity.setEmail(memberDto.getEmail());
        memberRepository.save(memberEntity);
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("유저 이메일"+ email);
        Optional<MemberEntity> userEntityWrapper = memberRepository.findByEmail(email);

        MemberEntity userEntity = userEntityWrapper.get();
        System.out.println(userEntity.getName());

        List<GrantedAuthority> authorities = new ArrayList<>();
        if(("admin@example.com").equals(email)){
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        }else{
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }
        System.out.println(userEntity.getPw());

        System.out.println(authorities);
        return new User(userEntity.getEmail(),userEntity.getPw(), authorities);

    }


}
