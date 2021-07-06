package switchtwentytwenty.project.interfaceadaptor.repository.jpa;

import org.springframework.data.repository.CrudRepository;
import switchtwentytwenty.project.datamodel.PersonJPA;

import java.util.List;


public interface PersonJPARepository extends CrudRepository<PersonJPA, String> {

    /**
     * Find person jpa
     * @param familyID - familyID
     * @return - person JPA
     */
    List<PersonJPA> findAllByFamilyID(String familyID);

    /**
     * Verifies if person with familyID and Vat exists.
     * @param familyID - person's familyID.
     * @param vat - person's Vat.
     * @return true if person with familyID and Vat exists.
     */
    boolean existsByFamilyIDAndVat(String familyID, String vat);
}
