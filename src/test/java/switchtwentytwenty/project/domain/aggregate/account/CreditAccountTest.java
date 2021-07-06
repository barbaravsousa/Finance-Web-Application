package switchtwentytwenty.project.domain.aggregate.account;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.designation.AccountDesignation;
import switchtwentytwenty.project.domain.share.id.AccountID;
import java.math.BigDecimal;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CreditAccountTest {

    @Test
    @DisplayName("Same Object")
    void sameObject() {
        //arrange
        AccountID id = new AccountID(UUID.randomUUID());
        AccountDesignation designation = new AccountDesignation("My Account");
        CreditAccount account = new CreditAccount(id, designation);
        //act
        CreditAccount sameAccount = account;
        //assert
        assertEquals(account, sameAccount);
    }

    @Test
    @DisplayName("Identical Object")
    void identicalObject() {
        //arrange
        AccountID id = new AccountID(UUID.randomUUID());
        AccountDesignation designation = new AccountDesignation("My Account");
        CreditAccount account = new CreditAccount(id, designation);
        //act
        CreditAccount sameAccount = new CreditAccount(id, designation);
        //assert
        assertEquals(account, sameAccount);
    }

    @Test
    @DisplayName("Null Object")
    void nullObject() {
        //arrange
        AccountID id = new AccountID(UUID.randomUUID());
        AccountDesignation designation = new AccountDesignation("My Account");
        CreditAccount account = new CreditAccount(id, designation);
        //act
        CreditAccount nullAccount = null;
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
        CreditAccount account = new CreditAccount(id, designation);
        //act
        CreditAccount differentAccount = new CreditAccount(differentID, differentDesignation);
        //assert
        assertNotEquals(account, differentAccount);
    }

    @Test
    @DisplayName("Different Class")
    void differentClass() {
        //arrange
        AccountID id = new AccountID(UUID.randomUUID());
        AccountDesignation designation = new AccountDesignation("My Account");
        CreditAccount account = new CreditAccount(id, designation);
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
        CreditAccount account = new CreditAccount(id, designation);
        CreditAccount sameAccount = new CreditAccount(id, designation);
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
        CreditAccount account = new CreditAccount(id, designation);
        CreditAccount differentAccount = new CreditAccount(differentID, differentDesignation);
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
        CreditAccount account = new CreditAccount(id, designation);
        //act
        CreditAccount sameAccount = new CreditAccount(id, designation);
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
        CreditAccount account = new CreditAccount(id, designation);
        //act
        CreditAccount differentAccount = new CreditAccount(id, differentDesignation);
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
        CreditAccount account = new CreditAccount(id, designation);
        //act
        CreditAccount differentAccount = new CreditAccount(differentID, differentDesignation);
        result = account.hasSameID(differentAccount.getID());
        //assert
        assertFalse(result);
    }


    @Test
    @DisplayName("Get account type: credit account")
    void creditAccountD() {
        //arrange

        AccountID id = new AccountID(UUID.randomUUID());
        AccountDesignation designation = new AccountDesignation("My Account");
        CreditAccount account = new CreditAccount(id, designation);
        //act
        String expected = Constants.CREDIT_ACCOUNT_TYPE;

        String result = account.getAccountType();
        //assert
        assertEquals(expected, result);
    }


    @Test
    void getAccountTypeCreditAccount() {
        //arrange
        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountDesignation designation = new AccountDesignation("my account");
        Account account = new CreditAccount(accountID,designation);
        String expected = Constants.CREDIT_ACCOUNT_TYPE;
        //act
        String result = account.getAccountType();
        //assert
        assertEquals(expected,result);
    }
}
