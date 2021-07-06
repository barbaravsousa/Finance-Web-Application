package switchtwentytwenty.project.applicationservice.irepository;

import switchtwentytwenty.project.datamodel.FamilyJPA;
import switchtwentytwenty.project.domain.aggregate.family.Family;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.exception.ElementNotFoundException;
import switchtwentytwenty.project.exception.InvalidEmailException;
import switchtwentytwenty.project.exception.InvalidRelationTypeException;

import java.io.IOException;
import java.util.List;

public interface IFamilyRepository {

    /**
     * Find element in FamilyRepository by ID.
     *
     * @param id - Family's identification
     * @return element with same ID
     */
    Family findByID(FamilyID id) throws InvalidRelationTypeException, IOException, InvalidEmailException, ElementNotFoundException;

    /**
     * Adds an Family instance to the repository.
     *
     * @param entity - Entity instance
     */
    FamilyJPA save(Family entity);

    /**
     * Verifies if the id exists in the repository.
     *
     * @param id - entity's id
     * @return false, if id isn't used in the app
     */
    boolean existsByID(FamilyID id);

    /**
     *
     * @return List of Families
     * @throws InvalidRelationTypeException
     * @throws IOException
     * @throws InvalidEmailException
     */
    List<Family> findAll() throws InvalidRelationTypeException, IOException, InvalidEmailException;

    /**
     * Delete all data from repository
     */
    void deleteAll();
}
