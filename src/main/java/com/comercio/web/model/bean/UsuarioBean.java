package com.comercio.web.model.bean;

import org.springframework.web.multipart.MultipartFile;

public class UsuarioBean {

	private String Nombre;

	private long DNI;

	private String Ap;

	private String Am;
	
	private long id_Rol;


	public long getId_Rol() {
		return id_Rol;
	}

	public void setId_Rol(long id_Rol) {
		this.id_Rol = id_Rol;
	}

	private String fechaNacimiento;

	private char Sexo;

	private String Direccion;

	private int Telefono;

	private String Correo;

	private MultipartFile Foto;

	private int Estado;

	private int Nit;
	
	private String Login;
	
	private String Clave;
	private String puestotrabajo;
	private String movil;
	public String getPuestotrabajo() {
		return puestotrabajo;
	}

	public void setPuestotrabajo(String puestotrabajo) {
		this.puestotrabajo = puestotrabajo;
	}

	public String getMovil() {
		return movil;
	}

	public void setMovil(String movil) {
		this.movil = movil;
	}

	private long id;
	private long id_dato;
	public long getId_dato() {
		return id_dato;
	}

	public void setId_dato(long id_dato) {
		this.id_dato = id_dato;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLogin() {
		return Login;
	}

	public void setLogin(String login) {
		Login = login;
	}

	public String getClave() {
		return Clave;
	}

	public void setClave(String clave) {
		Clave = clave;
	}

	public UsuarioBean(){}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public long getDNI() {
		return DNI;
	}

	public void setDNI(long DNI) {
		this.DNI = DNI;
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

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
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
		Correo = correo;
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
		Estado = estado;
	}

	public int getNit() {
		return Nit;
	}

	public void setNit(int nit) {
		Nit = nit;
	}
	
	


}
