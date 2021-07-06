package switchtwentytwenty.project.dto.toservicedto;

import switchtwentytwenty.project.dto.indto.PersonInDTO;

public class PersonDTOMapper {

    /**
     * Private empty constructor.
     */
    private PersonDTOMapper() {
    }

    /**
     * Map to person dto.
     * @param personInDTO - in person dto.
     * @return The person dto.
     */
    public static PersonDTO mapToDTO(PersonInDTO personInDTO) {
        return new PersonDTO.PersonDTOBuilder()
                .withName(personInDTO.getName())
                .withEmail(personInDTO.getEmail())
                .withBirthDate(personInDTO.getBirthDate())
                .withVat(personInDTO.getVat())
                .withHouseNumber(personInDTO.getHouseNumber())
                .withStreet(personInDTO.getStreet())
                .withZipCode(personInDTO.getZipCode())
                .withCity(personInDTO.getCity())
                .withCountry(personInDTO.getCountry())
                .withPhoneNumbers(personInDTO.getPhoneNumbers())
                .withFamilyID(personInDTO.getFamilyID())
                .withPassword(personInDTO.getPassword())
                .withUsername(personInDTO.getUsername())
                .build();
    }
}
