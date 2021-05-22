package formation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import formation.entities.Organisme;

@Repository
public interface OrganismeRepository extends JpaRepository<Organisme, Long> {
    
}
