package switchtwentytwenty.project.interfaceadaptor.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import switchtwentytwenty.project.applicationservice.irepository.IPersonRepository;
import switchtwentytwenty.project.datamodel.AccountIDJPA;
import switchtwentytwenty.project.datamodel.PersonJPA;
import switchtwentytwenty.project.datamodel.assembler.PersonDomainDataAssembler;
import switchtwentytwenty.project.domain.aggregate.person.Person;
import switchtwentytwenty.project.domain.aggregate.person.PersonFactory;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.domain.share.list.PersonList;
import switchtwentytwenty.project.domain.share.persondata.VAT;
import switchtwentytwenty.project.dto.todomaindto.PersonJpaToDomainDTO;
import switchtwentytwenty.project.exception.*;
import switchtwentytwenty.project.interfaceadaptor.repository.jpa.PersonJPARepository;

import java.util.List;
import java.util.Optional;

@Service
public class PersonRepository implements IPersonRepository {

    @Autowired
    PersonJPARepository repository;
    @Autowired
    PersonDomainDataAssembler assembler;

    /**
     * Adds an Person instance to the repository.
     *
     * @param person - person instance
     */
    public void save(Person person) {
        PersonJPA personJPA = assembler.toData(person);
        repository.save(personJPA);
    }

    /**
     * Verifies if the id exists in the repository.
     *
     * @param id - entity's id
     * @return false, if id isn't used in the app
     */
    public boolean existsByID(Email id) {
        return this.repository.existsById(id.toString());
    }

    /**
     * Find element in PersonRepository by ID.
     *
     * @param id - Account's identification
     * @return element with same ID
     */
    @Transactional
    public Person findByID(Email id) throws InvalidDateException, InvalidEmailException, InvalidVATException, InvalidPersonNameException, ElementNotFoundException {
        Optional<PersonJPA> personJPA = this.repository.findById(id.toString());
        if (!personJPA.isPresent()) {
            throw new ElementNotFoundException("Person not found");
        }
        PersonJpaToDomainDTO dto = assembler.toDomain(personJPA.get());
        return PersonFactory.create(dto);
    }

    /**
     * Find all Persons from a given family.
     *
     * @param familyID - family ID.
     * @return list of family members
     */
    @Transactional
    public PersonList findByFamilyID(FamilyID familyID)
            throws InvalidDateException, InvalidEmailException, InvalidVATException, InvalidPersonNameException {
        Iterable<PersonJPA> personJPAList = this.repository.findAllByFamilyID(familyID.toString());
        PersonList result = new PersonList();
        for (PersonJPA personJPA : personJPAList) {
            PersonJpaToDomainDTO dto = assembler.toDomain(personJPA);
            result.add(PersonFactory.create(dto));
        }
        return result;
    }

    /**
     * Find Person with given account.
     * @param accountID - accountID.
     * @return - The person that holds that account.
     * @throws InvalidVATException - if vat invalid.
     * @throws InvalidPersonNameException - if person name invalid.
     * @throws InvalidDateException - if date invalid.
     * @throws InvalidEmailException - if email invalid.
     * @throws ElementNotFoundException - if none person found.
     */
    @Transactional
    public Person findByAccountID(AccountID accountID)
            throws InvalidVATException, InvalidPersonNameException, InvalidDateException, InvalidEmailException, ElementNotFoundException {
        List<PersonJPA> list = (List<PersonJPA>) repository.findAll();
        for (PersonJPA personJPA : list) {
            List<AccountIDJPA> listAccounts = personJPA.getAccountIDList();
            for (AccountIDJPA accountIDJPA : listAccounts) {
                if (accountIDJPA.hasSameID(accountID.toString())) {
                    return PersonFactory.create(assembler.toDomain(personJPA));
                }
            }
        }
        throw new ElementNotFoundException("Person not found");
    }

    /**
     * Verifies if person with familyID and Vat exists.
     *
     * @param familyID - person's familyID.
     * @param vat - person's Vat.
     * @return true if person with familyID and Vat exists.
     */
    @Transactional
    public boolean existsByFamilyIDAndVat(FamilyID familyID, VAT vat) {
        return this.repository.existsByFamilyIDAndVat(familyID.toString(), vat.toString());
    }

    /**
     * Delete all data from repository
     */
    @Override
    public void deleteAll() {
        this.repository.deleteAll();
    }

}
