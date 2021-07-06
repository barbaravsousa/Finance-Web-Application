package switchtwentytwenty.project.domain.share.id;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AccountIDListTest {

    @Test
    @DisplayName("Create accountID list successfully")
    void createEmailList(){
        //act
        AccountIDList accountIDList = new AccountIDList();
        //assert
        assertNotNull(accountIDList);
    }

    @Test
    @DisplayName("Add an account ID successfully")
    void addAccountID(){
        //arrange
        AccountID accountID=new AccountID(UUID.randomUUID());
        AccountIDList accounts= new AccountIDList();
        accounts.add(accountID);
        //act
        boolean result=accounts.isEmpty();
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Add all accountIDList - throw exception")
    void addAllAccountsThrowException(){
        //arrange
        AccountIDList accountIDList = new AccountIDList();

        assertThrows(NullPointerException.class, ()->  accountIDList.addAll(null));
    }

    @Test
    @DisplayName("Add all accountID lists successfully")
    void addAllAccounts(){
        //arrange
        AccountIDList accountIDList1 = new AccountIDList();
        List<AccountID> accountIDList2 = new ArrayList<>();
        AccountID accountID=new AccountID(UUID.randomUUID());
        accountIDList2.add(accountID);
        accountIDList1.addAll(accountIDList2);

        boolean result= accountIDList1.contains(accountID);
        assertTrue(result);
    }

    @Test
    @DisplayName("List does not contain an accountID")
    void containsFalse_EmptyList() {

        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountIDList accounts = new AccountIDList();
        boolean result = accounts.contains(accountID);
        assertFalse(result);
    }

    @Test
    @DisplayName("List does not contain an accountID")
    void containsFalse() {

        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountID otherAccountID = new AccountID(UUID.randomUUID());
        AccountIDList accounts = new AccountIDList();
        accounts.add(accountID);
        boolean result = accounts.contains(otherAccountID);
        assertFalse(result);
    }

    @Test
    @DisplayName("List already contains the accountID")
    void containsTrue() {

        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountIDList accounts = new AccountIDList();
        accounts.add(accountID);
        boolean result = accounts.contains(accountID);
        assertTrue(result);
    }

    @Test
    @DisplayName("Is empty")
    void testIsEmpty() {
        //arrange
        AccountIDList accounts = new AccountIDList();
        //act
        boolean result = accounts.isEmpty();
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Is not empty")
    void testIsNotEmpty() {
        //arrange
        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountIDList accounts = new AccountIDList();
        accounts.add(accountID);
        //act
        boolean result = accounts.isEmpty();
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Equals: Same Instance")
    void testEquals_SameInstance() {
        //arrange
        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountIDList accounts = new AccountIDList();
        accounts.add(accountID);
        //act
        boolean result = accounts.equals(accounts);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Equals: null")
    void testEquals_Null() {
        //arrange
        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountIDList accounts = new AccountIDList();
        accounts.add(accountID);
        //act
        boolean result = accounts.equals(null);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Equals: another type")
    void testEquals_AnotherType() {
        //arrange
        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountIDList accounts = new AccountIDList();
        accounts.add(accountID);
        List<AccountID> other = new ArrayList<>();
        other.add(accountID);
        //act
        boolean result = accounts.equals(other);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Equals: same accounts id")
    void testEquals_SameElements() {
        //arrange
        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountIDList accounts = new AccountIDList();
        accounts.add(accountID);
        AccountIDList otherAccounts = new AccountIDList();
        otherAccounts.add(accountID);
        //act
        boolean result = accounts.equals(otherAccounts);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Equals: not same accounts id")
    void testEquals_NotSameElements() {
        //arrange
        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountID otherAccountID = new AccountID(UUID.randomUUID());
        AccountIDList accounts = new AccountIDList();
        accounts.add(accountID);
        AccountIDList otherAccounts = new AccountIDList();
        otherAccounts.add(otherAccountID);
        //act
        boolean result = accounts.equals(otherAccounts);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Hash Code: same accounts id")
    void testHashCode_SameElements() {
        //arrange
        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountIDList accounts = new AccountIDList();
        accounts.add(accountID);
        AccountIDList otherAccounts = new AccountIDList();
        otherAccounts.add(accountID);
        //act
        int hashCode = accounts.hashCode();
        int otherHashCode = otherAccounts.hashCode();
        //assert
        assertEquals(otherHashCode, hashCode);
    }

    @Test
    @DisplayName("Hash Code: not same accounts id")
    void testHashCode_NotSameElements() {
        //arrange
        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountID otherAccountID = new AccountID(UUID.randomUUID());
        AccountIDList accounts = new AccountIDList();
        accounts.add(accountID);
        AccountIDList otherAccounts = new AccountIDList();
        otherAccounts.add(otherAccountID);
        //act
        int hashCode = accounts.hashCode();
        int otherHashCode = otherAccounts.hashCode();
        //assert
        assertNotEquals(otherHashCode, hashCode);
    }
}