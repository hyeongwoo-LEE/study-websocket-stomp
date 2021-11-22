package study.websocket.dto.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.websocket.domain.user.User;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSignupDTO {


    private String username;

    private String password;

    public User toEntity(Boolean isFromSocial){

        User user = User.builder()
                .username(username)
                .password(password)
                .build();

        return user;
    }

}
