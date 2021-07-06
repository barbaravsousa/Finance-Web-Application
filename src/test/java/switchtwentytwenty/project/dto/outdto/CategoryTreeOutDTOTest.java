package switchtwentytwenty.project.dto.outdto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTreeOutDTOTest {

    @Test
    @DisplayName("Add child tree to category tree")
    void addChild() {
        CategoryTreeOutDTO tree1 = new CategoryTreeOutDTO("Food");
        CategoryTreeOutDTO treeChild = new CategoryTreeOutDTO("Fruit");
        tree1.addChildTree(treeChild);

        assertNotNull(tree1);
        assertNotNull(treeChild);
    }

    @Test
    void equalsChildTrue() {
        CategoryTreeOutDTO tree1 = new CategoryTreeOutDTO("Food");
        CategoryTreeOutDTO treeChild = new CategoryTreeOutDTO("Fruit");
        CategoryTreeOutDTO treeChild1 = new CategoryTreeOutDTO("Fruit");
        tree1.addChildTree(treeChild);
        tree1.addChildTree(treeChild1);

        boolean equals = treeChild.equals(treeChild1);

        assertTrue(equals);
        assertEquals(treeChild.hashCode(), treeChild1.hashCode());
    }


    @Test
    void equalsChildFalse() {
        CategoryTreeOutDTO tree1 = new CategoryTreeOutDTO("Food");
        CategoryTreeOutDTO treeChild = new CategoryTreeOutDTO("Fruit");
        CategoryTreeOutDTO treeChild1 = new CategoryTreeOutDTO("Veggies");
        tree1.addChildTree(treeChild);
        tree1.addChildTree(treeChild1);

        boolean equals = treeChild.equals(treeChild1);

        assertFalse(equals);
        assertNotEquals(treeChild.hashCode(), treeChild1.hashCode());
    }

    @Test
    void testChildCategoryTree() {
        CategoryTreeOutDTO tree1 = new CategoryTreeOutDTO("Food");
        CategoryTreeOutDTO treeChild = new CategoryTreeOutDTO("Fruit");
        CategoryTreeOutDTO treeChild1 = new CategoryTreeOutDTO("Veggies");
        tree1.addChildTree(treeChild);
        tree1.addChildTree(treeChild1);

        List<CategoryTreeOutDTO> result = tree1.getChildren();
        List<CategoryTreeOutDTO> expected = new ArrayList<>();
        expected.add(treeChild);
        expected.add(treeChild1);

        assertEquals(expected, result);
        assertNotNull(expected);
        assertNotNull(result);
    }

    @Test
    void testEqualsCategoryTree() {
        //arrange
        CategoryTreeOutDTO tree1 = new CategoryTreeOutDTO("Food");
        CategoryTreeOutDTO child1 = new CategoryTreeOutDTO("Fruit");
        tree1.addChildTree(child1);

        CategoryTreeOutDTO tree2 = new CategoryTreeOutDTO("Food");
        CategoryTreeOutDTO child2 = new CategoryTreeOutDTO("Fruit");
        tree2.addChildTree(child2);

        //act
        boolean result = tree1.equals(tree2);

        //assert
        assertTrue(result);
    }

    @Test
    void testNotEqualsCategoryTree() {
        //arrange
        CategoryTreeOutDTO tree1 = new CategoryTreeOutDTO("Food");
        CategoryTreeOutDTO child1 = new CategoryTreeOutDTO("Fruit");
        tree1.addChildTree(child1);

        CategoryTreeOutDTO tree2 = new CategoryTreeOutDTO("Food");
        CategoryTreeOutDTO child2 = new CategoryTreeOutDTO("Legumes");
        tree2.addChildTree(child2);

        //act
        boolean result = tree1.equals(tree2);

        //assert
        assertFalse(result);
    }

    @Test
    void testNotEqualsCategoryTree1() {
        //arrange
        CategoryTreeOutDTO tree1 = new CategoryTreeOutDTO("Food");
        CategoryTreeOutDTO child1 = new CategoryTreeOutDTO("Legumes");
        tree1.addChildTree(child1);

        CategoryTreeOutDTO tree2 = new CategoryTreeOutDTO(null);

        //act
        boolean result = tree1.equals(tree2);

        //assert
        assertFalse(result);
    }

    @Test
    void testNotEquals2() {
        //arrange
        CustomCategoryOutDTO customCategoryDTO = new CustomCategoryOutDTO("Legumes");
        CategoryTreeOutDTO tree = new CategoryTreeOutDTO("Legumes");
        //act
        boolean result = tree.equals(customCategoryDTO);
        //assert
        assertFalse(result);
    }
    @Test
    void testNotEqualsNull() {
        //arrange
        CategoryTreeOutDTO tree = new CategoryTreeOutDTO("Legumes");
        //act
        boolean result = tree.equals(null);
        //assert
        assertFalse(result);
    }

    @Test
    void testToStringCategoryTree() {
        CategoryTreeOutDTO tree1 = new CategoryTreeOutDTO("Food");
        CategoryTreeOutDTO child = new CategoryTreeOutDTO("Fruta");
        tree1.addChildTree(child);
        String expectedTree = "Food[Fruta[]]";
        assertEquals(expectedTree, tree1.toString());
        assertNotNull(tree1.toString());
    }


}
