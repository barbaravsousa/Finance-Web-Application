package switchtwentytwenty.project.interfaceadaptor.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import switchtwentytwenty.project.applicationservice.irepository.IFamilyRepository;
import switchtwentytwenty.project.datamodel.FamilyJPA;
import switchtwentytwenty.project.datamodel.FamilyRelationJPA;
import switchtwentytwenty.project.datamodel.assembler.FamilyDomainDataAssembler;
import switchtwentytwenty.project.datamodel.assembler.FamilyRelationDomainDataAssembler;
import switchtwentytwenty.project.domain.aggregate.family.Family;
import switchtwentytwenty.project.domain.aggregate.family.FamilyFactory;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.familydata.FamilyRelation;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.dto.todomaindto.FamilyJpaToDomainDTO;
import switchtwentytwenty.project.exception.ElementNotFoundException;
import switchtwentytwenty.project.exception.InvalidEmailException;
import switchtwentytwenty.project.exception.InvalidRelationTypeException;
import switchtwentytwenty.project.interfaceadaptor.repository.jpa.FamilyJPARepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public class FamilyRepository implements IFamilyRepository {

    @Autowired
    FamilyJPARepository repository;
    @Autowired
    FamilyDomainDataAssembler familyAssembler;
    @Autowired
    FamilyRelationDomainDataAssembler relationAssembler;

    /**
     * Transforms a Family in Family JPA and adds them to the Family JPA repository.
     *
     * @param family object
     */
    @Override
    public FamilyJPA save(Family family) {
        FamilyJPA familyJPA = familyAssembler.toData(family);
        List<FamilyRelation> relationList = family.getFamilyRelationList();
        for (FamilyRelation familyRelation : relationList) {
            FamilyRelationJPA familyRelationJPA = relationAssembler.toData(familyRelation, familyJPA);
            familyJPA.addFamilyRelation(familyRelationJPA);
        }
        return this.repository.save(familyJPA);
    }

    /**
     * Method to find by ID
     *
     * @param familyID - familyID
     * @return Family instance
     * @throws InvalidEmailException
     */
    @Transactional
    @Override
    public Family findByID(FamilyID familyID) throws InvalidRelationTypeException, IOException, InvalidEmailException, ElementNotFoundException {
        Optional<FamilyJPA> familyJPA = this.repository.findById(familyID.toString());
        if (!familyJPA.isPresent()) {
            throw new ElementNotFoundException(Constants.FAMILY_NOT_FOUND);
        }
        FamilyJpaToDomainDTO dto = familyAssembler.toDomain(familyJPA.get());
        return FamilyFactory.create(dto);
    }

    /**
     * Method to verify the familyId exists in the repository
     *
     * @param familyID - familyID
     * @return true if exists
     */
    @Override
    public boolean existsByID(FamilyID familyID) {
        return this.repository.existsById(familyID.toString());
    }


    /**
     * Method to return a list of all families stored in FamilyRepository
     *
     * @return List of families
     * @throws InvalidRelationTypeException
     * @throws IOException
     * @throws InvalidEmailException
     */
    @Transactional
    @Override
    public List<Family> findAll() throws InvalidRelationTypeException, IOException, InvalidEmailException {
        Iterable<FamilyJPA> familyJPA = this.repository.findAll();
        List<Family> familyList = new ArrayList<>();
        for (FamilyJPA familyJPA1 : familyJPA) {
            FamilyJpaToDomainDTO dto = familyAssembler.toDomain(familyJPA1);
            Family family = FamilyFactory.create(dto);
            familyList.add(family);
        }
        return familyList;
    }

    /**
     * Delete all data from repository
     */
    @Override
    public void deleteAll() {
        this.repository.deleteAll();
    }
}
