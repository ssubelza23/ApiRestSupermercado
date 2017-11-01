package com.comercio.web.model.bean;

import javax.validation.constraints.Size;

public class ContactosBean {
	private String id;
	@Size(min = 3, max = 50, message = "Introduzca nombre válido")
	private String nombre;
	@Size(min = 3, max = 50, message = "Se requiere Apellido paterno")
	private String ap;
	@Size(min = 3, max = 50, message = "Se requiere Apellido materno")
	private String am;
	@Size(min = 7, max = 50, message = "Se requiere Movil")
	private String movil;
	
	private char sexo;
	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	public String getId() {
		return id;
	}
	@Size(min = 9, max = 50, message = "Se requiere DNI valido")
	private String dni;
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
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
	public String getAp() {
		return ap;
	}
	public void setAp(String ap) {
		this.ap = ap;
	}
	public String getAm() {
		return am;
	}
	public void setAm(String am) {
		this.am = am;
	}
	public String getMovil() {
		return movil;
	}
	public void setMovil(String movil) {
		this.movil = movil;
	}
	
	
}
