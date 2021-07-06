package switchtwentytwenty.project.domain.aggregate.person;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.share.designation.CategoryDesignation;
import switchtwentytwenty.project.domain.share.designation.Designation;
import switchtwentytwenty.project.domain.share.id.*;
import switchtwentytwenty.project.domain.share.list.EmailList;
import switchtwentytwenty.project.domain.share.persondata.*;
import switchtwentytwenty.project.domain.share.persondata.address.Address;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    @DisplayName("Create person successfully")
    void createValidPerson() throws Exception {
        //arrange
        Email id = new Email("email@gmail.com");
        //act
        Person person = new Person(id);
        //assert
        assertNotNull(person);
    }

    @Test
    @DisplayName("Create person successfully: with other attributes")
    void createValidPerson_WithOtherAttributes() throws Exception {
        //arrange
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        Address address = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDate = new BirthDate("1995-03-22");
        Email id = new Email("email@gmail.com");
        PersonName name = new PersonName("Constantino");
        VAT vat = new VAT("123456789");
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        TelephoneNumber telephone = new TelephoneNumber("225658541");
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        telephoneNumberList.add(telephone);

        //act
        Person person = new Person(id);
        person.setName(name);
        person.setVat(vat);
        person.setBirthDate(birthDate);
        person.setTelephoneNumbers(telephoneNumberList);
        person.setAddress(address);
        person.setFamilyID(familyID);
        person.setLedgerID(ledgerID);

        //assert
        assertNotNull(person);
    }

    @Test
    @DisplayName("Create person successfully: with all attributes")
    void createValidPerson_WithAllAttributes() throws Exception {
        //arrange
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        Address address = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDate = new BirthDate("1995-03-22");
        Email id = new Email("email@gmail.com");
        PersonName name = new PersonName("Constantino");
        VAT vat = new VAT("123456789");
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        TelephoneNumber telephone = new TelephoneNumber("225658541");
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        telephoneNumberList.add(telephone);
        Email otherEmail = new Email("other@gmail.com");
        EmailList emailList = new EmailList();
        emailList.add(otherEmail);
        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountIDList accountIDList = new AccountIDList();
        accountIDList.add(accountID);

        //act
        Person person = new Person(id);
        person.setName(name);
        person.setVat(vat);
        person.setBirthDate(birthDate);
        person.setTelephoneNumbers(telephoneNumberList);
        person.setAddress(address);
        person.setFamilyID(familyID);
        person.setLedgerID(ledgerID);
        person.setOtherEmails(emailList);
        person.setAccountIDList(accountIDList);

        //assert
        assertNotNull(person);
    }

    @Test
    @DisplayName("Get person id successfully")
    void getID() throws Exception {
        //arrange
        Email id = new Email("email@gmail.com");
        Person person = new Person(id);
        //act
        ID result = person.getID();
        //assert
        assertEquals(id, result);
    }

    @Test
    void addEmailToProfile() throws Exception {
        //arrange
        Email id = new Email("email@gmail.com");
        Email emailToAdd = new Email("joaquinWork@gmail.com");
        Person person = new Person(id);
        //act
        person.addEmail(emailToAdd);
        boolean result = person.containsEmail(emailToAdd);
        //assert
        assertTrue(result);
    }

    @Test
    void addSeveralEmailsToProfile() throws Exception {
        //arrange
        Email id = new Email("email@gmail.com");
        Email emailToAdd = new Email("joaquinWork@gmail.com");
        Email anotherToAdd = new Email("joaquin2Work@gmail.com");
        Person person = new Person(id);
        //act
        person.addEmail(emailToAdd);
        person.addEmail(anotherToAdd);
        boolean result = person.containsEmail(emailToAdd);
        boolean secondResult = person.containsEmail(emailToAdd);
        //assert
        assertTrue(result);
        assertTrue(secondResult);
    }

    @Test
    void addRepeatedEmailsToProfile() throws Exception {
        //arrange
        Email id = new Email("email@gmail.com");
        Email emailToAdd = new Email("joaquinWork@gmail.com");
        Person person = new Person(id);
        //act
        person.addEmail(emailToAdd);
        //assert
        assertThrows(IllegalArgumentException.class, () -> person.addEmail(emailToAdd));
    }

    @Test
    void deleteEmailFromProfile() throws Exception {
        //arrange
        Email id = new Email("email@gmail.com");
        Email emailToAdd = new Email("joaquinWork@gmail.com");
        Person person = new Person(id);
        //act
        person.addEmail(emailToAdd);
        boolean result = person.removeEmail(emailToAdd);
        //assert
        assertTrue(result);
    }

    @Test
    void deleteEmailThatDoesNotExistOnProfile() throws Exception {
        //arrange
        Email id = new Email("email@gmail.com");
        Email emailToAdd = new Email("joaquinWork@gmail.com");
        Person person = new Person(id);

        //act and assert
        assertThrows(IllegalArgumentException.class, () -> person.removeEmail(emailToAdd));
    }


    @Test
    void addAccount() throws Exception {
        //arrange
        Email id = new Email("email@gmail.com");
        Person person = new Person(id);
        AccountID accountID = new AccountID(UUID.randomUUID());
        //act
        boolean result = person.addAccountID(accountID);
        //assert
        assertTrue(result);
    }

    @Test
    void AddANullAccount() throws Exception {
        //arrange
        Email id = new Email("email@gmail.com");
        Person person = new Person(id);
        //act
        boolean result = person.addAccountID(null);
        //assert
        assertFalse(result);
    }

    @Test
    void ownsAccount() throws Exception {
        //arrange
        Email id = new Email("email@gmail.com");
        Person person = new Person(id);
        AccountID accountID = new AccountID(UUID.randomUUID());
        //act
        boolean result = person.isMyAccount(accountID);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Has same ID")
    void hasSameID() throws Exception {
        //arrange
        Email id = new Email("email@gmail.com");
        Person person = new Person(id);
        //act
        boolean result = person.hasSameID(id);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Doesn't have same ID: null")
    void doesntHaveSameID_null() throws Exception {
        //arrange
        Email id = new Email("email@gmail.com");
        Person person = new Person(id);
        //act - assert
        assertThrows(NullPointerException.class, () -> person.hasSameID(null));
    }

    @Test
    @DisplayName("Has a different ID")
    void doesNotHaveSameID_differentIDs() throws Exception {
        //arrange
        Email id = new Email("email@gmail.com");
        Person person = new Person(id);
        Email id2 = new Email("constantino@gmail.com");
        //act
        boolean result = person.hasSameID(id2);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Persons equals")
    void equalsMethod_BothPersonEquals() throws Exception {
        //arrange
        Email id = new Email("email@gmail.com");
        Person person = new Person(id);
        Person other = new Person(id);
        //act & assert
        assertEquals(person, other);
    }

    @Test
    @DisplayName("Persons equals: same instance")
    void equalsMethod_SameInstance() throws Exception {
        //arrange
        Email id = new Email("email@gmail.com");
        Person person = new Person(id);
        //act & assert
        assertEquals(person, person);
    }

    @Test
    @DisplayName("Persons equals: different types")
    void equalsMethod_DifferentTypes() throws Exception {
        //arrange
        Email id = new Email("email@gmail.com");
        Person person = new Person(id);
        //act & assert
        assertNotEquals(id, person);
    }

    @Test
    @DisplayName("Persons equals: null")
    void equalsMethod_Null() throws Exception {
        //arrange
        Email id = new Email("email@gmail.com");
        Person person = new Person(id);
        //act & assert
        assertNotEquals(null, person);
    }

    @Test
    void sameHashCode() throws Exception {
        //arrange
        Email id = new Email("email@gmail.com");
        Person person = new Person(id);
        Person other = new Person(id);
        int result = person.hashCode();
        int expected = other.hashCode();
        //act & assert
        assertEquals(expected, result);
    }

    @Test
    void differentHashCode() throws Exception {
        //arrange
        Email id = new Email("email@gmail.com");
        Email otherId = new Email("other@gmail.com");
        Person person = new Person(id);
        Person other = new Person(otherId);
        int result = person.hashCode();
        int expected = other.hashCode();
        //act & assert
        assertNotEquals(expected, result);
    }

    @Test
    @DisplayName("Has the same Vat")
    void hasSameVAT() throws Exception {
        //arrange
        Email id = new Email("email@gmail.com");
        VAT vat = new VAT("123456789");
        Person person = new Person(id);
        person.setVat(vat);
        //act
        boolean result = person.hasSameVAT(vat);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Not has the same Vat")
    void doesNotHaveTheSameVAT() throws Exception {
        //arrange
        Email id = new Email("email@gmail.com");
        VAT vat = new VAT("123456789");
        VAT otherVat = new VAT("292684720");
        Person person = new Person(id);
        person.setVat(vat);
        //act
        boolean result = person.hasSameVAT(otherVat);
        //assert
        assertFalse(result);
    }

    @Test
    void sameValueAs_true() throws Exception {
        //arrange
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        Address address = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDate = new BirthDate("1995-03-22");
        Email id = new Email("email@gmail.com");
        PersonName name = new PersonName("Constantino");
        VAT vat = new VAT("123456789");
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        TelephoneNumber telephone = new TelephoneNumber("225658541");
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        telephoneNumberList.add(telephone);
        Email otherEmail = new Email("other@gmail.com");
        EmailList emailList = new EmailList();
        emailList.add(otherEmail);
        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountIDList accountIDList = new AccountIDList();
        accountIDList.add(accountID);
        Person person = new Person(id);
        person.setName(name);
        person.setVat(vat);
        person.setBirthDate(birthDate);
        person.setTelephoneNumbers(telephoneNumberList);
        person.setAddress(address);
        person.setFamilyID(familyID);
        person.setLedgerID(ledgerID);
        person.setOtherEmails(emailList);
        person.setAccountIDList(accountIDList);
        //act
        boolean result = person.sameValueAs(person);
        //assert
        assertTrue(result);
    }
    @Test
    void sameValueAs_falseDifferentvat() throws Exception {
        //arrange
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        Address address = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDate = new BirthDate("1995-03-22");
        Email id = new Email("email@gmail.com");
        PersonName name = new PersonName("Constantino");
        VAT vat = new VAT("123456789");
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        TelephoneNumber telephone = new TelephoneNumber("225658541");
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        telephoneNumberList.add(telephone);
        Email otherEmail = new Email("other@gmail.com");
        EmailList emailList = new EmailList();
        emailList.add(otherEmail);
        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountIDList accountIDList = new AccountIDList();
        accountIDList.add(accountID);
        Person person = new Person(id);
        person.setName(name);
        person.setVat(vat);
        person.setBirthDate(birthDate);
        person.setTelephoneNumbers(telephoneNumberList);
        person.setAddress(address);
        person.setFamilyID(familyID);
        person.setLedgerID(ledgerID);
        person.setOtherEmails(emailList);
        person.setAccountIDList(accountIDList);
        //arrange other person
        Person person2 = new Person (id);
        VAT vat2 = new VAT("135794285");
        person2.setVat(vat2);
        person2.setName(name);
        person2.setBirthDate(birthDate);
        person2.setTelephoneNumbers(telephoneNumberList);
        person2.setAddress(address);
        person2.setFamilyID(familyID);
        person2.setLedgerID(ledgerID);
        person2.setOtherEmails(emailList);
        person2.setAccountIDList(accountIDList);
        //act
        boolean result = person.sameValueAs(person2);
        //assert
        assertFalse(result);
    }

    @Test
    void sameValueAs_falseDifferentBirthDate() throws Exception {
        //arrange
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        Address address = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDate = new BirthDate("1995-03-22");
        Email id = new Email("email@gmail.com");
        PersonName name = new PersonName("Constantino");
        VAT vat = new VAT("123456789");
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        TelephoneNumber telephone = new TelephoneNumber("225658541");
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        telephoneNumberList.add(telephone);
        Email otherEmail = new Email("other@gmail.com");
        EmailList emailList = new EmailList();
        emailList.add(otherEmail);
        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountIDList accountIDList = new AccountIDList();
        accountIDList.add(accountID);
        Person person = new Person(id);
        person.setName(name);
        person.setVat(vat);
        person.setBirthDate(birthDate);
        person.setTelephoneNumbers(telephoneNumberList);
        person.setAddress(address);
        person.setFamilyID(familyID);
        person.setLedgerID(ledgerID);
        person.setOtherEmails(emailList);
        person.setAccountIDList(accountIDList);
        //arrange other person
        Person person2 = new Person (id);
        BirthDate birthDate2 = new BirthDate("1995-03-28");
        person2.setVat(vat);
        person2.setName(name);
        person2.setBirthDate(birthDate2);
        person2.setTelephoneNumbers(telephoneNumberList);
        person2.setAddress(address);
        person2.setFamilyID(familyID);
        person2.setLedgerID(ledgerID);
        person2.setOtherEmails(emailList);
        person2.setAccountIDList(accountIDList);
        //act
        boolean result = person.sameValueAs(person2);
        //assert
        assertFalse(result);
    }

    @Test
    void sameValueAs_False() throws Exception {
        //arrange
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        Address address = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDate = new BirthDate("1995-03-22");
        Email id = new Email("email@gmail.com");
        PersonName name = new PersonName("Constantino");
        VAT vat = new VAT("123456789");
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        TelephoneNumber telephone = new TelephoneNumber("225658541");
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        telephoneNumberList.add(telephone);
        Email otherEmail = new Email("other@gmail.com");
        EmailList emailList = new EmailList();
        emailList.add(otherEmail);
        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountIDList accountIDList = new AccountIDList();
        accountIDList.add(accountID);
        Person person = new Person(id);
        person.setName(name);
        person.setVat(vat);
        person.setBirthDate(birthDate);
        person.setTelephoneNumbers(telephoneNumberList);
        person.setAddress(address);
        person.setFamilyID(familyID);
        person.setLedgerID(ledgerID);
        person.setOtherEmails(emailList);
        person.setAccountIDList(accountIDList);
        Person other = new Person(id);
        //act
        boolean result = person.sameValueAs(other);
        //assert
        assertFalse(result);
    }

    @Test
    void removeEmail() throws Exception {
        //arrange
        Email personID = new Email("email@gmail.com");
        Email emailToAdd = new Email("joaquinWork@gmail.com");
        Email anotherToAdd = new Email("joaquin2Work@gmail.com");
        Person person = new Person(personID);
        //act
        person.addEmail(emailToAdd);
        person.addEmail(anotherToAdd);
        person.removeEmail(anotherToAdd);
        boolean result = person.containsEmail(emailToAdd);
        boolean secondResult = person.containsEmail(anotherToAdd);
        //assert
        assertTrue(result);
        assertFalse(secondResult);
    }

    @Test
    void removeANoneExistentEmail() throws Exception {
        //arrange
        Email personID = new Email("email@gmail.com");
        Email emailToAdd = new Email("joaquinWork@gmail.com");
        Email anotherToAdd = new Email("joaquin2Work@gmail.com");
        Person person = new Person(personID);
        person.addEmail(emailToAdd);
        //assert e act
        assertThrows(IllegalArgumentException.class, () -> {
            person.removeEmail(anotherToAdd);
        });
    }

    @Test
    void equals_true_samePerson() throws Exception {
        //arrange
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        Address address = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDate = new BirthDate("1995-03-22");
        Email id = new Email("email@gmail.com");
        PersonName name = new PersonName("Constantino");
        VAT vat = new VAT("123456789");
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        TelephoneNumber telephone = new TelephoneNumber("225658541");
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        telephoneNumberList.add(telephone);
        Email otherEmail = new Email("other@gmail.com");
        EmailList emailList = new EmailList();
        emailList.add(otherEmail);
        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountIDList accountIDList = new AccountIDList();
        accountIDList.add(accountID);
        Person person = new Person(id);
        person.setName(name);
        person.setVat(vat);
        person.setBirthDate(birthDate);
        person.setTelephoneNumbers(telephoneNumberList);
        person.setAddress(address);
        person.setFamilyID(familyID);
        person.setLedgerID(ledgerID);
        person.setOtherEmails(emailList);
        person.setAccountIDList(accountIDList);
        //act
        boolean result = person.equals(person);
        //assert
        assertTrue(result);
    }

    @Test
    void equals_false_DifferentObject() throws Exception {
        //arrange person
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        Address address = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDate = new BirthDate("1995-03-22");
        Email id = new Email("email@gmail.com");
        PersonName name = new PersonName("Constantino");
        VAT vat = new VAT("123456789");
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        TelephoneNumber telephone = new TelephoneNumber("225658541");
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        telephoneNumberList.add(telephone);
        Email otherEmail = new Email("other@gmail.com");
        EmailList emailList = new EmailList();
        emailList.add(otherEmail);
        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountIDList accountIDList = new AccountIDList();
        accountIDList.add(accountID);
        Person person = new Person(id);
        person.setName(name);
        person.setVat(vat);
        person.setBirthDate(birthDate);
        person.setTelephoneNumbers(telephoneNumberList);
        person.setAddress(address);
        person.setFamilyID(familyID);
        person.setLedgerID(ledgerID);
        person.setOtherEmails(emailList);
        person.setAccountIDList(accountIDList);
        //arrange designation
        Designation designation= new CategoryDesignation("Food");
        //act
        boolean result = person.equals(designation);
        //assert
        assertFalse(result);
    }

    @Test
    void equals_false_null() throws Exception {
        //arrange person
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        Address address = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDate = new BirthDate("1995-03-22");
        Email id = new Email("email@gmail.com");
        PersonName name = new PersonName("Constantino");
        VAT vat = new VAT("123456789");
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        TelephoneNumber telephone = new TelephoneNumber("225658541");
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        telephoneNumberList.add(telephone);
        Email otherEmail = new Email("other@gmail.com");
        EmailList emailList = new EmailList();
        emailList.add(otherEmail);
        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountIDList accountIDList = new AccountIDList();
        accountIDList.add(accountID);
        Person person = new Person(id);
        person.setName(name);
        person.setVat(vat);
        person.setBirthDate(birthDate);
        person.setTelephoneNumbers(telephoneNumberList);
        person.setAddress(address);
        person.setFamilyID(familyID);
        person.setLedgerID(ledgerID);
        person.setOtherEmails(emailList);
        person.setAccountIDList(accountIDList);

        //act
        boolean result = person.equals(null);
        //assert
        assertFalse(result);
    }

    @Test
    void equals_personWithSameEmail() throws Exception {
        //arrange person
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        Address address = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDate = new BirthDate("1995-03-22");
        Email id = new Email("email@gmail.com");
        PersonName name = new PersonName("Constantino");
        VAT vat = new VAT("123456789");
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        TelephoneNumber telephone = new TelephoneNumber("225658541");
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        telephoneNumberList.add(telephone);
        Email otherEmail = new Email("other@gmail.com");
        EmailList emailList = new EmailList();
        emailList.add(otherEmail);
        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountIDList accountIDList = new AccountIDList();
        accountIDList.add(accountID);
        Person person = new Person(id);
        person.setName(name);
        person.setVat(vat);
        person.setBirthDate(birthDate);
        person.setTelephoneNumbers(telephoneNumberList);
        person.setAddress(address);
        person.setFamilyID(familyID);
        person.setLedgerID(ledgerID);
        person.setOtherEmails(emailList);
        person.setAccountIDList(accountIDList);
        //arrange person 2
        Person person2 = new Person(id);
        //act
        boolean result = person.equals(person2);
        //assert
        assertTrue(result);
    }

    @Test
    void equals_personWithDifferentEmail() throws Exception {
        //arrange person
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        Address address = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDate = new BirthDate("1995-03-22");
        Email id = new Email("email@gmail.com");
        PersonName name = new PersonName("Constantino");
        VAT vat = new VAT("123456789");
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        TelephoneNumber telephone = new TelephoneNumber("225658541");
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        telephoneNumberList.add(telephone);
        Email otherEmail = new Email("other@gmail.com");
        EmailList emailList = new EmailList();
        emailList.add(otherEmail);
        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountIDList accountIDList = new AccountIDList();
        accountIDList.add(accountID);
        Person person = new Person(id);
        person.setName(name);
        person.setVat(vat);
        person.setBirthDate(birthDate);
        person.setTelephoneNumbers(telephoneNumberList);
        person.setAddress(address);
        person.setFamilyID(familyID);
        person.setLedgerID(ledgerID);
        person.setOtherEmails(emailList);
        person.setAccountIDList(accountIDList);
        //arrange person 2

        Email id2 = new Email("nosoylamismaersona@gmail.com");
        Person person2 = new Person(id2);
        //act
        boolean result = person.equals(person2);
        //assert
        assertFalse(result);
    }
}
