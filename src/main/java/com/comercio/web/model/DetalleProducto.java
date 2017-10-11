package com.comercio.web.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DetalleProdcutos")
public class DetalleProducto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@OneToOne
	@JoinColumn(name = "id_producto")
	private Producto productos;

	private int cantidadDisponible;

	private float costo;
	private float porcentajeGanacia;
	private float precioDeVenta;

	public DetalleProducto() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getCantidadDisponible() {
		return cantidadDisponible;
	}

	public void setCantidadDisponible(int cantidadDisponible) {
		this.cantidadDisponible = cantidadDisponible;
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

	public float getPorcentajeGanacia() {
		return porcentajeGanacia;
	}

	public void setPorcentajeGanacia(float porcentajeGanacia) {
		this.porcentajeGanacia = porcentajeGanacia;
	}

	public float getPrecioDeVenta() {
		return precioDeVenta;
	}

	public void setPrecioDeVenta(float precioDeVenta) {
		this.precioDeVenta = precioDeVenta;
	}

	public Producto getProductos() {
		return productos;
	}

}
