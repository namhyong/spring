package backend_project.spring.config;

import backend_project.spring.service.MainService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
    public class SecurityConfig extends webSecurityConfigurerAdapter{
        private MainService mainService;
    }
}
