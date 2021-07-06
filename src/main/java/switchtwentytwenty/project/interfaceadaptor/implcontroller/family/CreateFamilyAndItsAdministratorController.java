package switchtwentytwenty.project.interfaceadaptor.implcontroller.family;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switchtwentytwenty.project.dto.indto.FamilyAndAdminInDTO;
import switchtwentytwenty.project.exception.BusinessErrorMessage;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IFamilyAndMemberService;
import switchtwentytwenty.project.dto.toservicedto.FamilyAndAdminDTO;
import switchtwentytwenty.project.dto.toservicedto.FamilyAndAdminDTOMapper;
import switchtwentytwenty.project.dto.outdto.FamilyAndAdminOutDTO;
import switchtwentytwenty.project.exception.*;
import switchtwentytwenty.project.interfaceadaptor.icontroller.family.ICreateFamilyAndItsAdministratorController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

//@Controller             //springboot component
@RestController
@AllArgsConstructor     //Constructor method with all class arguments (lombock)
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "http://localhost:3002"}, maxAge = 3600)
public class CreateFamilyAndItsAdministratorController implements ICreateFamilyAndItsAdministratorController {

    //Attributes
    @Autowired
    private final IFamilyAndMemberService familyAndMemberService;

    //Constructor Method

    //Business Method

    /**
     * Start family and create administrator
     * @param info - family and admin information
     * @return family and admin information and links
     */
    @PostMapping(value = "/families")
    public ResponseEntity<Object> startFamily(@RequestBody FamilyAndAdminInDTO info) {
        try {

            FamilyAndAdminDTO familyAndAdminDTO = FamilyAndAdminDTOMapper.mapToDTO(info);

            FamilyAndAdminOutDTO result = this.familyAndMemberService.startFamily(familyAndAdminDTO);

            Link selfLink = linkTo(methodOn(CreateFamilyAndItsAdministratorController.class).startFamily(info)).withRel("self");

            result.add(selfLink);
            return new ResponseEntity<>(result, HttpStatus.CREATED);

        } catch (PersonAlreadyInSystemException | NullPointerException | InvalidDateException | InvalidEmailException | InvalidPersonNameException | InvalidVATException | IllegalArgumentException | BusinessErrorMessage exception) {

            String errorMessage = exception.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
    }
}
