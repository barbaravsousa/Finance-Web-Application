package switchtwentytwenty.project.datamodel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryJPATest {

    @Test
    void testGetters() {
        //arrange
        String id = "OPI0";
        String designation = "Compras";
        CategoryJPA categoryJPA = new CategoryJPA(id, null, designation,true);
        String Id = "0008";
        String designation1 = "Roupa";
        String famID = "Sousa09";
        CategoryJPA categoryJPA1 = new CategoryJPA(Id, null, designation1, famID, false);
        //act
        boolean result = categoryJPA.isStandard();
        boolean otherResult = categoryJPA1.isStandard();
        //arrange
        assertTrue(result);
        assertFalse(otherResult);
    }

}