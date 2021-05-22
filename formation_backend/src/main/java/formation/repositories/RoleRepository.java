package formation.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import formation.entities.ERole;
import formation.entities.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
	public Role findByName(ERole name);
}
