package hibernate.test.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "Persona", uniqueConstraints = {
    @UniqueConstraint(columnNames = "ID") 	
    })
public class PersonaEntity implements Serializable {
	

private static final long serialVersionUID = -1798070786993154676L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Integer personaId;
	
	@Column(name = "NOMBRE", unique = true, nullable = false, length = 100)
	private String nombre;
	
	@Column(name = "EDAD", unique = true, nullable = false, length = 100)
	private String edad;
	
	@Column(name = "FECHA_NACIMIENTO", unique = true, nullable = false, length = 100)
	private String fechaNacimiento;
	
	public Integer getpersonaId() {
		return personaId;
	}
	
	public void setPersonaId(Integer personaId) {
		this.personaId = personaId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEdad() {
		return edad;
	}

	
	

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getPersonaId() {
		return personaId;
	}

	public void setfNac(String fNac) {
		// TODO Auto-generated method stub
		
	}

	public void setEdad(int edad2) {
		// TODO Auto-generated method stub
		
	}

	

	public void setFechaNacimiento(Date fechaNacimiento2) {
		// TODO Auto-generated method stub
		
	}

	

	

	




	
		
	}

    

    
