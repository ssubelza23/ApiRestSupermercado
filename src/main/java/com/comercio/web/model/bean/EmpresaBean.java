package com.comercio.web.model.bean;

import javax.validation.constraints.Size;

public class EmpresaBean {
	private long id;
	@Size(min = 3, max = 50, message = "Introduzca nombre válido")
	private String nombre;
	private String direccioncomercial;
	private String corre;
	private String sitioweb;
	private int telefono;
	private String fax;
	private String detalles;
	@Size(min = 3, max = 50, message = "Introduzca la especialidad de la empresa")
	private String especialidades;
	@Size(min = 3, max = 50, message = "Se reuiqere el sector de la empresa")
	private String sector;
	private String sede;
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
	public String getCorre() {
		return corre;
	}
	public void setCorre(String corre) {
		this.corre = corre;
	}
	public String getSitioweb() {
		return sitioweb;
	}
	public void setSitioweb(String sitioweb) {
		this.sitioweb = sitioweb;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getDetalles() {
		return detalles;
	}
	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}
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
	
	
	
}
