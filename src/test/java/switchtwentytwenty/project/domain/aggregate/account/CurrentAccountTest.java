package switchtwentytwenty.project.domain.aggregate.account;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.designation.AccountDesignation;
import switchtwentytwenty.project.domain.share.id.AccountID;

import java.math.BigDecimal;
import java.util.UUID;

public class CurrentAccountTest {

    @Test
    @DisplayName("Same Object")
    void sameObject() {
        //arrange
        AccountID id = new AccountID(UUID.randomUUID());
        AccountDesignation designation = new AccountDesignation("My Account");
        CurrentAccount account = new CurrentAccount(id, designation);
        //act
        CurrentAccount sameAccount = account;
        //assert
        assertEquals(account, sameAccount);
    }

    @Test
    @DisplayName("Identical Object")
    void identicalObject() {
        //arrange
        AccountID id = new AccountID(UUID.randomUUID());
        AccountDesignation designation = new AccountDesignation("My Account");
        CurrentAccount account = new CurrentAccount(id, designation);
        //act
        CurrentAccount sameAccount = new CurrentAccount(id, designation);
        //assert
        assertEquals(account, sameAccount);
    }

    @Test
    @DisplayName("Null Object")
    void nullObject() {
        //arrange
        AccountID id = new AccountID(UUID.randomUUID());
        AccountDesignation designation = new AccountDesignation("My Account");
        CurrentAccount account = new CurrentAccount(id, designation);
        //act
        CurrentAccount nullAccount = null;
        //assert
        assertNotEquals(account, nullAccount);
    }

    @Test
    @DisplayName("Different Object")
    void differentObject() {
        //arrange
        AccountID id = new AccountID(UUID.randomUUID());
        AccountID differentID = new AccountID(UUID.randomUUID());
        AccountDesignation designation = new AccountDesignation("My Account");
        AccountDesignation differentDesignation = new AccountDesignation("Other Account");
        CurrentAccount account = new CurrentAccount(id, designation);
        //act
        CurrentAccount differentAccount = new CurrentAccount(differentID, differentDesignation);
        //assert
        assertNotEquals(account, differentAccount);
    }

    @Test
    @DisplayName("Different Class")
    void differentClass() {
        //arrange
        AccountID id = new AccountID(UUID.randomUUID());
        AccountDesignation designation = new AccountDesignation("My Account");
        CurrentAccount account = new CurrentAccount(id, designation);
        //act
        BigDecimal bigDecimal = BigDecimal.ONE;
        //assert
        assertNotEquals(account, bigDecimal);
    }

    @Test
    @DisplayName("Same Hash Code")
    void sameHashCode() {
        //arrange
        int hash1;
        int hash2;
        AccountID id = new AccountID(UUID.randomUUID());
        AccountDesignation designation = new AccountDesignation("My Account");
        CurrentAccount account = new CurrentAccount(id, designation);
        CurrentAccount sameAccount = new CurrentAccount(id, designation);
        //act
        hash1 = account.hashCode();
        hash2 = sameAccount.hashCode();
        //assert
        assertEquals(hash1, hash2);
    }

    @Test
    @DisplayName("Not Same Hash Code")
    void notSameHashCode() {
        //arrange
        int hash1;
        int hash2;
        AccountID id = new AccountID(UUID.randomUUID());
        AccountID differentID = new AccountID(UUID.randomUUID());
        AccountDesignation designation = new AccountDesignation("My Account");
        AccountDesignation differentDesignation = new AccountDesignation("Other Account");
        CurrentAccount account = new CurrentAccount(id, designation);
        CurrentAccount differentAccount = new CurrentAccount(differentID, differentDesignation);
        //act
        hash1 = account.hashCode();
        hash2 = differentAccount.hashCode();
        //assert
        assertNotEquals(hash1, hash2);
    }

    @Test
    @DisplayName("As Same Value")
    void asSameValue() {
        //arrange
        boolean result;
        AccountID id = new AccountID(UUID.randomUUID());
        AccountDesignation designation = new AccountDesignation("My Account");
        CurrentAccount account = new CurrentAccount(id, designation);
        //act
        CurrentAccount sameAccount = new CurrentAccount(id, designation);
        result = account.sameValueAs(sameAccount);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Not same values")
    void notSameValues() {
        //arrange
        boolean result;
        AccountID id = new AccountID(UUID.randomUUID());
        AccountDesignation designation = new AccountDesignation("My Account");
        AccountDesignation differentDesignation = new AccountDesignation("Other Account");
        CurrentAccount account = new CurrentAccount(id, designation);
        //act
        CurrentAccount differentAccount = new CurrentAccount(id, differentDesignation);
        result = account.sameValueAs(differentAccount);
        //assert
        assertFalse(result);
        assertTrue(account.hasSameID(differentAccount.getID()));
    }

    @Test
    @DisplayName("Not Same ID")
    void notSameID() {
        //arrange
        boolean result;
        AccountID id = new AccountID(UUID.randomUUID());
        AccountID differentID = new AccountID(UUID.randomUUID());
        AccountDesignation designation = new AccountDesignation("My Account");
        AccountDesignation differentDesignation = new AccountDesignation("Other Account");
        CurrentAccount account = new CurrentAccount(id, designation);
        //act
        CurrentAccount differentAccount = new CurrentAccount(differentID, differentDesignation);
        result = account.hasSameID(differentAccount.getID());
        //assert
        assertFalse(result);
    }

    @Test
    void getAccountTypeCurrentAccount(){
        //arrange
        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountDesignation designation = new AccountDesignation("my account");
        Account account = new CurrentAccount(accountID,designation);
        String expected = Constants.CURRENT_ACCOUNT_TYPE;
        //act
        String result = account.getAccountType();
        //assert
        assertEquals(expected,result);
    }
}
