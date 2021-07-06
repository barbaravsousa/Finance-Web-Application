package switchtwentytwenty.project.domain.aggregate.account;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.share.designation.AccountDesignation;
import switchtwentytwenty.project.domain.share.designation.Designation;
import switchtwentytwenty.project.domain.share.id.AccountID;

import java.math.BigDecimal;
import java.util.UUID;

public class RootAccountTest {


    @Test
    @DisplayName("Same object")
    void sameObject() {
        //arrange
        AccountID accountID = new AccountID(UUID.randomUUID());
        Designation designation = new AccountDesignation("My root account");
        RootAccount account = new RootAccount(accountID, designation);
        //act
        RootAccount sameAccount = account;
        //assert
        assertEquals(account, sameAccount);
    }

    @Test
    @DisplayName("Identical object")
    void identicalObject() {
        //arrange
        AccountID accountID = new AccountID(UUID.randomUUID());
        Designation designation = new AccountDesignation("My root account");
        RootAccount account = new RootAccount(accountID, designation);
        //act
        RootAccount sameAccount = new RootAccount(accountID, designation);
        //assert
        assertEquals(account, sameAccount);
    }

    @Test
    @DisplayName("Null object")
    void nullObject() {
        //arrange
        AccountID accountID = new AccountID(UUID.randomUUID());
        Designation designation = new AccountDesignation("My root account");
        RootAccount account = new RootAccount(accountID, designation);
        //act
        RootAccount nullAccount = null;
        //assert
        assertNotEquals(account, nullAccount);
    }

    @Test
    @DisplayName("Different object")
    void differentObject() {
        //arrange
        AccountID accountID = new AccountID(UUID.randomUUID());
        Designation designation = new AccountDesignation("My root account");
        RootAccount account = new RootAccount(accountID, designation);
        //act
        AccountID differentAccountID = new AccountID(UUID.randomUUID());
        RootAccount differentAccount = new RootAccount(differentAccountID, designation);
        //assert
        assertNotEquals(account, differentAccount);

    }

    @Test
    @DisplayName("Different class")
    void differentClass() {
        //arrange
        AccountID accountID = new AccountID(UUID.randomUUID());
        Designation designation = new AccountDesignation("My root account");
        RootAccount account = new RootAccount(accountID, designation);
        //act
        BigDecimal bigDecimal = BigDecimal.ONE;
        //assert
        assertNotEquals(account, bigDecimal);
    }

    @Test
    @DisplayName("Same hashCode")
    void sameHashCode() {
        //arrange
        int hash1;
        int hash2;
        AccountID accountID = new AccountID(UUID.randomUUID());
        Designation designation = new AccountDesignation("My root account");
        RootAccount account = new RootAccount(accountID, designation);
        //act
        RootAccount sameAccount = new RootAccount(accountID, designation);
        hash1 = account.hashCode();
        hash2 = sameAccount.hashCode();
        //assert
        assertEquals(hash1, hash2);
    }

    @Test
    @DisplayName("Different hashCode")
    void differentHashCode() {
        //arrange
        int hash1;
        int hash2;
        AccountID accountID = new AccountID(UUID.randomUUID());
        Designation designation = new AccountDesignation("My root account");
        RootAccount account = new RootAccount(accountID, designation);
        //act
        AccountID differentAccountID = new AccountID(UUID.randomUUID());
        RootAccount differentAccount = new RootAccount(differentAccountID, designation);
        hash1 = account.hashCode();
        hash2 = differentAccount.hashCode();
        //assert
        assertNotEquals(hash1, hash2);
    }
}
