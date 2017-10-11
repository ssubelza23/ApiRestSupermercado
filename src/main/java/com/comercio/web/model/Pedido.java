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

@Entity
@Table(name = "pedidos")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private Date fechaDeOrden;
	private Date fechaDeEntrega;
	private String estado;

	@OneToOne
	@JoinColumn(name = "id_proveedor")
	private Proveedor proveedor;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Producto> productos = new ArrayList<>();

	private float subTotal;

	public Pedido() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getFechaDeOrden() {
		return fechaDeOrden;
	}

	public void setFechaDeOrden(Date fechaDeOrden) {
		this.fechaDeOrden = fechaDeOrden;
	}

	public Date getFechaDeEntrega() {
		return fechaDeEntrega;
	}

	public void setFechaDeEntrega(Date fechaDeEntrega) {
		this.fechaDeEntrega = fechaDeEntrega;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public float getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(float subTotal) {
		this.subTotal = subTotal;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public List<Producto> getProductos() {
		return productos;
	}

}
