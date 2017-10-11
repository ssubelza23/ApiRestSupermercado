package com.comercio.web.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "categorias")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String nombre;
	private String detalle;
	private String logo;
	private int estado;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Producto> productos = new ArrayList<>();

	
	public Categoria() {
	}
	public Categoria(String nombre,String detalle,String logo,int estado) {
		this.nombre=nombre;
		this.detalle=detalle;
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

	public void setDetalle(String detalle) {
		this.detalle = detalle;
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

	public List<Producto> getProdcutos() {
		return productos;
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
