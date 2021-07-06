package switchtwentytwenty.project.domain.domaindto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import switchtwentytwenty.project.domain.aggregate.ledger.Ledger;
import switchtwentytwenty.project.domain.aggregate.person.Person;

@AllArgsConstructor
public class PersonAndLedgerDomainDTO {

    //Attributes
    @Getter
    private final Person person;
    @Getter
    private final Ledger ledger;
}
