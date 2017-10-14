package com.comercio.web.model;


public class response {
 private String plantilla;
 private String fragmento;
 private String msg;
 public String getMsg() {
	return msg;
}

public void setMsg(String msg) {
	this.msg = msg;
}
private boolean existe;
 private Usuario usuario;



public String getPlantilla() {
	return plantilla;
}

public Usuario getUsuario() {
	return usuario;
}

public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
}

public void setPlantilla(String plantilla) {
	this.plantilla = plantilla;
}
public String getFragmento() {
	return fragmento;
}
public void setFragmento(String fragmento) {
	this.fragmento = fragmento;
}
public boolean isExiste() {
	return existe;
}
public void setExiste(boolean existe) {
	this.existe = existe;
}
@Override
public String toString() {
	try {
		return new com.fasterxml.jackson.databind.ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
		
	} catch (com.fasterxml.jackson.core.JsonProcessingException e) {
		e.printStackTrace();
	}
	return null;
}
}