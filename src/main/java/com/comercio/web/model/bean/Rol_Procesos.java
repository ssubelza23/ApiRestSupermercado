package com.comercio.web.model.bean;

import java.util.ArrayList;
import java.util.List;

public class Rol_Procesos {
	private long idRolModificar;
 private List<Long> eliminar=new ArrayList<>();
 private List<Long> añadir=new ArrayList<>();
public List<Long> getEliminar() {
	return eliminar;
}
public void setEliminar(List<Long> eliminar) {
	this.eliminar = eliminar;
}
public List<Long> getAñadir() {
	return añadir;
}
public void setAñadir(List<Long> añadir) {
	this.añadir = añadir;
}
public long getIdRolModificar() {
	return idRolModificar;
}
public void setIdRolModificar(long idRolModificar) {
	this.idRolModificar = idRolModificar;
}
	
}
