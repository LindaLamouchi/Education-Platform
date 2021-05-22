package formation.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import formation.entities.Formation;
import formation.entities.ImageModel;

import java.util.List;

public interface ImageRepository extends JpaRepository<ImageModel, Long>{
	Optional<ImageModel> findByName(String name);

	boolean existsByName(String name);
	ImageModel findByFormation(Formation formation); 
	
}
