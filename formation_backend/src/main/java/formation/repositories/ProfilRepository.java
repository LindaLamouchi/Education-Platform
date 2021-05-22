package formation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import formation.entities.Profil;

@Repository
public interface ProfilRepository extends JpaRepository<Profil, Long> {
    
}
