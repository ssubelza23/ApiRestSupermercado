package com.comercio.web.model.bean;

import javax.validation.constraints.Size;

public class ProdcutoVentasBean {
	private long id;
	private String addStock;
	private String fechaVencimientoMod;
	private String costoMod;
	private String gananciaMod;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAddStock() {
		return addStock;
	}
	public void setAddStock(String addStock) {
		this.addStock = addStock;
	}
	public String getFechaVencimientoMod() {
		return fechaVencimientoMod;
	}
	public void setFechaVencimientoMod(String fechaVencimientoMod) {
		this.fechaVencimientoMod = fechaVencimientoMod;
	}
	public String getCostoMod() {
		return costoMod;
	}
	public void setCostoMod(String costoMod) {
		this.costoMod = costoMod;
	}
	public String getGananciaMod() {
		return gananciaMod;
	}
	public void setGananciaMod(String gananciaMod) {
		this.gananciaMod = gananciaMod;
	}
	
	
}
