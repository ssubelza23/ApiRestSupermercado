package com.comercio.web.model.bean;

import javax.validation.constraints.Size;

public class ProcesoBean {
	@Size(min = 3, max = 50, message = "Introduzca nombre válido")
	private String nombre;
	@Size(min = 3, max = 50, message = "Se requiere una descripcion")
	private String descripcion;
	@Size(min = 3, max = 50, message = "Introduzca un enlace valido")
	private String enlace;
	private String logo;
	private long id;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEnlace() {
		return enlace;
	}
	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public ProcesoBean(){}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
}
