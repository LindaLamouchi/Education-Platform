package formation.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="image_model")
public class ImageModel {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "picByte", length = 2000)
	private byte[] picByte;
	
	@OneToOne
	@JoinColumn(name="formation_id",nullable = true)
	private Formation formation;
	
	public ImageModel() {
		// TODO Auto-generated constructor stub
	}
	public ImageModel(String name, String type, byte[] picByte,Formation formation) {
		this.name = name;
		this.type = type;
		this.picByte = picByte;
		this.formation=formation;
		}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getPicByte() {
		return picByte;
	}

	public void setPicByte(byte[] picByte) {
		this.picByte = picByte;
	}
	
	public Formation getformation() {
		return formation;
	}
	public void setformation(Formation formation) {
		this.formation = formation;
	}

	

}
