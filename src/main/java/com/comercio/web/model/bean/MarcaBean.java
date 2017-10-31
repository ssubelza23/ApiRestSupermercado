package com.comercio.web.model.bean;


import javax.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public class MarcaBean {
	private String id;
	@Size(min = 3, max = 50, message = "Introduzca nombre válido")
	private String nombre;
	private MultipartFile logo;
	private int estado;
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
	public MultipartFile getLogo() {
		return logo;
	}
	public void setLogo(MultipartFile logo) {
		this.logo = logo;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}

	
	
	
}
