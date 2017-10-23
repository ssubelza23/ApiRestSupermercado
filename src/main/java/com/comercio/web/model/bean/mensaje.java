package com.comercio.web.model.bean;

public class mensaje {
private boolean error=false;
private String mensaje;
public boolean isError() {
	return error;
}
public void setError(boolean error) {
	this.error = error;
}
public String getMensaje() {
	return mensaje;
}
public void setMensaje(String mensaje) {
	this.mensaje = mensaje;
}

}
