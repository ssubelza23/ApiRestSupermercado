package com.comercio.web.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
@Entity
@Table(name = "proveedores")
public class Proveedor implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String detalles;
	private int estado;

	


	public Usuario getContacto() {
		return contacto;
	}
	public void setContacto(Usuario contacto) {
		this.contacto = contacto;
	}



	@OneToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private Usuario contacto;
	public void setContactos(Usuario contactos) {
		this.contacto = contactos;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "empresa_id")
	private Empresa empresa;
	
	public Usuario getContactos() {
		return contacto;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	//===============================fin==============================================//
	public Proveedor(){}
	
	
	public Proveedor(String detalles,int estado){
		this.detalles=detalles;
		this.estado = estado;
	
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}





	public String getDetalles() {
		return detalles;
	}

	public void setDetalles(String detalles) {
		this.detalles = detalles;
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
