package switchtwentytwenty.project.interfaceadaptor.icontroller.member;

import org.springframework.http.ResponseEntity;

import switchtwentytwenty.project.dto.indto.PersonInDTO;

public interface IAddFamilyMemberController {


    /**
     * Allows to add a new family Member.
     * @param info - PersonInDTO with person data.
     * @return An response entity that informs the operation success.
     */
    ResponseEntity<Object> addFamilyMember(PersonInDTO info);
}
