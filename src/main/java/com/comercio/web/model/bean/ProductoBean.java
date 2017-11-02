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
	@NotNull(message="Codigo de barra, no puede estar vacio")
	private long codigoBarras;
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