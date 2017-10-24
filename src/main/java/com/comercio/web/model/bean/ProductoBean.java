package com.comercio.web.model.bean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.web.multipart.MultipartFile;

public class ProductoBean {

	private String id;
	private String nombre;
	private String contenidoNeto;
	private String medida;
	private String detalle;
	private String presentacion;
	private MultipartFile imagen;
	@NotNull(message="Codigo de barra, no puede estar vacio")
	@NumberFormat(pattern="Codigo de barra, debe ser numerico")
	@Size(min = 3, max = 50,message="Introduzca nombre un codigo de barras válido")
	
	private long codigoBarras;
	private String industria;
	private long idProveedor;
	private long idCategoria;
	private long idSector;
	private String porcentajeGanancia;
	private String costo;
	private String precioDeVenta;
	private int cantidadMinima;
	private String medidaMinima;
	private String contenidoxcaja;
	private String medidacontenidoxcaja;
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
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
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
	public long getCodigoBarras() {
		return codigoBarras;
	}
	public void setCodigoBarras(long codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	public String getIndustria() {
		return industria;
	}
	public void setIndustria(String industria) {
		this.industria = industria;
	}
	public long getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(long idProveedor) {
		this.idProveedor = idProveedor;
	}
	public long getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(long idCategoria) {
		this.idCategoria = idCategoria;
	}
	public long getIdSector() {
		return idSector;
	}
	public void setIdSector(long idSector) {
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
		return precioDeVenta;
	}
	public void setPrecioDeVenta(String precioDeVenta) {
		this.precioDeVenta = precioDeVenta;
	}
	public int getCantidadMinima() {
		return cantidadMinima;
	}
	public void setCantidadMinima(int cantidadMinima) {
		this.cantidadMinima = cantidadMinima;
	}
	public String getMedidaMinima() {
		return medidaMinima;
	}
	public void setMedidaMinima(String medidaMinima) {
		this.medidaMinima = medidaMinima;
	}
	public String getContenidoxcaja() {
		return contenidoxcaja;
	}
	public void setContenidoxcaja(String contenidoxcaja) {
		this.contenidoxcaja = contenidoxcaja;
	}
	public String getMedidacontenidoxcaja() {
		return medidacontenidoxcaja;
	}
	public void setMedidacontenidoxcaja(String medidacontenidoxcaja) {
		this.medidacontenidoxcaja = medidacontenidoxcaja;
	}
	
	
}