package switchtwentytwenty.project.domain.domaindto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import switchtwentytwenty.project.domain.aggregate.family.Family;
import switchtwentytwenty.project.domain.aggregate.ledger.Ledger;
import switchtwentytwenty.project.domain.aggregate.person.Person;

@AllArgsConstructor
public class FamilyAndAdministratorDomainDTO {

    //Attributes
    @Getter
    private Family family;
    @Getter
    private Person person;
    @Getter
    private Ledger personLedger;
    @Getter
    private Ledger familyLedger;

    //Constructor Method

}
