package formation.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import formation.entities.Domaine;

@Repository
public interface DomaineRepository extends JpaRepository<Domaine, Long>{
    
}
