package switchtwentytwenty.project.autentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@AllArgsConstructor
public class JwtResponseDTO extends RepresentationModel<JwtResponseDTO> {

    @Getter
    String jwt;
    @Getter
    Long id;
    @Getter
    String username;
    @Getter
    String email;
    @Getter
    String familyID;
    @Getter
    List<String> roles;
}
