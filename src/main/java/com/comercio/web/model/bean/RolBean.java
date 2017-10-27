package com.comercio.web.model.bean;



import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.comercio.web.model.Proceso;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


public class RolBean {

	private long id;
	@Size(min = 3, max = 50, message = "Introduzca nombre válido")
	@NotNull
	private String nombre;
	@NotNull
	@Size(min = 3, max = 50, message = "Introduzca nombre válido")
	private String descripcion;
	
	private List<Long> procesos=new ArrayList<>();
public List<Long> getProcesos() {
		return procesos;
	}

	public void setProcesos(List<Long> procesos) {
		this.procesos = procesos;
	}

public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	private Rol_Procesos rol_procesos;

	public Rol_Procesos getRol_procesos() {
		return rol_procesos;
	}

	public void setRol_procesos(Rol_Procesos rol_procesos) {
		this.rol_procesos = rol_procesos;
	}

	public RolBean() {
	}

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
