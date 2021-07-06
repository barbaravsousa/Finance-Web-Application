package switchtwentytwenty.project.dto.indto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class FamilyRelationInDTO {

    //Attributes

    @Getter
    @Setter(AccessLevel.PROTECTED)
    private String personEmail;

    @Getter
    @Setter(AccessLevel.PROTECTED)
    private String kinEmail;

    @Getter
    @Setter(AccessLevel.PROTECTED)
    private String relationType;

}
