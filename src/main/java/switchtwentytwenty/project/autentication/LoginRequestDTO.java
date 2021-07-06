package switchtwentytwenty.project.autentication;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class LoginRequestDTO {

    @Getter
    String username;

    @Getter
    String password;
}
