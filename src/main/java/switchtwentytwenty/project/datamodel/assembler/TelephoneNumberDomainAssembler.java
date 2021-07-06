package switchtwentytwenty.project.datamodel.assembler;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import switchtwentytwenty.project.datamodel.PersonJPA;
import switchtwentytwenty.project.datamodel.TelephoneNumberJPA;
import switchtwentytwenty.project.domain.share.persondata.TelephoneNumber;
import switchtwentytwenty.project.domain.share.persondata.TelephoneNumberList;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TelephoneNumberDomainAssembler {

    /**
     * Assembler to telephone number jpa.
     * @param telephoneNumber - the telephone number instance.
     * @param personJPA - the person jpa.
     * @return The telephone number jpa.
     */
    public static TelephoneNumberJPA toData(TelephoneNumber telephoneNumber, PersonJPA personJPA) {
        return new TelephoneNumberJPA(telephoneNumber.toString(), personJPA);
    }

    /**
     * Assembler to list of telephone numbers jpa.
     * @param telephoneNumberJPAList - the list of telephone numbers instances.
     * @return The list of telephone numbers jpa.
     */
    public static TelephoneNumberList toDomain(List<TelephoneNumberJPA> telephoneNumberJPAList) {
        TelephoneNumberList telephoneNumbersList = new TelephoneNumberList();
        for (TelephoneNumberJPA telephoneNumberJPA : telephoneNumberJPAList) {
            telephoneNumbersList.add(TelephoneNumberDomainAssembler.toDomain(telephoneNumberJPA));
        }
        return telephoneNumbersList;
    }

    /**
     * Assembler to telephone number jpa.
     * @param telephoneNumberJPA - the telephone number jpa.
     * @return The telephone number.
     */
    public static TelephoneNumber toDomain(TelephoneNumberJPA telephoneNumberJPA) {
        return new TelephoneNumber(telephoneNumberJPA.getNumber());
    }
}
