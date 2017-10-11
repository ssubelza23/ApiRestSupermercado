package com.comercio.web.model.bean;


public class ProductoBean {

	private long id;
	private long codigoBarra;
	private String nombre;
	private int cantidad;
	private String medida;
	private String contenidoneto;
	private String detalles;
	private String presentacion;
	private String imagen;
	private String industria;
	private int estado;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCodigoBarra() {
		return codigoBarra;
	}
	public void setCodigoBarra(long codigoBarra) {
		this.codigoBarra = codigoBarra;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getMedida() {
		return medida;
	}
	public void setMedida(String medida) {
		this.medida = medida;
	}
	public String getContenidoneto() {
		return contenidoneto;
	}
	public void setContenidoneto(String contenidoneto) {
		this.contenidoneto = contenidoneto;
	}
	public String getDetalles() {
		return detalles;
	}
	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}
	public String getPresentacion() {
		return presentacion;
	}
	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public String getIndustria() {
		return industria;
	}
	public void setIndustria(String industria) {
		this.industria = industria;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	


}