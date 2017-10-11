package com.comercio.web.model.bean;



public class CategoriaBean {
	private long id;
	private String nombre;
	private String detalle;
	private String logo;
	private int estado;
	private long id_producto;
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
	public long getId_producto() {
		return id_producto;
	}
	public void setId_producto(long id_producto) {
		this.id_producto = id_producto;
	}
	
}
