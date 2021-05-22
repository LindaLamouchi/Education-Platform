package formation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import formation.entities.Pays;

@Repository
public interface PaysRepository extends JpaRepository<Pays, Long> {
    
}

