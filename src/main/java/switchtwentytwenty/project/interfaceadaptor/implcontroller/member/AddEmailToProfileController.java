package switchtwentytwenty.project.interfaceadaptor.implcontroller.member;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import switchtwentytwenty.project.dto.outdto.AddEmailOutDTO;
import switchtwentytwenty.project.exception.BusinessErrorMessage;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IPersonService;
import switchtwentytwenty.project.dto.indto.AddEmailInDTO;
import switchtwentytwenty.project.exception.*;
import switchtwentytwenty.project.interfaceadaptor.icontroller.member.IAddEmailToProfileController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "http://localhost:3002"}, maxAge = 3600)
public class AddEmailToProfileController implements IAddEmailToProfileController {

    // Attributes

    @Autowired
    private final IPersonService personService;


    // Business Methods

    /**
     * Allows to add an email address to a Person Profile
     *
     * @param personId - id of person used to identify the person that is going to add an email to his profile
     * @param info     - Dto with person data
     * @return true if the email is added with success
     */
    @PostMapping(value = "/users/{personId}/email")
    public ResponseEntity<Object> addEmailToProfile(@PathVariable String personId, @RequestBody AddEmailInDTO info) {
        try {
             personService.addEmailToProfile(personId, info.getEmailToAdd());
            AddEmailOutDTO result = new AddEmailOutDTO(personId);
            Link linkToProfile = WebMvcLinkBuilder.linkTo(methodOn(ViewProfileController.class)
                    .getUserProfile(personId))
                    .withRel("View User Profile");

            result.add(linkToProfile);

            return new ResponseEntity<>(result, HttpStatus.OK);

        } catch (IllegalArgumentException | NullPointerException | InvalidDateException | InvalidPersonNameException | InvalidVATException | InvalidEmailException | ElementNotFoundException exception) {
            BusinessErrorMessage msg = new BusinessErrorMessage(exception.getMessage(), BusinessErrorMessage.GROUP_DESCRIPTION_NOT_FOUND);
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }

    }
}
