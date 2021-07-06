package switchtwentytwenty.project.applicationservice.appservice.implappservice;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IFamilyAndMemberService;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IFamilyIDGenerator;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.ILedgerIDGenerator;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IAuthorizationService;
import switchtwentytwenty.project.applicationservice.irepository.IFamilyRepository;
import switchtwentytwenty.project.applicationservice.irepository.ILedgerRepository;
import switchtwentytwenty.project.applicationservice.irepository.IPersonRepository;
import switchtwentytwenty.project.autentication.ERole;
import switchtwentytwenty.project.autentication.SignupDTO;
import switchtwentytwenty.project.datamodel.FamilyJPA;
import switchtwentytwenty.project.datamodel.assembler.FamilyDomainDataAssembler;
import switchtwentytwenty.project.domain.aggregate.family.Family;
import switchtwentytwenty.project.domain.aggregate.family.FamilyFactory;
import switchtwentytwenty.project.domain.aggregate.person.Person;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.domaindto.FamilyAndAdministratorDomainDTO;
import switchtwentytwenty.project.domain.domaindto.PersonAndLedgerDomainDTO;
import switchtwentytwenty.project.domain.domainservice.FamilyAndAdminFactory;
import switchtwentytwenty.project.domain.domainservice.PersonAndLedgerFactory;
import switchtwentytwenty.project.domain.share.familydata.FamilyRelation;
import switchtwentytwenty.project.domain.share.familydata.RelationType;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.domain.share.id.ID;
import switchtwentytwenty.project.domain.share.id.LedgerID;
import switchtwentytwenty.project.domain.share.persondata.PersonName;
import switchtwentytwenty.project.dto.outdto.*;
import switchtwentytwenty.project.dto.todomaindto.*;
import switchtwentytwenty.project.dto.toservicedto.FamilyAndAdminDTO;
import switchtwentytwenty.project.dto.toservicedto.PersonDTO;
import switchtwentytwenty.project.exception.*;
import switchtwentytwenty.project.util.Util;

import java.io.IOException;
import java.util.*;

@Service
@AllArgsConstructor
public class FamilyAndMemberService implements IFamilyAndMemberService {

    //Attributes
    @Autowired
    private final IFamilyRepository familyRepository;
    @Autowired
    private final IPersonRepository personRepository;
    @Autowired
    private final ILedgerRepository ledgerRepository;
    @Autowired
    private final ILedgerIDGenerator ledgerIDGenerator;
    @Autowired
    private final IFamilyIDGenerator familyIDGenerator;
    @Autowired
    private final FamilyDomainDataAssembler familyDomainDataAssembler;
    @Autowired
    private final IAuthorizationService signUpService;

    //Getter and Setters

    //Business Methods

    /**
     * Creates a family relation between two persons.
     *
     * @param personEmail      - person
     * @param kinEmail         - kin
     * @param familyIdentifier - family
     * @param relationTypeName - relation that links person to kin
     */
    public Optional<FamilyRelationOutDTO> createFamilyRelation(String personEmail, String kinEmail, String familyIdentifier, String relationTypeName)
            throws IOException, InvalidRelationTypeException, InvalidEmailException, ElementNotFoundException, InvalidDateException,
            InvalidVATException, InvalidPersonNameException {
        Email personID = new Email(personEmail);
        Email kinID = new Email(kinEmail);
        FamilyID familyID = new FamilyID(UUID.fromString(familyIdentifier));
        RelationType relationType = RelationType.getInstance(relationTypeName);
        if (areFromTheSameHousehold(personID, kinID, familyID)) {
            Family family = familyRepository.findByID(familyID);
            family.createFamilyRelation(personID, kinID, relationType);
            FamilyJPA familyJPA = familyRepository.save(family);
            return familyRepositorySaveResponseHandler(familyJPA, personID, kinID);
        }
        return Optional.empty();
    }

    /**
     * Handle the returned DTO from the save method in family repository.
     * The Data Model is converted to a output DTO.
     *
     * @param familyJPA Data model
     * @param personID  person identifier
     * @param kinID     kin identifier
     * @return output DTO
     * @throws InvalidRelationTypeException invalid relation type
     * @throws IOException                  failed to read properties file
     * @throws InvalidEmailException        invalid email
     */
    protected Optional<FamilyRelationOutDTO> familyRepositorySaveResponseHandler(FamilyJPA familyJPA, Email personID, Email kinID) throws InvalidRelationTypeException, IOException, InvalidEmailException {
        FamilyJpaToDomainDTO familyJpaToDomainDTO = familyDomainDataAssembler.toDomain(familyJPA);
        Family storedFamily = FamilyFactory.create(familyJpaToDomainDTO);
        Optional<FamilyRelation> storedFamilyRelation = storedFamily.getFamilyRelationByIDs(personID, kinID);
        return storedFamilyRelation.map(FamilyRelationOutDTOMapper::toDTO);
    }

    /**
     * Get list of family relations from a person given his/her ID
     *
     * @param personIDString - a string with person ID
     * @return a list of family relations from a person
     * @throws ElementNotFoundException - person or family not found in repository
     */
    public ViewRelationOutDTO getFamilyRelationByPersonID(String personIDString)
            throws InvalidEmailException, ElementNotFoundException, InvalidDateException, InvalidVATException, InvalidPersonNameException,
            IOException, InvalidRelationTypeException {

        Email personID = new Email(personIDString);

        Person person = personRepository.findByID(personID);

        FamilyID familyID = person.getFamilyID();

        ViewRelationOutDTO familyRelationsWithNamesFromEachPerson = new ViewRelationOutDTO(Collections.emptyList());
        List<FamilyRelation> familyRelations = getFamilyRelationByPersonID(personID, familyID);

        for (FamilyRelation familyRelation : familyRelations) {
            Email personIDinRelation = familyRelation.getPersonID();
            Email kinID = familyRelation.getKinID();

            Person personInRelation = personRepository.findByID(personIDinRelation);
            Person kinPerson = personRepository.findByID(kinID);

            PersonName personName = personInRelation.getName();
            PersonName kinName = kinPerson.getName();

            RelationType relationType = familyRelation.getRelationType();

            familyRelationsWithNamesFromEachPerson.add("" + personName + ", " + relationType + ", " + kinName + "");
        }

        return familyRelationsWithNamesFromEachPerson;
    }

    /**
     * Initialise family and its administrator
     *
     * @param dto - family and admin dto
     */
    @Transactional(rollbackFor = Exception.class)
    public FamilyAndAdminOutDTO startFamily(FamilyAndAdminDTO dto) throws PersonAlreadyInSystemException, InvalidEmailException, InvalidDateException, InvalidVATException, InvalidPersonNameException, BusinessErrorMessage {
        FamilyAndAdminVODTO valueObjects = FamilyAndAdminDomainAssembler.toDomain(dto);
        if (personRepository.existsByID(valueObjects.getEmail())) {
            throw new PersonAlreadyInSystemException("Email already used as ID.");
        }
        //create family and admin
        FamilyID familyID = familyIDGenerator.generate();
        LedgerID personLedgerID = ledgerIDGenerator.generate();
        LedgerID familyLedgerID;
        do {
            familyLedgerID = ledgerIDGenerator.generate();
        } while (personLedgerID.equals(familyLedgerID));
        FamilyAndAdminVODTO newValueObjects = valueObjects.addIDs(familyID, familyLedgerID, personLedgerID);
        PersonVoDTO personVoDTO = FamilyAndAdminDomainDTOMapper.createVOPersonDTO(newValueObjects);
        FamilyVoDTO familyVoDTO = FamilyAndAdminDomainDTOMapper.createVOFamilyDTO(newValueObjects);
        FamilyAndAdministratorDomainDTO domainDTO = FamilyAndAdminFactory.startFamily(familyVoDTO, personVoDTO);
        familyRepository.save(domainDTO.getFamily());
        personRepository.save(domainDTO.getPerson());
        ledgerRepository.save(domainDTO.getPersonLedger());
        ledgerRepository.save(domainDTO.getFamilyLedger());
        FamilyAndAdminOutDTO familyAndAdminInfo;
        familyAndAdminInfo = new FamilyAndAdminOutDTO(newValueObjects.getFamilyName().toString(), newValueObjects.getPersonName().toString(), newValueObjects.getFamilyID().toString(), newValueObjects.getEmail().toString());
        //create user
        Set<String> roles = new HashSet<>();
        roles.add(ERole.ROLE_ADMIN.name());
        SignupDTO registerUserDTO = new SignupDTO(dto.getUsername(), dto.getEmail(), dto.getPassword(), domainDTO.getFamily().getID().toString(), roles);
        signUpService.registerUser(registerUserDTO);
        return familyAndAdminInfo;
    }

    /**
     * Allows to add a new family member.
     *
     * @param personDTO Receives the personDTO.
     * @return The PersonOutDTO with selected data.
     * @throws InvalidDateException           Throws invalid date exception.
     * @throws InvalidVATException            Throws invalid vat exception.
     * @throws InvalidEmailException          Throws invalid email exception.
     * @throws PersonAlreadyInSystemException Throws person already in system exception if main email already used in app or vat already used in
     *                                        family.
     */
    @Transactional(rollbackFor = Exception.class)
    public PersonOutDTO addFamilyMember(PersonDTO personDTO)
            throws InvalidDateException, InvalidVATException, InvalidEmailException, PersonAlreadyInSystemException,
            InvalidPersonNameException, ElementNotFoundException, BusinessErrorMessage {

        //create family member
        LedgerID ledgerID = ledgerIDGenerator.generate();
        PersonVoDTO personVoDTO = PersonDomainAssembler.toDomain(personDTO, ledgerID);

        if (!familyRepository.existsByID(personVoDTO.getFamilyID())) {
            throw new ElementNotFoundException(Constants.FAMILY_NOT_FOUND);
        }
        if (personRepository.existsByID(personVoDTO.getEmail())) {
            throw new PersonAlreadyInSystemException("Email Already Used");
        }
        if (personRepository.existsByFamilyIDAndVat(personVoDTO.getFamilyID(), personVoDTO.getVat())) {
            throw new InvalidVATException("Vat Already Used In This Family");
        }

        PersonAndLedgerDomainDTO personAndLedgerDomainDTO = PersonAndLedgerFactory.create(personVoDTO);
        Person person = personAndLedgerDomainDTO.getPerson();
        personRepository.save(personAndLedgerDomainDTO.getPerson());
        ledgerRepository.save(personAndLedgerDomainDTO.getLedger());

        //create user
        Set<String> roles = new HashSet<>();
        roles.add(ERole.ROLE_USER.name());
        SignupDTO registerUserDTO = new SignupDTO(personDTO.getUsername(), personDTO.getEmail(), personDTO.getPassword(), personDTO.getFamilyID(), roles);
        signUpService.registerUser(registerUserDTO);


        return new PersonOutDTO(person.getName().toString(), person.getMainEmail().toString(), person.getFamilyID().toString());
    }

    /**
     * Get family relation's from a person given his/her ID
     *
     * @param personID - person ID
     * @param familyID - family ID
     * @return a list of family relations from a person
     * @throws ElementNotFoundException - family not found
     */
    public List<FamilyRelation> getFamilyRelationByPersonID(Email personID, FamilyID familyID) throws
            ElementNotFoundException, InvalidRelationTypeException, IOException, InvalidEmailException {
        Family family = familyRepository.findByID(familyID);
        return family.getFamilyRelationByPersonID(personID);
    }

    /**
     * Confirms if the two id are from a persons that belong to the same family.
     *
     * @param personID - person ID
     * @param kinID    - kin ID
     * @param familyID - family ID
     * @return true if they belong to the same household
     */
    protected boolean areFromTheSameHousehold(Email personID, Email kinID, FamilyID familyID) throws
            ElementNotFoundException, InvalidDateException, InvalidEmailException, InvalidVATException, InvalidPersonNameException {
        Util.checkIfNull(Arrays.asList(personID, kinID, familyID));
        areTheSamePerson(personID, kinID);
        Person person = personRepository.findByID(personID);
        Person kin = personRepository.findByID(kinID);
        if (!person.isMemberOfFamily(familyID) || !kin.isMemberOfFamily(familyID)) {
            throw new IllegalArgumentException("Person and/or kin doesn't belong to the family");
        }
        return true;
    }

    /**
     * Verifies if both parameters are from the same person
     *
     * @param personID - Person ID
     * @param kinID    - Kin ID
     * @return return true is the ID are the same
     */
    private void areTheSamePerson(ID personID, ID kinID) {
        if (personID.equals(kinID)) {
            throw new IllegalArgumentException("Person ID and kin Id are from the same person");
        }
    }
}