package study.websocket.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import study.websocket.domain.user.UserRepository;
import study.websocket.filter.JwtAuthenticationEntryPoint;

@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@RequiredArgsConstructor
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final UserRepository userRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }
}
