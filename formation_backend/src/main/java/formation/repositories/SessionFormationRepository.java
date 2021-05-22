package formation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import formation.entities.SessionFormation;

@Repository
public interface SessionFormationRepository extends JpaRepository<SessionFormation, Long> {
    
}
