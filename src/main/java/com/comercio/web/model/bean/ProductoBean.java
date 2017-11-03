package com.comercio.web.model.bean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public class ProductoBean {

	private String id;
	@Size(min = 3, max = 50,message="Introduzca nombre del producto")
	private String nombre;
	@Size(min = 3, max = 50,message="Se requiere el contenido neto del producto")
	private String contenidoNeto;
	private String medida;
	@Size(min = 3, max = 50,message="Introduzca detalles del producto")
	private String detalles;
	@Size(min = 3, max = 50,message="Introduzca presentacion del producto")
	private String presentacion;
	private MultipartFile imagen;
	
	private String codigoBarras;
	private String industria;
	@NotNull(message="selecione un Proveedor")
	private String idProveedor;
	@NotNull(message="selecione una Categoria")
	private String idCategoria;
	@NotNull(message="selecione un Sector")
	private String idSector;
	
	
	
	
	
	
	
	public String getIdMarca() {
		return idMarca;
	}
	public void setIdMarca(String idMarca) {
		this.idMarca = idMarca;
	}
	@NotNull(message="selecione una marca")
	private String idMarca;
	@Size(min = 1, max = 50,message="defina el porcentaje de ganacia")
	private String porcentajeGanancia;
	@Size(min = 1, max = 50,message="defina el costo del producto")
	private String costo;
	@Size(min = 1, max = 50,message="Se requiere el precio de venta")
	private String precioVenta;
	
	private String fechaVencimiento;

	private String stock;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getContenidoNeto() {
		return contenidoNeto;
	}
	public void setContenidoNeto(String contenidoNeto) {
		this.contenidoNeto = contenidoNeto;
	}
	public String getMedida() {
		return medida;
	}
	public void setMedida(String medida) {
		this.medida = medida;
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
	public MultipartFile getImagen() {
		return imagen;
	}
	public void setImagen(MultipartFile imagen) {
		this.imagen = imagen;
	}
	public String getCodigoBarras() {
		return codigoBarras;
	}
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	public String getIndustria() {
		return industria;
	}
	public void setIndustria(String industria) {
		this.industria = industria;
	}
	public String getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(String idProveedor) {
		this.idProveedor = idProveedor;
	}
	public String getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(String idCategoria) {
		this.idCategoria = idCategoria;
	}
	public String getIdSector() {
		return idSector;
	}
	public void setIdSector(String idSector) {
		this.idSector = idSector;
	}
	public String getPorcentajeGanancia() {
		return porcentajeGanancia;
	}
	public void setPorcentajeGanancia(String porcentajeGanancia) {
		this.porcentajeGanancia = porcentajeGanancia;
	}
	public String getCosto() {
		return costo;
	}
	public void setCosto(String costo) {
		this.costo = costo;
	}
	public String getPrecioDeVenta() {
		return precioVenta;
	}
	public void setPrecioDeVenta(String precioDeVenta) {
		this.precioVenta = precioDeVenta;
	}
	public String getPrecioVenta() {
		return precioVenta;
	}
	public void setPrecioVenta(String precioVenta) {
		this.precioVenta = precioVenta;
	}
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}


	
}