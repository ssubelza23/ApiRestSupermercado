package com.comercio.web.model.bean;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.springframework.web.multipart.MultipartFile;

public class UsuarioBean {
	private String id;
	@Size(min = 3, max = 50,message="Introduzca nombre válido")
	private String nombre;
	private String am;
	@Size(min = 3, max = 50,message="Introduzca apellido válido")
	private String ap;
	@NotNull(message="Verifique datos de DNI")
	@Min(100000000)
	private long dni;
	@NotNull(message="Seleccione un ROl")
	private long id_rol;
	private String fechaNacimiento;
	private char sexo;
	private String direccion;
	private String telefono;
	@Email
	private String correo;
	private long idDato;
	private String login;
	private String clave;
	private String nit;
	private MultipartFile foto;
	private String puestoTrabajo;
	private String movil;
	
	
	public String getPuestoTrabajo() {
		return puestoTrabajo;
	}
	public void setPuestoTrabajo(String puestoTrabajo) {
		this.puestoTrabajo = puestoTrabajo;
	}
	public String getMovil() {
		return movil;
	}
	public void setMovil(String movil) {
		this.movil = movil;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getAm() {
		return am;
	}
	public void setAm(String am) {
		this.am = am;
	}
	public String getAp() {
		return ap;
	}
	public void setAp(String ap) {
		this.ap = ap;
	}
	public long getDni() {
		return dni;
	}
	public void setDni(long dni) {
		this.dni = dni;
	}
	public long getId_rol() {
		return id_rol;
	}
	public void setId_rol(long id_rol) {
		this.id_rol = id_rol;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public long getIdDato() {
		return idDato;
	}
	public void setIdDato(long idDato) {
		this.idDato = idDato;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getNit() {
		return nit;
	}
	public void setNit(String nit) {
		this.nit = nit;
	}
	public MultipartFile getFoto() {
		return foto;
	}
	public void setFoto(MultipartFile foto) {
		this.foto = foto;
	}
	
	
	
}
