package whattoeattoday.whatoeattoday.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import whattoeattoday.whatoeattoday.repository.PersonRepository;

public class PersonService implements UserDetailsService {
    private PersonRepository personRepository;

}
