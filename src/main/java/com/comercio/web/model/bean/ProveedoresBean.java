package com.comercio.web.model.bean;


public class ProveedoresBean {

private long id;
private long idempresa;
private long idmarca;
private long idcontacto;
private String detalles;
public long getId() {
	return id;
}
public long getIdempresa() {
	return idempresa;
}
public void setIdempresa(long idempresa) {
	this.idempresa = idempresa;
}
public long getIdmarca() {
	return idmarca;
}
public void setIdmarca(long idmarca) {
	this.idmarca = idmarca;
}
public long getIdcontacto() {
	return idcontacto;
}
public void setIdcontacto(long idcontacto) {
	this.idcontacto = idcontacto;
}
public String getDetalles() {
	return detalles;
}
public void setDetalles(String detalles) {
	this.detalles = detalles;
}
public void setId(long id) {
	this.id = id;
}


}