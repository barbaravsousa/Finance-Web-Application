package switchtwentytwenty.project.autentication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserJPA, Long> {

    /**
     * Find user by user name
     * @param username - username
     * @return user
     */
    Optional<UserJPA> findByUsername(String username);

    /**
     * Check if user exists through its ID (email)
     * @param email - email
     * @return true, if user exists
     */
    Boolean existsByEmail(String email);


    /**
     * Check if user exists through its user name
     * @param username - username
     * @return true, if user exists
     */
    Boolean existsByUsername(String username);

    /**
     * Delete all users in the database
     *
     */
    @Override
    void deleteAll();
}
