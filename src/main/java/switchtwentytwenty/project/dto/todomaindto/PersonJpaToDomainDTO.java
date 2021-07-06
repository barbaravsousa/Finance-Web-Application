package switchtwentytwenty.project.dto.todomaindto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import switchtwentytwenty.project.domain.share.id.*;
import switchtwentytwenty.project.domain.share.list.EmailList;
import switchtwentytwenty.project.domain.share.persondata.BirthDate;
import switchtwentytwenty.project.domain.share.persondata.PersonName;
import switchtwentytwenty.project.domain.share.persondata.TelephoneNumberList;
import switchtwentytwenty.project.domain.share.persondata.VAT;
import switchtwentytwenty.project.domain.share.persondata.address.Address;

@AllArgsConstructor
public class PersonJpaToDomainDTO {

    //Attribute
    @Getter
    private final PersonName name;
    @Getter
    private final BirthDate birthDate;
    @Getter
    private final VAT vat;
    @Getter
    private final Address address;
    @Getter
    private final TelephoneNumberList phoneNumbers;
    @Getter
    private final Email email;
    @Getter
    private final FamilyID familyID;
    @Getter
    private final LedgerID ledgerID;
    @Getter
    private final EmailList otherEmails;
    @Getter
    private final AccountIDList accounts;
}

