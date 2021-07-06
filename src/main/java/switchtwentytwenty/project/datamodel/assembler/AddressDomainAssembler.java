package switchtwentytwenty.project.datamodel.assembler;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import switchtwentytwenty.project.datamodel.AddressJPA;
import switchtwentytwenty.project.datamodel.PersonJPA;
import switchtwentytwenty.project.domain.share.persondata.address.Address;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressDomainAssembler {

    /**
     * Assembler to address jpa.
     * @param address - address instance.
     * @param personJPA - person jpa.
     * @return The address jpa.
     */
    public static AddressJPA toData(Address address, PersonJPA personJPA) {
        String houseNumber = address.getHouseNumber().toString();
        String street = address.getStreet().toString();
        String city = address.getCity().toString();
        String zipCode = address.getZipCode().toString();
        String country = address.getCountry().toString();
        return new AddressJPA(houseNumber, street, city, zipCode, country, personJPA);
    }

    /**
     * Assembler to address instance.
     * @param addressJPA - address jpa.
     * @return The address instance.
     */
    public static Address toDomain(AddressJPA addressJPA) {
        String houseNumber = addressJPA.getHouseNumber();
        String street = addressJPA.getStreet();
        String city = addressJPA.getCity();
        String zipCode = addressJPA.getZipCode();
        String country = addressJPA.getCountry();
        return new Address(street, houseNumber, zipCode, city, country);
    }
}
