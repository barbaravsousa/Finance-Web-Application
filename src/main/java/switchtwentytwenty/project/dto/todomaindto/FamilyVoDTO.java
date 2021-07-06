package switchtwentytwenty.project.dto.todomaindto;

import lombok.*;
import switchtwentytwenty.project.domain.share.familydata.FamilyName;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.domain.share.id.LedgerID;

@AllArgsConstructor
@NoArgsConstructor
public class FamilyVoDTO {

    //Attribute
    @Getter
    @Setter(AccessLevel.PROTECTED)
    private FamilyID familyID;
    @Getter
    @Setter(AccessLevel.PROTECTED)
    private LedgerID ledgerID;
    @Getter
    @Setter(AccessLevel.PROTECTED)
    private Email administratorID;
    @Getter
    @Setter(AccessLevel.PROTECTED)
    private FamilyName name;

}
