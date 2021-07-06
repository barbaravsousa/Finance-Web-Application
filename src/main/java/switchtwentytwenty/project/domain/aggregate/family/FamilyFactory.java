package switchtwentytwenty.project.domain.aggregate.family;

import switchtwentytwenty.project.dto.todomaindto.FamilyVoDTO;
import switchtwentytwenty.project.dto.todomaindto.FamilyJpaToDomainDTO;

public abstract class FamilyFactory {

    /**
     * Private constructor.
     */
    private FamilyFactory() {}

    /**
     * Create Family
     *
     * @param valueObjects - value objects
     */
    public static Family create(FamilyVoDTO valueObjects) {
       Family family = new Family(valueObjects.getFamilyID());
       family.setAdministratorID(valueObjects.getAdministratorID());
       family.setLedgerID(valueObjects.getLedgerID());
       family.setName(valueObjects.getName());
       return family;
    }

    /**
     * Create Family
     *
     * @param dto - dto
     */
    public static Family create(FamilyJpaToDomainDTO dto) {
        Family family = new Family(dto.getFamilyID());
        family.setAdministratorID(dto.getAdministratorID());
        family.setLedgerID(dto.getLedgerID());
        family.setRegistrationDate(dto.getRegistrationDate());
        family.addAccountID(dto.getAccountID());
        family.setFamilyID(dto.getFamilyID());
        family.setFamilyRelationList(dto.getFamilyRelation());
        family.setName(dto.getName());
        return family;
    }
}