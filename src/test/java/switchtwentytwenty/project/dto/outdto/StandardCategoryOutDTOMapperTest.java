package switchtwentytwenty.project.dto.outdto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.aggregate.category.Category;
import switchtwentytwenty.project.domain.aggregate.category.CategoryFactory;
import switchtwentytwenty.project.domain.share.designation.CategoryDesignation;
import switchtwentytwenty.project.domain.share.designation.Designation;
import switchtwentytwenty.project.domain.share.id.CategoryID;
import switchtwentytwenty.project.dto.toservicedto.StandardCategoryDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class StandardCategoryOutDTOMapperTest {

    @Test
    @DisplayName("toDTO Function: with parentID")
    void toDTO_WithParentID() {
        //arrange
        Designation designation = new CategoryDesignation("Car");
        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());
        CategoryID parentID = new CategoryID(UUID.randomUUID().toString());
        Category standard = CategoryFactory.create(designation, categoryID, parentID);
        StandardCategoryOutDTO result = StandardCategoryOutDTOMapper.toDTO(standard);
        String stringDesignation = designation.toString();
        String stringCategoryID = categoryID.toString();
        String stringParentID = parentID.toString();
        StandardCategoryOutDTO expected = new StandardCategoryOutDTO(stringDesignation, stringCategoryID, stringParentID);
        //act & assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("toDTO Function: without parentID")
    void toDTO_WithoutParentID() {
        //arrange
        Designation designation = new CategoryDesignation("Car");
        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());
        Category standard = CategoryFactory.create(designation, categoryID, null);
        StandardCategoryOutDTO result = StandardCategoryOutDTOMapper.toDTO(standard);
        String stringDesignation = designation.toString();
        String stringCategoryID = categoryID.toString();
        StandardCategoryOutDTO expected = new StandardCategoryOutDTO(stringDesignation, stringCategoryID, null);
        //act & assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("toDTO Function: without parentID")
    void toDTO_WithoutParentI() {
        //arrange
        Designation designation = new CategoryDesignation("Car");
        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());
        Category standard = CategoryFactory.create(designation, categoryID, null);
        String stringDesignation = designation.toString();
        String stringCategoryID = categoryID.toString();
        StandardCategoryOutDTO standardCategoryOutDTO = new StandardCategoryOutDTO(stringDesignation, stringCategoryID, null);
        CategoryOutDTO result = StandardCategoryOutDTOMapper.toOutCategoryDTO(standardCategoryOutDTO);
        //act
        CategoryOutDTO expected = new CategoryOutDTO(standard.getID().toString(), standard.getDesignation().toString(), null);
        // assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("To OutStandardCategoryDTO")
    void toStandardCategoryDTO() {
        //arrange
        List<StandardCategoryDTO> categoryDTOList = new ArrayList<>();
        StandardCategoryDTO dto1 = new StandardCategoryDTO("Food", "001", null);
        StandardCategoryDTO dto2 = new StandardCategoryDTO("Education", "002", null);
        StandardCategoryDTO dto3 = new StandardCategoryDTO("Energy", "003", "002");
        categoryDTOList.add(dto1);
        categoryDTOList.add(dto2);
        categoryDTOList.add(dto3);

        //act
        List<StandardCategoryOutDTO> outCategoryDTOList = StandardCategoryOutDTOMapper.toOutStandardCategoryDTOList(categoryDTOList);
        StandardCategoryOutDTO outDto1 = outCategoryDTOList.get(0);
        StandardCategoryOutDTO outDto2 = outCategoryDTOList.get(1);
        StandardCategoryOutDTO outDto3 = outCategoryDTOList.get(2);

        //assert
        //designation
        assertEquals(outDto1.getDesignation(), dto1.getDesignation());
        assertEquals(outDto2.getDesignation(), dto2.getDesignation());
        assertEquals(outDto3.getDesignation(), dto3.getDesignation());
        //identifier
        assertEquals(outDto1.getId(), dto1.getId());
        assertEquals(outDto2.getId(), dto2.getId());
        assertEquals(outDto3.getId(), dto3.getId());
        //parent identifier
        assertEquals(outDto1.getParentID(), dto1.getParentID());
        assertEquals(outDto2.getParentID(), dto2.getParentID());
        assertEquals(outDto3.getParentID(), dto3.getParentID());
    }

    @Test
    @DisplayName("To Out CategoryDTO")
    void toOutCategoryDTO() {
        //arrange
        List<StandardCategoryOutDTO> standardCategoryDTOList = new ArrayList<>();
        StandardCategoryOutDTO dto1 = new StandardCategoryOutDTO("Food", "001", null);
        StandardCategoryOutDTO dto2 = new StandardCategoryOutDTO("Education", "002", null);
        StandardCategoryOutDTO dto3 = new StandardCategoryOutDTO("Energy", "003", "002");

        standardCategoryDTOList.add(dto1);
        standardCategoryDTOList.add(dto2);
        standardCategoryDTOList.add(dto3);

        //act
        List<CategoryOutDTO> resultList = StandardCategoryOutDTOMapper.toOutCategoryDTOList(standardCategoryDTOList);
        CategoryOutDTO resultDTO1 = resultList.get(0);
        CategoryOutDTO resultDTO2 = resultList.get(1);
        CategoryOutDTO resultDTO3 = resultList.get(2);

        //assert
        //designation
        assertEquals(dto1.getDesignation(), resultDTO1.getDesignation());
        assertEquals(dto2.getDesignation(), resultDTO2.getDesignation());
        assertEquals(dto3.getDesignation(), resultDTO3.getDesignation());
        //identifier
        assertEquals(dto1.getId(), resultDTO1.getId());
        assertEquals(dto2.getId(), resultDTO2.getId());
        assertEquals(dto3.getId(), resultDTO3.getId());
        //parent identifier
        assertEquals(dto1.getParentID(), resultDTO1.getParentID());
        assertEquals(dto2.getParentID(), resultDTO2.getParentID());
        assertEquals(dto3.getParentID(), resultDTO3.getParentID());
    }


}