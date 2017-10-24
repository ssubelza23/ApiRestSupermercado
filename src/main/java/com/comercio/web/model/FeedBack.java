package com.comercio.web.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.ObjectError;

public class FeedBack {

	private List<ObjectError> lista_errores=new ArrayList<>();
	private String mensaje;
	public List<ObjectError> getLista_errores() {
		return lista_errores;
	}
	public void setLista_errores(List<ObjectError> lista_errores) {
		this.lista_errores = lista_errores;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
}
