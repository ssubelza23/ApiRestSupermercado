package com.comercio.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "ventas")
public class Venta {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@CreationTimestamp
	private Date fechaAlta;

	private long numeroFactura;
	private Date fechaDeEmision;

	@OneToOne
	@JoinColumn(name = "id_cajero")
	private Usuario usuario;

	@OneToOne
	@JoinColumn(name = "id_cliente")
	private Usuario cliente;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Producto> productos = new ArrayList<>();

	private long totalACobrar;

	private String detalles;

	public long getTotalACobrar() {
		return totalACobrar;
	}

	public void setTotalACobrar(long totalACobrar) {
		this.totalACobrar = totalACobrar;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public long getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(long numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	public Date getFechaDeEmision() {
		return fechaDeEmision;
	}

	public void setFechaDeEmision(Date fechaDeEmision) {
		this.fechaDeEmision = fechaDeEmision;
	}

	public String getDetalles() {
		return detalles;
	}

	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Usuario getCliente() {
		return cliente;
	}

}
