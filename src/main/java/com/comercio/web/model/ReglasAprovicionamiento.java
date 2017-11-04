package com.comercio.web.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "marcas")
@Audited
public class ReglasAprovicionamiento  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(unique=true)
	private String nombre;
	private String detalle;
	private String logo;
	private int estado;
	

	
	public ReglasAprovicionamiento() {
	}
	public ReglasAprovicionamiento(String nombre,String detalles,String logo,int estado) {
		this.nombre=nombre;
		this.detalle=detalles;
		this.logo=logo;
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

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalles) {
		this.detalle = detalles;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
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
