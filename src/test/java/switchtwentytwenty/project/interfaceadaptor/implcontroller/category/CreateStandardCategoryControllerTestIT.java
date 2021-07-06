package switchtwentytwenty.project.interfaceadaptor.implcontroller.category;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.Application;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.ICategoryService;
import switchtwentytwenty.project.applicationservice.irepository.ICategoryRepository;

import switchtwentytwenty.project.dto.indto.CategoryInDTO;
import switchtwentytwenty.project.dto.outdto.StandardCategoryOutDTO;
import switchtwentytwenty.project.exception.DesignationNotPossibleException;

import switchtwentytwenty.project.exception.ElementNotFoundException;
import switchtwentytwenty.project.interfaceadaptor.icontroller.category.ICreateStandardCategoryController;


import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = Application.class)
public class CreateStandardCategoryControllerTestIT {
    @Autowired
    ICategoryRepository categoryRepository;
    @Autowired
    ICategoryService categoryService;
    @Autowired
    ICreateStandardCategoryController controller;

    @BeforeEach
    public void before(){
        categoryRepository.deleteAll();
    }

    @Test
    void createStandardCategory()  {
        //arrange
        String designation = "Abacaxi";
        CategoryInDTO info= new CategoryInDTO();
        info.setDesignation(designation);



        ResponseEntity<Object> result = controller.createStandardCategory(info);
        assertEquals(201,result.getStatusCodeValue());

    }

    @Test
    void createStandardChildCategory() throws DesignationNotPossibleException {
        //arrange
        //create parentCategory
        String designationParent ="food";
        StandardCategoryOutDTO outparent= categoryService.createRootStandardCategory(designationParent);
        String parentID= outparent.getId();

        //create childCategory
        String designationChild = "Abacaxi";
        CategoryInDTO info= new CategoryInDTO();
        info.setDesignation(designationChild);
        info.setParentID(parentID);

        //act
        ResponseEntity<Object> result = controller.createStandardCategory(info);

        //assert
        assertEquals(201,result.getStatusCodeValue());
    }

    @Test
    void createStandardCategoryFailure() throws DesignationNotPossibleException, ElementNotFoundException {
        //arrange
        //create parentCategory
        String designationParent ="Abacaxi";
        StandardCategoryOutDTO outparent= categoryService.createRootStandardCategory(designationParent);
        String parentID= outparent.getId();

        //create childCategory
        String designationChild = "Abacaxi";
        CategoryInDTO info= new CategoryInDTO();
        info.setDesignation(designationChild);
        info.setParentID(parentID);
        categoryService.createChildStandardCategory(designationParent,parentID);

        //act
        ResponseEntity<Object> result = controller.createStandardCategory(info);

        //assert
        assertEquals(400,result.getStatusCodeValue());
    }

}