package com.comercio.web.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.envers.Audited;



@Entity
@Table(name = "datos")
@Audited
public class Dato implements Serializable{
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	@ColumnTransformer(write=" MD5(?) ")
	private String login;
	
	@ColumnTransformer(write=" MD5(?) ")
	private String clave;


    
	public Dato(String login,String clave,Usuario usuario){
	this.login=login;
	this.clave=clave;
	}
	
	public Dato() {
	}
	public Dato( String login,String clave) {
		this.login=login;
		this.clave=clave;
		
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}


/*	@Override
	public String toString() {
		return "dato [id=" + id + ", login=" + login + ", clave="+ clave +", usuario="+ usuario + "]";
	}
*/
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
