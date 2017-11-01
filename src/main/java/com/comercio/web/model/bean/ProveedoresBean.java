package com.comercio.web.model.bean;



import javax.validation.constraints.NotNull;

public class ProveedoresBean {

private String id;
@NotNull(message="selecione una empresa")
private String idempresa;
@NotNull(message="selecione una marca")
private String idmarca;
@NotNull(message="selecione un contacto")
private String idcontacto;

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public String getIdempresa() {
	return idempresa;
}

public void setIdempresa(String idempresa) {
	this.idempresa = idempresa;
}

public String getIdmarca() {
	return idmarca;
}

public void setIdmarca(String idmarca) {
	this.idmarca = idmarca;
}

public String getIdcontacto() {
	return idcontacto;
}

public void setIdcontacto(String idcontacto) {
	this.idcontacto = idcontacto;
}



}