package com.comercio.web.model;


import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(name = "usuarios")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
public class Usuario implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	public void setId(long id) {
		this.id = id;
	}
	@NotEmpty(message = "*Por favor ontroduzca su nombre")
	private String Nombre;
	@NotEmpty(message = "*Por favor ontroduzca su apellido paterno.")
	private String Ap;
	private String Am;
	private long DNI;
	private Date fechaNacimiento;
	private char Sexo;
	private String Direccion;
	private int Telefono;
	@Email(message = "*Por favor introduzca un correo valido")
	private String Correo;
	@Transient
	private MultipartFile Foto;
	private int Estado;
	private int Nit;
	private String puestoTrabajo;
	private String movil;
	
	public String getMovil() {
		return movil;
	}
	public void setMovil(String movil) {
		this.movil = movil;
	}
	public String getPuestoTrabajo() {
		return puestoTrabajo;
	}
	public void setPuestoTrabajo(String puestoTrabajo) {
		this.puestoTrabajo = puestoTrabajo;
	}
	@CreationTimestamp
	private Date FechaAlta;
	//relacion uno a uchos Usuarios-Roles
	
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Rol> roles = new ArrayList<>();

	
	public List<Rol> getRoles() {
		return roles;
	}
    public void addRol(Rol rol) {
        roles.add( rol );
       
    }
	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}




  
   
	@OneToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name = "datos_id")
	private Dato datos;


	public Dato getDatos() {
		return datos;
	}
	public void setDatos(Dato datos) {
		this.datos = datos;
	}
	public Usuario() {
	}

	public Usuario(int id) {
		this.id = id;
	}
	public Usuario(String Nombre, String Ap, String Am,long DNI,Date fechaNacimiento, 
			char Sexo, String Direccion, int Telefono,
			String correo, int estado, int Nit,String movil,String puestotrabajo) {
		this.Nombre = Nombre;
		this.Ap = Ap;
		this.Am = Am;
		this.DNI=DNI;
		this.fechaNacimiento=fechaNacimiento;
		this.Sexo = Sexo;
		this.Direccion = Direccion;
		this.Telefono = Telefono;
		this.Correo = correo;
		this.Estado = estado;
		this.Nit = Nit;
		this.puestoTrabajo=puestotrabajo;
		this.movil=movil;
		
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getDNI() {
		return DNI;
	}

	public void setDNI(long dNI) {
		DNI = dNI;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getAp() {
		return Ap;
	}

	public void setAp(String ap) {
		Ap = ap;
	}

	public String getAm() {
		return Am;
	}

	public void setAm(String am) {
		Am = am;
	}

	public char getSexo() {
		return Sexo;
	}

	public void setSexo(char sexo) {
		Sexo = sexo;
	}

	public String getDireccion() {
		return Direccion;
	}

	public void setDireccion(String direccion) {
		Direccion = direccion;
	}

	public int getTelefono() {
		return Telefono;
	}

	public void setTelefono(int telefono) {
		Telefono = telefono;
	}

	public String getCorreo() {
		return Correo;
	}

	public void setCorreo(String correo) {
		this.Correo = correo;
	}

	public MultipartFile getFoto() {
		return Foto;
	}

	public void setFoto(MultipartFile foto) {
		Foto = foto;
	}

	public int getEstado() {
		return Estado;
	}

	public void setEstado(int estado) {
		this.Estado = estado;
	}

	public int getNIT() {
		return Nit;
	}

	public void setNIT(int nIT) {
		Nit = nIT;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Date getFechaAlta() {
		return FechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		FechaAlta = fechaAlta;
	}
	
/*	@Override
	public String toString() {
		return "usuarios [id=" + id + ", Nombre=" + Nombre + ", Ap="+ Ap +", Am="+ Am +", DNI="+DNI+", fechaNacimiento="+ fechaNacimiento +", Sexo=" + Sexo + ", Direccion=" + Direccion + ", Telefono="+ Telefono + ", Correo=" + Correo +", Foto="+Foto+", Estado="+Estado+ ", Nit="+Nit + ", roles="+roles +", datos="+datos +"]";
	}*/
	@Override
	public String toString() {
		try {
			return new com.fasterxml.jackson.databind.ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
			
		} catch (com.fasterxml.jackson.core.JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
