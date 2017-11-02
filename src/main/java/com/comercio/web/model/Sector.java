package com.comercio.web.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Entity
@Table(name = "sectores")
public class Sector {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotNull(message="No puede estar vacio")
	@Size(min = 3, max = 50)
	@Column (unique=true)
	private String nombre;
	private String detalle;
	private String logo;
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	private int estado;

	public Sector() {
	}
	public Sector(String nombre,String detalle,String logo,int estado) {
		this.nombre=nombre;
		this.detalle=detalle;
		this.estado=estado;
	}
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

	public String getDetalle(){
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
