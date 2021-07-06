package switchtwentytwenty.project.domain.share.familydata;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.exception.InvalidRelationTypeException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RelationTypeTest {

    @Test
    @DisplayName("Same value as other")
    void sameValueAsOther() throws Exception{
        //arrange
        RelationType relationType1 = RelationType.getInstance(Constants.PARENT);
        RelationType relationType2 = RelationType.getInstance(Constants.PARENT);;
        //act
        boolean result = relationType1.equals(relationType2);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Not the same value as other")
    void notSameValueAsOther() throws Exception {
        //arrange
        RelationType relationType1 = RelationType.getInstance(Constants.PARENT);
        RelationType relationType2 = RelationType.getInstance(Constants.GRANDPARENT);
        //act
        boolean result = relationType1.equals(relationType2);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Create a valid RelationType")
    void createAValidRelationType() throws Exception {
        //arrange
        String expected = Constants.GRANDCHILD;
        String result;
        RelationType relationType = RelationType.getInstance(Constants.GRANDPARENT);
        //act
        result = relationType.getOppositeDenomination();
        //assert
        assertEquals(result, expected);
    }

    @Test
    @DisplayName("Create another valid RelationType")
    void createAnotherValidRelationType() throws Exception {
        //arrange
        String expected = Constants.PARENT;
        String result;
        RelationType relationType = RelationType.getInstance(Constants.CHILD);
        //act
        result = relationType.getOppositeDenomination();
        //assert
        assertEquals(result, expected);
    }

    @Test
    @DisplayName("Null relation type name")
    void nullRelationTypeName() {
        //arrange
        String relationTypeName = null;
        //act and assert
        assertThrows(NullPointerException.class, () -> {
            RelationType.getInstance(relationTypeName);
        });
    }

    @Test
    @DisplayName("invalid relation type name")
    void invalidRelationTypeName() {
        //arrange
        String relationTypeName = "mistress";
        //act and assert
        assertThrows(InvalidRelationTypeException.class, () -> {
            RelationType.getInstance(relationTypeName);
        });
    }

    @Test
    @DisplayName("Empty relation type name")
    void emptyRelationTypeName() {
        //arrange
        String relationTypeName = "   ";
        //act and assert
        assertThrows(IllegalArgumentException.class, () -> {
            RelationType.getInstance(relationTypeName);
        });
    }

    @Test
    @DisplayName("Get relation type denomination")
    void getRelationTypeDenomination() throws Exception{
        //arrange
        String expected = Constants.GRANDPARENT;
        String result;
        RelationType relationType = RelationType.getInstance(expected);
        //act
        result = relationType.getDenomination();
        //assert
        assertEquals(result, expected);
    }

    @Test
    @DisplayName("Same object in memory")
    void sameObjectInMemory() throws Exception{
        //arrange
        boolean result;
        RelationType relationType = RelationType.getInstance(Constants.PARENT);
        RelationType sameRelationType = relationType;
        //act
        result = relationType.equals(sameRelationType);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Same object in memory - hash")
    void sameObjectInMemoryHash() throws Exception {
        //arrange
        int hash;
        int sameHash;
        RelationType relationType = RelationType.getInstance(Constants.PARENT);
        RelationType sameRelationType = relationType;
        //act
        hash = relationType.hashCode();
        sameHash = sameRelationType.hashCode();
        //assert
        assertEquals(hash, sameHash);
    }

    @Test
    @DisplayName("Not same object in memory - hash")
    void notSameObjectInMemoryHash() throws Exception {
        //arrange
        int hash;
        int sameHash;
        RelationType relationType = RelationType.getInstance(Constants.PARENT);
        RelationType otherRelationType = RelationType.getInstance(Constants.COUSIN);
        //act
        hash = relationType.hashCode();
        sameHash = otherRelationType.hashCode();
        //assert
        assertNotEquals(hash, sameHash);
    }

    @Test
    @DisplayName("Different kind of object")
    void differentObject() throws Exception {
        //arrange
        boolean result;
        RelationType relationType = RelationType.getInstance(Constants.PARENT);
        MoneyValue moneyValue = new MoneyValue(new BigDecimal(200));
        //act
        result = relationType.equals(moneyValue);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Different kind of object - hash")
    void differentObjectHash() throws Exception{
        //arrange
        int hash;
        int sameHash;
        RelationType relationType = RelationType.getInstance(Constants.PARENT);
        MoneyValue moneyValue = new MoneyValue(new BigDecimal(200));
        //act
        hash = relationType.hashCode();
        sameHash = moneyValue.hashCode();
        //assert
        assertNotEquals(hash, sameHash);
    }
}