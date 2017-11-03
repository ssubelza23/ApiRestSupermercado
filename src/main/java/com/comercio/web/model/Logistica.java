package com.comercio.web.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "logisticaProductos")
public class Logistica  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private int stock;
	private float costo;
	private float porcentajeGanancia;
	private float precioVenta; ;
	private Date fechaVecimiento;
	private int estado;
	private int reglaAprovicionamiento;

	@CreationTimestamp
	private Date fechaAlta;
	

	

	
	public Logistica() {
	}
	public Logistica(int stock,float costo,float porcentajeGanancia,float precioVenta,Date fechaVencimiento, int estado) {
		this.stock=stock;
		this.costo=costo;
		this.porcentajeGanancia=porcentajeGanancia;
		this.precioVenta=precioVenta;
		this.fechaVecimiento=fechaVencimiento;
		
	}
	
	

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public float getCosto() {
		return costo;
	}
	public void setCosto(float costo) {
		this.costo = costo;
	}
	public float getPorcentajeGanancia() {
		return porcentajeGanancia;
	}
	public void setPorcentajeGanancia(float porcentajeGanancia) {
		this.porcentajeGanancia = porcentajeGanancia;
	}
	public float getPrecioVenta() {
		return precioVenta;
	}
	public void setPrecioVenta(float precioVenta) {
		this.precioVenta = precioVenta;
	}
	public Date getFechaVecimiento() {
		return fechaVecimiento;
	}
	public void setFechaVecimiento(Date fechaVecimiento) {
		this.fechaVecimiento = fechaVecimiento;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public int getReglaAprovicionamiento() {
		return reglaAprovicionamiento;
	}
	public void setReglaAprovicionamiento(int reglaAprovicionamiento) {
		this.reglaAprovicionamiento = reglaAprovicionamiento;
	}
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
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
