package switchtwentytwenty.project.interfaceadaptor.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import switchtwentytwenty.project.applicationservice.irepository.ICategoryRepository;
import switchtwentytwenty.project.datamodel.CategoryJPA;
import switchtwentytwenty.project.datamodel.assembler.CategoryDomainDataAssembler;
import switchtwentytwenty.project.domain.aggregate.category.Category;
import switchtwentytwenty.project.domain.aggregate.category.CategoryFactory;
import switchtwentytwenty.project.domain.share.designation.Designation;
import switchtwentytwenty.project.domain.share.id.CategoryID;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.dto.todomaindto.CategoryVoDTO;
import switchtwentytwenty.project.exception.ElementNotFoundException;
import switchtwentytwenty.project.interfaceadaptor.repository.jpa.CategoryJPARepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepository implements ICategoryRepository {

    @Autowired
    CategoryJPARepository repository;
    @Autowired
    CategoryDomainDataAssembler assembler;


    /**
     * Checks if a category ID exists in the system.
     *
     * @param categoryID of the category
     * @return false if the category doesn't exists in the system
     */
    public boolean existsByID(CategoryID categoryID) {
        return this.repository.existsById(categoryID.toString());
    }


    /**
     * Finds a Category JPA given a ID and checks if the category exists in the database.
     *
     * @param id of the category we are looking
     * @return a object Category
     * @throws ElementNotFoundException is thrown in case the id is not found
     */
    @Transactional
    public Category findByID(CategoryID id) throws ElementNotFoundException {
        Optional<CategoryJPA> optionalCategoryJPA = this.repository.findById(id.toString());
        if (optionalCategoryJPA.isPresent()) {
            CategoryJPA categoryJPA = optionalCategoryJPA.get();
            CategoryVoDTO categoryVoDTO = assembler.toDomain(categoryJPA);
            return CategoryFactory.create(categoryVoDTO);
        }
        throw new ElementNotFoundException("Category not found");
    }

    /**
     * Transforms a Category in Category JPA and adds them to the Category JPA repository.
     *
     * @param category object
     */
    public Category save(Category category) {
        CategoryJPA categoryJPA = assembler.toData(category);
        CategoryJPA responseCategoryJPA = this.repository.save(categoryJPA);
        CategoryVoDTO responseCategoryVoDTO = assembler.toDomain(responseCategoryJPA);
        return CategoryFactory.create(responseCategoryVoDTO);
    }

    /**
     * Checks the list of categories to see if a certain root Category designation exists.
     *
     * @param designation of a category
     * @return true if the designations exists
     */
    @Transactional
    public boolean containsRootDesignation(Designation designation) {
        Iterable<CategoryJPA> categories = repository.findCategoryJPAByDesignation(designation.toString());
        return categories.iterator().hasNext();
    }


    /**
     * Checks if a category with the same parent has the same designation.
     *
     * @param designation of a given category
     * @param parentID    of the category
     * @return true if the designation already exists
     */
    @Transactional
    public boolean containsDesignationWithSameParent(CategoryID parentID,Designation designation) {
        Iterable<CategoryJPA> categories = repository.findCategoryJPAByParentIDWithDesignation(parentID.toString(),designation.toString());

        for (CategoryJPA categoryJPA : categories) {
            if (categoryJPA.getDesignation().equals(designation.toString())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Iterates over the list of standard categories JPA in the database, turns them to a domain object and adds them to a new list.
     *
     * @return a list with all the standard categories.
     */
    @Transactional
    public List<Category> getStandardCategories() {
        Iterable<CategoryJPA> categories = repository.findCategoryJPAStandard();
        List<Category> standardCategories = new ArrayList<>();

        for (CategoryJPA categoryJPA : categories) {
            CategoryVoDTO categoryVoDTO = assembler.toDomain(categoryJPA);
            Category category = CategoryFactory.create(categoryVoDTO);
            standardCategories.add(category);
        }
        return standardCategories;
    }

    /**
     * Get list of the categories on the family’s category tree.
     *
     * @param familyID - family identifier
     * @return list of categories
     */
    @Transactional
    public List<Category> getListOfFamilyCategories(FamilyID familyID) {
        Iterable<CategoryJPA> iterable = repository.findByFamilyID(familyID.toString());
        List<Category> categoryList = new ArrayList<>();

        for (CategoryJPA categoryJPA : iterable) {
            CategoryVoDTO categoryVoDTO = assembler.toDomain(categoryJPA);
            Category category = CategoryFactory.create(categoryVoDTO);
            categoryList.add(category);
        }
        return categoryList;
    }

    /**
     * Iterates over a list of Category JPA's that have the same parent ID, transforms the JPA into a domain
     * object and adds them to a new list.
     *
     * @param parentID of the category
     * @return a list of categories
     */
    public List<Category> getListOfStandardCategoriesWithSameParent(CategoryID parentID) {
        Iterable<CategoryJPA> iterable = repository.findCategoryJPAStandardByParentID(parentID.toString());
        List<Category> categories = new ArrayList<>();

        for (CategoryJPA categoryJPA : iterable) {
            CategoryVoDTO categoryVoDTO = assembler.toDomain(categoryJPA);
            Category category = CategoryFactory.create(categoryVoDTO);
            categories.add(category);
        }
        return categories;
    }

    public List<Category> getListOfCategoriesWithSameParent(CategoryID parentID) {
        Iterable<CategoryJPA> iterable = repository.findCategoryJPAByParentID(parentID.toString());
        List<Category> categories = new ArrayList<>();

        for (CategoryJPA categoryJPA : iterable) {
            CategoryVoDTO categoryVoDTO = assembler.toDomain(categoryJPA);
            Category category = CategoryFactory.create(categoryVoDTO);
            categories.add(category);
        }
        return categories;
    }


    /**
     * Delete all data from repository
     */
    @Override
    public void deleteAll() {
        this.repository.deleteAll();
    }

}
