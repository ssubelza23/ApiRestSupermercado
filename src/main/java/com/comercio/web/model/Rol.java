package com.comercio.web.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import javax.persistence.Table;

import org.hibernate.envers.Audited;



@Entity
@Table(name = "roles")
@Audited
public class Rol implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(unique=true)
	private String nombre;
	private String descripcion;
	private int estado;

	@ManyToMany
	private List<Proceso> procesos = new ArrayList<>();

	public void setProcesos(List<Proceso> procesos) {
		this.procesos = procesos;
	}

	public List<Proceso> getProcesos() {
		return procesos;
	}

	public Rol() {
	}

	public Rol(String nombre, String descripcion, int estado) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.estado = estado;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public void addProceso(Proceso proceso) {
		procesos.add(proceso);

	}

	public void removeProceso(Proceso proceso) {
		procesos.remove(proceso);

	}
	@Override
	public String toString() {
		try {
			return new com.fasterxml.jackson.databind.ObjectMapper().writerWithDefaultPrettyPrinter()
					.writeValueAsString(this);

		} catch (com.fasterxml.jackson.core.JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
