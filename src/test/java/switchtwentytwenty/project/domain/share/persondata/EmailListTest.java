package switchtwentytwenty.project.domain.share.persondata;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.list.EmailList;
import switchtwentytwenty.project.exception.InvalidEmailException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmailListTest {

    @Test
    @DisplayName("Create email list successfully")
    void createEmailList(){
        //act
        EmailList emailList = new EmailList();
        //assert
        assertNotNull(emailList);
    }

    @Test
    @DisplayName("Add email successfully")
    void addEmail() throws InvalidEmailException {
        //arrange
        EmailList emailList = new EmailList();
        Email email = new Email("newEmail@gmail.com");
        //act
        boolean result = emailList.add(email);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Failure add email: null")
    void failureAddEmail_Null(){
        //arrange
        EmailList emailList = new EmailList();
        //act
        boolean result = emailList.add(null);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Add all emails successfully")
    void addAllEmails() throws InvalidEmailException {
        //arrange
        EmailList emailList = new EmailList();
        Email email = new Email("newEmail@gmail.com");
        Email otherEmail = new Email("awesomeEmail@gmail.com");
        List<Email> list = new ArrayList<>();
        list.add(email);
        list.add(otherEmail);
        //act
        boolean result = emailList.addAll(list);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Failure add all emails: null")
    void failureAddAllEmails_Null(){
        //arrange
        EmailList emailList = new EmailList();
        //act
        boolean result = emailList.addAll(null);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Failure add all emails: empty")
    void failureAddAllEmails_Empty(){
        //arrange
        EmailList emailList = new EmailList();
        List<Email> list = new ArrayList<>();
        //act
        boolean result = emailList.addAll(list);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Same value as")
    void sameValueAs() throws InvalidEmailException {
        //arrange
        EmailList emailList = new EmailList();
        EmailList otherEmailList = new EmailList();
        Email email1 = new Email("newEmail@gmail.com");
        Email email2 = new Email("awesomeEmail@gmail.com");
        Email email3 = new Email("newEmail@gmail.com");
        Email email4 = new Email("awesomeEmail@gmail.com");
        List<Email> list = new ArrayList<>();
        list.add(email1);
        list.add(email2);
        emailList.addAll(list);
        List<Email> otherList = new ArrayList<>();
        otherList.add(email3);
        otherList.add(email4);
        otherEmailList.addAll(otherList);
        //act
        boolean result = emailList.equals(otherEmailList);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Same value as: empty lists")
    void sameValueAs_EmptyLists(){
        //arrange
        EmailList emailList = new EmailList();
        EmailList otherEmailList = new EmailList();
        //act
        boolean result = emailList.equals(otherEmailList);
        //arrange
        assertTrue(result);
    }

    @Test
    @DisplayName("Not same value as: different lists size")
    void notSameValueAs_ListsWithDifferentSizes() throws InvalidEmailException {
        //arrange
        EmailList emailList = new EmailList();
        EmailList otherEmailList = new EmailList();
        Email email = new Email("newEmail@gmail.com");
        Email otherEmail = new Email("awesomeEmail@gmail.com");
        List<Email> list = new ArrayList<>();
        list.add(email);
        list.add(otherEmail);
        emailList.addAll(list);
        //act
        boolean result = emailList.equals(otherEmailList);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Not same value as: different emails")
    void notSameValueAs_DifferentEmails() throws InvalidEmailException {
        //arrange
        EmailList emailList = new EmailList();
        EmailList otherEmailList = new EmailList();
        Email email1 = new Email("newEmail@gmail.com");
        Email email2 = new Email("awesomeEmail@gmail.com");
        Email email3 = new Email("anotherEmail@gmail.com");
        List<Email> list = new ArrayList<>();
        list.add(email1);
        list.add(email2);
        emailList.addAll(list);
        List<Email> otherList = new ArrayList<>();
        otherList.add(email1);
        otherList.add(email3);
        otherEmailList.addAll(otherList);
        //act
        boolean result = emailList.equals(otherEmailList);
        //assert
        assertFalse(result);
    }

    @Test
    void getEmailList_Success() throws InvalidEmailException {
        //arrange
        EmailList emailList = new EmailList();
        Email email1 = new Email("newEmail@gmail.com");
        Email email2 = new Email("awesomeEmail@gmail.com");
        Email email3 = new Email("ole@gmail.com");

        List<Email> list = new ArrayList<>();
        list.add(email1);
        list.add(email2);
        list.add(email3);
        emailList.addAll(list);

        //act
        List<Email> result = emailList.getEmailList();

        //assert

        assertTrue(result.contains(email1));
        assertTrue(result.contains(email2));
        assertTrue(result.contains(email3));

    }

    @Test
    void getEmailList_Unsuccessful() throws InvalidEmailException {
        //arrange
        EmailList emailList = new EmailList();
        Email email1 = new Email("newEmail@gmail.com");
        Email email2 = new Email("awesomeEmail@gmail.com");
        Email email3 = new Email("ole@gmail.com");

        List<Email> list = new ArrayList<>();
        list.add(email1);
        list.add(email2);
        emailList.addAll(list);

        //act
        List<Email> result = emailList.getEmailList();

        //assert

        assertTrue(result.contains(email1));
        assertTrue(result.contains(email2));
        assertFalse(result.contains(email3));
    }

    @Test
    @DisplayName("Identical object")
    void identicalObject() throws InvalidEmailException {
        //arrange
        EmailList emailList = new EmailList();
        Email email1 = new Email("newEmail@gmail.com");
        Email email2 = new Email("awesomeEmail@gmail.com");
        Email email3 = new Email("ole@gmail.com");
        List<Email> list = new ArrayList<>();
        list.add(email1);
        list.add(email2);
        list.add(email3);
        emailList.addAll(list);
        //act
        EmailList identicalEmailList = new EmailList();
        List<Email> identicalList = new ArrayList<>();
        identicalList.add(email3);
        identicalList.add(email1);
        identicalList.add(email2);
        identicalEmailList.addAll(identicalList);
        //assert
        assertEquals(emailList, identicalEmailList);
    }

    @Test
    @DisplayName("Same object")
    void sameObject() throws InvalidEmailException {
        //arrange
        EmailList emailList = new EmailList();
        Email email1 = new Email("newEmail@gmail.com");
        Email email2 = new Email("awesomeEmail@gmail.com");
        Email email3 = new Email("ole@gmail.com");
        List<Email> list = new ArrayList<>();
        list.add(email1);
        list.add(email2);
        list.add(email3);
        emailList.addAll(list);
        //act
        EmailList sameEmailList = emailList;
        //assert
        assertEquals(emailList, sameEmailList);
    }

    @Test
    @DisplayName("Not same object")
    void notSameObject() throws InvalidEmailException {
        //arrange
        EmailList emailList = new EmailList();
        Email email1 = new Email("newEmail@gmail.com");
        Email email2 = new Email("awesomeEmail@gmail.com");
        Email email3 = new Email("ole@gmail.com");
        List<Email> list = new ArrayList<>();
        list.add(email1);
        list.add(email2);
        list.add(email3);
        emailList.addAll(list);
        //act
        BigDecimal bigDecimal = new BigDecimal("100");
        //assert
        assertNotEquals(emailList, bigDecimal);
    }

    @Test
    @DisplayName("Compare with null object")
    void compareWithNullObject() throws InvalidEmailException {
        //arrange
        EmailList emailList = new EmailList();
        Email email1 = new Email("newEmail@gmail.com");
        Email email2 = new Email("awesomeEmail@gmail.com");
        Email email3 = new Email("ole@gmail.com");
        List<Email> list = new ArrayList<>();
        list.add(email1);
        list.add(email2);
        list.add(email3);
        emailList.addAll(list);
        //act
        EmailList otherEmailList = null;
        //assert
        assertNotEquals(emailList, otherEmailList);
    }

    @Test
    @DisplayName("Same hash - 1")
    void sameHashOne() throws InvalidEmailException {
        //arrange
        int hash1;
        int hash2;
        EmailList emailList = new EmailList();
        Email email1 = new Email("newEmail@gmail.com");
        Email email2 = new Email("awesomeEmail@gmail.com");
        Email email3 = new Email("ole@gmail.com");
        List<Email> list = new ArrayList<>();
        list.add(email1);
        list.add(email2);
        list.add(email3);
        emailList.addAll(list);
        EmailList sameEmailList = emailList;
        //act
        hash1 = emailList.hashCode();
        hash2 = sameEmailList.hashCode();
        //assert
        assertEquals(hash1, hash2);
    }

    @Test
    @DisplayName("Same hash - 2")
    void sameHashTwo() throws InvalidEmailException {
        //arrange
        int hash1;
        int hash2;
        EmailList emailList = new EmailList();
        Email email1 = new Email("newEmail@gmail.com");
        Email email2 = new Email("awesomeEmail@gmail.com");
        Email email3 = new Email("ole@gmail.com");
        List<Email> list = new ArrayList<>();
        list.add(email1);
        list.add(email2);
        list.add(email3);
        emailList.addAll(list);

        EmailList identicalEmailList = new EmailList();
        List<Email> identicalList = new ArrayList<>();
        identicalList.add(email1);
        identicalList.add(email2);
        identicalList.add(email3);
        identicalEmailList.addAll(identicalList);
        //act
        hash1 = emailList.hashCode();
        hash2 = identicalEmailList.hashCode();
        //assert
        assertEquals(hash1, hash2);
    }

    @Test
    @DisplayName("Not same hash - 1")
    void notSameHashOne() throws InvalidEmailException {
        //arrange
        int hash1;
        int hash2;
        EmailList emailList = new EmailList();
        Email email1 = new Email("newEmail@gmail.com");
        Email email2 = new Email("awesomeEmail@gmail.com");
        Email email3 = new Email("ole@gmail.com");
        List<Email> list = new ArrayList<>();
        list.add(email1);
        list.add(email2);
        list.add(email3);
        emailList.addAll(list);

        EmailList identicalEmailList = new EmailList();
        List<Email> identicalList = new ArrayList<>();
        identicalList.add(email3);
        identicalList.add(email1);
        identicalList.add(email2);
        identicalEmailList.addAll(identicalList);
        //act
        hash1 = emailList.hashCode();
        hash2 = identicalEmailList.hashCode();
        //assert
        assertNotEquals(hash1, hash2);
    }

    @Test
    @DisplayName("Not same hash - 2")
    void notSameHashTwo() throws InvalidEmailException {
        //arrange
        int hash1;
        int hash2;
        EmailList emailList = new EmailList();
        Email email1 = new Email("newEmail@gmail.com");
        Email email2 = new Email("awesomeEmail@gmail.com");
        Email email3 = new Email("ole@gmail.com");
        List<Email> list = new ArrayList<>();
        list.add(email1);
        list.add(email2);
        list.add(email3);
        emailList.addAll(list);

        EmailList otherEmailList = new EmailList();
        List<Email> otherList = new ArrayList<>();
        otherList.add(email3);
        otherEmailList.addAll(otherList);
        //act
        hash1 = emailList.hashCode();
        hash2 = otherEmailList.hashCode();
        //assert
        assertNotEquals(hash1, hash2);
    }
}
