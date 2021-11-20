package study.websocket.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import study.websocket.domain.user.User;
import study.websocket.domain.user.UserRepository;
import study.websocket.handler.exception.CustomException;

@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new CustomException("존재하지 않은 아이디입니다."));

        return new PrincipalDetails(user);
    }
}
