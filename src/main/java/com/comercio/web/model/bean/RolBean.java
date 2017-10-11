package com.comercio.web.model.bean;
public class RolBean {
private long id;
	public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
	private String nombre;
	private String descripcion;
	private Rol_Procesos rol_procesos;
	public Rol_Procesos getRol_procesos() {
		return rol_procesos;
	}
	public void setRol_procesos(Rol_Procesos rol_procesos) {
		this.rol_procesos = rol_procesos;
	}
	public RolBean(){}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
}
