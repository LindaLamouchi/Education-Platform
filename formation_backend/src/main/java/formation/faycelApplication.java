package formation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import formation.entities.ERole;
import formation.entities.Role;
import formation.repositories.RoleRepository;

@SpringBootApplication
@ComponentScan(basePackages = "formation.controllers, formation.services, formation.security")
@EntityScan(basePackages = "formation.entities")
@EnableJpaRepositories(basePackages = "formation.repositories")
public class faycelApplication implements CommandLineRunner {
	@Autowired
	private RoleRepository roleRepository;
	public static void main(String[] args) {
		SpringApplication.run(faycelApplication.class, args);
		
	    }
	
	
	@Override
    public void run(String... args) throws Exception {
        if(roleRepository.findByName(ERole.SIMPLE_UTILISATEUR)==null){
            roleRepository.save(new Role(ERole.ADMINISTRATEUR));
            roleRepository.save(new Role(ERole.SIMPLE_UTILISATEUR));
        }

    }
	}






