package switchtwentytwenty.project.domain.domainservice;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import switchtwentytwenty.project.domain.aggregate.ledger.Ledger;
import switchtwentytwenty.project.domain.aggregate.ledger.LedgerFactory;
import switchtwentytwenty.project.domain.aggregate.person.Person;
import switchtwentytwenty.project.domain.aggregate.person.PersonFactory;
import switchtwentytwenty.project.domain.domaindto.PersonAndLedgerDomainDTO;
import switchtwentytwenty.project.dto.todomaindto.PersonVoDTO;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonAndLedgerFactory {

    /**
     * Allows to add a family member and also initialize ledger.
     * @param dto - dto with personal data and ledger id.
     * @return Person and Ledger instances.
     */
    public static PersonAndLedgerDomainDTO create(PersonVoDTO dto) {
        Person person = PersonFactory.create(dto);
        Ledger ledger = LedgerFactory.create(dto.getLedgerID());
        return new PersonAndLedgerDomainDTO(person, ledger);
    }
}
