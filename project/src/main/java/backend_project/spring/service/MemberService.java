package backend_project.spring.service;

import backend_project.spring.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberService implements UserDetailsService {
    private MemberRepository mainRepository;
}
