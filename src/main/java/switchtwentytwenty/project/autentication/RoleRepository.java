package switchtwentytwenty.project.autentication;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<RoleJPA, Long> {

    Optional<RoleJPA> findByName(ERole name);

    RoleJPA save(RoleJPA role);
}

