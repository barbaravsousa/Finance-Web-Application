package switchtwentytwenty.project.datamodel.assembler;


import org.springframework.stereotype.Service;
import switchtwentytwenty.project.datamodel.CategoryJPA;
import switchtwentytwenty.project.domain.aggregate.category.Category;
import switchtwentytwenty.project.domain.share.designation.CategoryDesignation;
import switchtwentytwenty.project.domain.share.id.CategoryID;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.dto.todomaindto.CategoryVoDTO;

import java.util.UUID;

@Service
public class CategoryDomainDataAssembler {

    /**
     * Transforms the domain object, Category, into a JPA object.
     *
     * @param category - object that is going to be transformed
     * @return a category JPA
     */
    public CategoryJPA toData(Category category) {
        CategoryJPA categoryJPA;
        String parent = category.getParentID()!=null ? category.getParentID().toString() : null;
        if (category.isStandard()) {
            categoryJPA = new CategoryJPA(category.getID().toString(), parent, category.getDesignation().toString(), category.isStandard());
        } else {
            categoryJPA = new CategoryJPA(category.getID().toString(), parent, category.getDesignation().toString(), category.getFamilyID().toString(), category.isStandard());
        }
        return categoryJPA;
    }

    /**
     * Transforms the JPA object into a domain object.
     *
     * @param categoryJPA - object that is going to be transformed
     * @return the domain object
     */
    public CategoryVoDTO toDomain(CategoryJPA categoryJPA) {

        CategoryID categoryID = new CategoryID(categoryJPA.getId());
        CategoryDesignation categoryDesignation = new CategoryDesignation(categoryJPA.getDesignation());
        CategoryID parent = categoryJPA.getParentID()!=null ? new CategoryID(categoryJPA.getParentID()) : null;


        CategoryVoDTO categoryVoDTO;
        if (categoryJPA.isStandard()) {
            categoryVoDTO = new CategoryVoDTO(categoryID, parent, categoryDesignation);
        } else {
            FamilyID familyID = new FamilyID(UUID.fromString(categoryJPA.getFamilyId()));
            categoryVoDTO = new CategoryVoDTO(categoryID, parent, categoryDesignation,familyID);
        }
        return categoryVoDTO;
    }

}
