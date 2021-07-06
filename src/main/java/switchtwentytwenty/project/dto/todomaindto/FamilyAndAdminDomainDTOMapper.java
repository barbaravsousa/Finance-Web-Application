package switchtwentytwenty.project.dto.todomaindto;

public abstract class FamilyAndAdminDomainDTOMapper {

    /**
     * Private constructor.
     */
    private FamilyAndAdminDomainDTOMapper() {}

    public static PersonVoDTO createVOPersonDTO(FamilyAndAdminVODTO vo){
        PersonVoDTO personVoDTO = new PersonVoDTO();
        personVoDTO.setAddress(vo.getAddress());
        personVoDTO.setBirthDate(vo.getBirthDate());
        personVoDTO.setEmail(vo.getEmail());
        personVoDTO.setLedgerID(vo.getPersonLedgerID());
        personVoDTO.setFamilyID(vo.getFamilyID());
        personVoDTO.setName(vo.getPersonName());
        personVoDTO.setVat(vo.getVat());
        personVoDTO.setPhoneNumbers(vo.getPhoneNumbers());
        return personVoDTO;
    }

    public static FamilyVoDTO createVOFamilyDTO(FamilyAndAdminVODTO vo){
        FamilyVoDTO familyVoDTO = new FamilyVoDTO();
        familyVoDTO.setLedgerID(vo.getFamilyLedgerID());
        familyVoDTO.setFamilyID(vo.getFamilyID());
        familyVoDTO.setAdministratorID(vo.getEmail());
        familyVoDTO.setName(vo.getFamilyName());
        return familyVoDTO;
    }
}
