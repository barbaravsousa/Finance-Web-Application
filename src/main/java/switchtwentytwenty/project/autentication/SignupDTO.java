package switchtwentytwenty.project.autentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Set;

@AllArgsConstructor
public class SignupDTO {

    @Getter
    String username;

    @Getter
    String email;

    @Getter
    String password;

    @Getter
    String familyID;

    @Getter
    Set<String> role;
}
