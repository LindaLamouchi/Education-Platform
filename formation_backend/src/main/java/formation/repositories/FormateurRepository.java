package formation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import formation.entities.Formateur;

@Repository
public interface FormateurRepository extends JpaRepository<Formateur, Long> {
    
}
