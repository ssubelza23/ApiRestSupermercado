package com.comercio.web.model.bean;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



public class SectorBean {
	private long id;
	@NotNull(message="No puede estar vacio")
	@Size(min = 3, max = 50,message="Introduzca nombre válido")
	@Column (unique=true)
	private String nombre;
	@NotNull(message="No puede estar vacio")
	@Size(min = 3, max = 50,message="Introduzca nombre válido")
	private String detalle;
	@NotNull(message="No puede estar vacio")
	@Size(min = 3, max = 50,message="Introduzca nombre válido")
	private String logo;
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
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
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalles) {
		this.detalle = detalles;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	
}
