package formation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import formation.entities.Participant;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    
}
