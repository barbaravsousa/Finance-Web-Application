package switchtwentytwenty.project.interfaceadaptor.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import switchtwentytwenty.project.datamodel.CategoryJPA;
import switchtwentytwenty.project.datamodel.assembler.CategoryDomainDataAssembler;
import switchtwentytwenty.project.domain.aggregate.category.Category;
import switchtwentytwenty.project.domain.aggregate.category.CategoryFactory;
import switchtwentytwenty.project.domain.share.designation.CategoryDesignation;
import switchtwentytwenty.project.domain.share.id.CategoryID;
import switchtwentytwenty.project.dto.todomaindto.CategoryVoDTO;
import switchtwentytwenty.project.exception.ElementNotFoundException;
import switchtwentytwenty.project.interfaceadaptor.repository.jpa.CategoryJPARepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class CategoryRepositoryTest {

    @InjectMocks
    CategoryRepository repository;
    @Mock
    CategoryJPARepository jpaRepository;
    @Mock
    CategoryDomainDataAssembler assembler;


    @Test
    @DisplayName("Exists By ID - False")
    void existsById() {

        when(jpaRepository.existsById(Mockito.anyString())).thenReturn(false);
        boolean result = repository.existsByID(new CategoryID(UUID.randomUUID().toString()));

        assertFalse(result);
    }

    @Test
    @DisplayName("Exists By ID - True")
    void existsByIdTrue() {

        when(jpaRepository.existsById(Mockito.anyString())).thenReturn(true);
        boolean result = repository.existsByID(new CategoryID(UUID.randomUUID().toString()));

        assertTrue(result);
    }

    @Test
    @DisplayName("Find by ID")
    void findByID() throws Exception{
        //arrange
        String id = UUID.randomUUID().toString();
        String parentID = UUID.randomUUID().toString();
        String designation = "category";
        CategoryJPA categoryJPA = new CategoryJPA(id, parentID, designation, true);
        Category expected = CategoryFactory.create(new CategoryDesignation(designation), new CategoryID(id), new CategoryID(parentID));
        CategoryVoDTO dto = new CategoryVoDTO(new CategoryID(id), new CategoryID(parentID), new CategoryDesignation(designation));

        when(jpaRepository.findById(Mockito.anyString())).thenReturn(java.util.Optional.of(categoryJPA));
        when(assembler.toDomain(categoryJPA)).thenReturn(dto);
        //act
        Category result = repository.findByID(new CategoryID(id));
        //assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Find by ID: not found")
    void findByID_NotFound() {
        //arrange
        String id = UUID.randomUUID().toString();
        when(jpaRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
        //act - assert
        assertThrows(ElementNotFoundException.class, () -> repository.findByID(new CategoryID(id)));
    }

    @Test
    @DisplayName("Get List Of Categories With Same Parent: empty")
    void getListOfCategoriesWithSameParent_Empty() {
        //arrange
        String id = UUID.randomUUID().toString();
        Iterable<CategoryJPA> iterable = new ArrayList<>();
        List<Category> expected = new ArrayList<>();
        when(jpaRepository.findCategoryJPAByParentID(Mockito.anyString())).thenReturn(iterable);
        //act
        List<Category> result = repository.getListOfCategoriesWithSameParent(new CategoryID(id));
        //assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Get List Of Categories With Same Parent: one category")
    void getListOfCategoriesWithSameParent_OneCategory() {
        //arrange
        String id = UUID.randomUUID().toString();
        String parentID = UUID.randomUUID().toString();
        String designation = "category";
        CategoryJPA categoryJPA = new CategoryJPA(id, parentID, designation, true);
        List<CategoryJPA> list = new ArrayList<>();
        list.add(categoryJPA);
        CategoryVoDTO dto = new CategoryVoDTO(new CategoryID(id), new CategoryID(parentID), new CategoryDesignation(designation));

        Category expectedCategory = CategoryFactory.create(new CategoryDesignation(designation), new CategoryID(id), new CategoryID(parentID));
        List<Category> expected = new ArrayList<>();
        expected.add(expectedCategory);

        when(jpaRepository.findCategoryJPAByParentID(Mockito.anyString())).thenReturn(list);
        when(assembler.toDomain(categoryJPA)).thenReturn(dto);
        //act
        List<Category> result = repository.getListOfCategoriesWithSameParent(new CategoryID(id));
        //assert
        assertEquals(expected, result);
    }


}
