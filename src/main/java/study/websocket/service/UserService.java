package study.websocket.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.websocket.domain.user.User;
import study.websocket.domain.user.UserRepository;
import study.websocket.dto.user.UserSignupDTO;
import study.websocket.handler.exception.CustomException;

@Transactional
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;


    /**
     * 아이디 중복확인
     */
    @Transactional(readOnly = true)
    public Boolean isDuplicateNickname(String username) {

        if(userRepository.findByUsername(username).isPresent()){
            return false;
        }else{
            return true;
        }
    }

    /**
     * 회원가입
     */
    @Transactional
    public User join(UserSignupDTO userSignupDTO){

        if(userRepository.findByUsername(userSignupDTO.getUsername()).isPresent()){
            throw new CustomException("이미 존재하는 아이디입니다.");
        }

        User user = userRepository.save(userSignupDTO.toEntity(false));

        return user;
    }





}
