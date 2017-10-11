package com.comercio.web.model;

import java.io.Serializable;

import java.util.Date;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;



@Entity
@Table(name = "productos")
public class Producto implements Serializable{

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	@CreationTimestamp
	private Date fechaAlta;
	
	
	public Producto() {
	}

	public Producto(long codigobarra,String nombre,int cantidad,String medida,String contenidoneto,
			String detalles,String presentacion,String imagen,String industria) {
		this.codigoBarra=codigobarra;
		this.nombre=nombre;
		this.cantidad=cantidad;
		this.medida=medida;
		this.contenidoneto=contenidoneto;
		this.detalles=detalles;
		this.presentacion=presentacion;
		this.imagen=imagen;
		this.industria=industria;
		
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

	public String getIndustria() {
		return industria;
	}

	public void setIndustria(String industria) {
		this.industria = industria;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCodigoBarra() {
		return codigoBarra;
	}

	public void setCodigoBarra(int codigoBarra) {
		this.codigoBarra = codigoBarra;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFoto() {
		return imagen;
	}

	public void setFoto(String imagen) {
		this.imagen = imagen;
	}

	public String getDetalle() {
		return detalles;
	}

	public void setDetalle(String detalles) {
		this.detalles = detalles;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

}
