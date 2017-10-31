package com.comercio.web.model;




import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;



@Entity
@Table(name = "empresas")
public class Empresa implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(unique=true)
	private String nombre;
	private String direccioncomercial;
	private int telefono;
	private String correo;
	private String sitioweb;
	private String fax;
	private String detalle;
	public String getEspecialidades() {
		return especialidades;
	}
	public void setEspecialidades(String especialidades) {
		this.especialidades = especialidades;
	}
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	public String getSede() {
		return sede;
	}
	public void setSede(String sede) {
		this.sede = sede;
	}


	private String especialidades;
	private String sector;
	private String sede;
	private int estado;
public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccioncomercial() {
		return direccioncomercial;
	}
	public void setDireccioncomercial(String direccioncomercial) {
		this.direccioncomercial = direccioncomercial;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getSitioweb() {
		return sitioweb;
	}
	public void setSitioweb(String sitioweb) {
		this.sitioweb = sitioweb;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
public Empresa() {
	
}
public Empresa(String nombre,String direccioncomercial,int telefono,String correo,String sitioweb,
		String fax,String detalle,String especialidades,String sector,String sede,int estado) {
	this.nombre=nombre;
	this.direccioncomercial=direccioncomercial;
	this.telefono=telefono;
	this.correo=correo;
	this.sitioweb=sitioweb;
	this.fax=fax;
	this.detalle=detalle;
	this.especialidades=especialidades;
this.sector=sector;
this.sede=sede;
	
	this.estado=estado;
	
}


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
