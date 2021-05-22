package formation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import formation.entities.Formation;
import formation.entities.ImageModel;
import formation.repositories.FormationRepository;
import formation.repositories.ImageRepository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "image")
public class ImageUploadController {

	@Autowired
	ImageRepository imageRepository;
	@Autowired
	FormationRepository formations;
	
	
	@PostMapping("/upload/{id}")
	public ResponseEntity<ImageModel>
	uplaodImage(@RequestParam("imageFile") MultipartFile file, @PathVariable Long id) throws
	IOException {
		ImageModel img =null;
		System.out.println("Taille de l'image : " + file.getBytes().length);
		
		Optional<Formation> t=formations.findById(id);
		Formation form=t.get();
		
		//clean our data table if there is an image assigned to this team we'll delete it
		
		List<ImageModel> l=imageRepository.findAll();
		
		for (ImageModel imageModel : l) {
			if(imageModel.getformation()!=null)
				if(imageModel.getformation().getFormationId()==id)
					imageRepository.delete(imageModel);
		}
		
		
		System.out.println("file name = "+file.getOriginalFilename());
		ResponseEntity response = null;
		
		img = new ImageModel(file.getOriginalFilename(), file.getContentType(), file.getBytes(), form);
		imageRepository.save(img);
		
		System.out.println("image ajoutée avec succès ");
		
		response = ResponseEntity.ok().body(img);
		
		return response;
	}
	/*@GetMapping(path = { "/get/{imageName}" })
	public ImageModel getImage(@PathVariable("imageName") String imageName)
			throws IOException {
		System.out.println("nom de l'image : "+imageName);
		final Optional<ImageModel> retrievedImage =
				imageRepository.findByName(imageName);
		ImageModel img = new ImageModel(retrievedImage.get().getName(),
				retrievedImage.get().getType(),
				retrievedImage.get().getPicByte());
		return img;
	}*/
	
	@GetMapping(path = { "/get/{id}" })
	public ImageModel getImage(@PathVariable Long id) throws IOException {
		Optional<Formation> t;
		t=formations.findById(id);
		
		final ImageModel img = imageRepository.findByFormation(t.get());		
		return img;
	}
	
	
	
	
	
	
	
	
}