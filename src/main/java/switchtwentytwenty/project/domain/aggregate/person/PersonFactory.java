package switchtwentytwenty.project.domain.aggregate.person;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.domain.share.id.LedgerID;
import switchtwentytwenty.project.domain.share.persondata.BirthDate;
import switchtwentytwenty.project.domain.share.persondata.PersonName;
import switchtwentytwenty.project.domain.share.persondata.TelephoneNumberList;
import switchtwentytwenty.project.domain.share.persondata.VAT;
import switchtwentytwenty.project.domain.share.persondata.address.Address;
import switchtwentytwenty.project.dto.todomaindto.PersonJpaToDomainDTO;
import switchtwentytwenty.project.dto.todomaindto.PersonVoDTO;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonFactory {

    /**
     * Create person instance.
     * @param dto - dto with person related value objects.
     * @return The person instance.
     */
    public static Person create(PersonVoDTO dto) {
        return create(dto.getEmail(), dto.getName(), dto.getVat(), dto.getBirthDate(), dto.getPhoneNumbers(), dto.getAddress(),
                dto.getFamilyID(), dto.getLedgerID());
    }

    /**
     * Create person instance.
     * @param dto - dto with person related value objects.
     * @return The person instance.
     */
    public static Person create(PersonJpaToDomainDTO dto) {
        Person person = create(dto.getEmail(), dto.getName(), dto.getVat(), dto.getBirthDate(), dto.getPhoneNumbers(), dto.getAddress(),
                dto.getFamilyID(), dto.getLedgerID());
        person.setOtherEmails(dto.getOtherEmails());
        person.setAccountIDList(dto.getAccounts());
        Objects.requireNonNull(person.getOtherEmails());
        Objects.requireNonNull(person.getAccountIDList());
        return person;
    }

    /**
     * Create person instance with basic attributes.
     * @param email - person email.
     * @param personName - person name.
     * @param vat - person vat.
     * @param birthDate - person birth date.
     * @param telephoneNumberList - person telephone number list.
     * @param address - person address.
     * @param familyID - person family id.
     * @param ledgerID - person ledger id.
     * @return The person instance.
     */
    private static Person create(Email email, PersonName personName, VAT vat, BirthDate birthDate, TelephoneNumberList telephoneNumberList,
            Address address, FamilyID familyID, LedgerID ledgerID) {
        Person person = new Person(email);
        person.setName(personName);
        person.setVat(vat);
        person.setBirthDate(birthDate);
        person.setTelephoneNumbers(telephoneNumberList);
        person.setAddress(address);
        person.setFamilyID(familyID);
        person.setLedgerID(ledgerID);
        Objects.requireNonNull(person.getName());
        Objects.requireNonNull(person.getBirthDate());
        Objects.requireNonNull(person.getVat());
        Objects.requireNonNull(person.getAddress());
        Objects.requireNonNull(person.getFamilyID());
        Objects.requireNonNull(person.getLedgerID());
        return person;
    }
}
