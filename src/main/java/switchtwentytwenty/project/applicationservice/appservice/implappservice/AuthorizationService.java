package switchtwentytwenty.project.applicationservice.appservice.implappservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IAuthorizationService;
import switchtwentytwenty.project.applicationservice.irepository.IPersonRepository;
import switchtwentytwenty.project.autentication.*;
import switchtwentytwenty.project.domain.aggregate.person.Person;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.exception.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class AuthorizationService implements IAuthorizationService {

    private static final String ERROR_ROLE_NOT_FOUND = "Error: Role is not found.";

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    IPersonRepository personRepository;

    @Transactional(rollbackFor = Exception.class)
    public void registerUser(SignupDTO signUpRequest) throws BusinessErrorMessage {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new BusinessErrorMessage("Error: Username is already taken!", HttpStatus.BAD_REQUEST.value());
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BusinessErrorMessage("Error: Email is already in use!", HttpStatus.BAD_REQUEST.value());
        }

        UserJPA user = new UserJPA(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()), signUpRequest.getFamilyID());

        Set<String> strRoles = signUpRequest.getRole();
        Set<RoleJPA> roles = new HashSet<>();

        if (strRoles == null) {
            RoleJPA userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException(ERROR_ROLE_NOT_FOUND));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "ROLE_ADMIN":
                        RoleJPA adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException(ERROR_ROLE_NOT_FOUND));
                        roles.add(adminRole);
                        break;

                    case "ROLE_SYSTEM_MANAGER":
                        RoleJPA systemRole = roleRepository.findByName(ERole.ROLE_SYSTEM_MANAGER)
                                .orElseThrow(() -> new RuntimeException(ERROR_ROLE_NOT_FOUND));
                        roles.add(systemRole);
                        break;

                    default:
                        RoleJPA userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException(ERROR_ROLE_NOT_FOUND));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
    }

    /**
     * get personID from username
     *
     * @param username - username
     * @return person's ID
     * @throws InvalidEmailException      invalid email
     * @throws UserEmailNotFoundException email not found
     */
    public Email getPersonIDOfUser(String username) throws InvalidEmailException, UserEmailNotFoundException {
        Optional<UserJPA> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            throw new UserEmailNotFoundException("Username not found in database");
        }
        return new Email(user.get().getEmail());
    }

    /**
     * Authorization method to access an account
     *
     * @param username  - username from token
     * @param accountId - account ID involved in operation
     * @return true, if Authorized
     * @throws InvalidEmailException
     * @throws UserEmailNotFoundException - email not found
     * @throws InvalidDateException
     * @throws ElementNotFoundException
     * @throws InvalidVATException
     * @throws InvalidPersonNameException
     */
    public boolean accessAccountAuthorization(String username, String accountId) throws InvalidEmailException, UserEmailNotFoundException, InvalidDateException, ElementNotFoundException, InvalidVATException, InvalidPersonNameException {
        Email personID = this.getPersonIDOfUser(username);
        Person person = personRepository.findByID(personID);
        AccountID accountID = new AccountID(UUID.fromString(accountId));
        return person.isMyAccount(accountID);
    }

    /**
     * Defines access to family cash account.
     * Just accessible to the family administrator.
     *
     * @return true if access is authorized
     */
    public boolean accessFamilyCashAccountAuthorization(String role) {
        return role.equals(ERole.ROLE_ADMIN.toString());
    }

    /**
     * get user role from token information
     *
     * @param tokenInfo - part of token information
     * @return user's role
     */
    public String getRole(String tokenInfo) {
        String[] authorities = tokenInfo.split("Authorities=");
        String role = authorities[1].substring(1);
        role = role.substring(0, role.length() - 1);
        return role.substring(0, role.length() - 1);
    }

    /**
     * get family ID from username
     *
     * @param username - username
     * @return family id's user
     * @throws UserEmailNotFoundException
     */
    public String getFamilyID(String username) throws UserEmailNotFoundException {
        Optional<UserJPA> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            throw new UserEmailNotFoundException("Username not found in database");
        }
        return user.get().getFamilyID();
    }

}
