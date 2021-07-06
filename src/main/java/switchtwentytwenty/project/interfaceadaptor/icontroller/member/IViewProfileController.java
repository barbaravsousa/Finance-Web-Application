package switchtwentytwenty.project.interfaceadaptor.icontroller.member;

import org.springframework.http.ResponseEntity;

public interface IViewProfileController {

    /**
     * Method that adds all the information about a user to a DTO.
     *
     * @param personId - person's email
     * @return a DTO with the user's profile if successfully, or a message error otherwise
     */
    ResponseEntity<Object> getUserProfile(String personId);
}
