package com.comercio.web.model;

import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "sesiones")
public class Sesion {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@OneToOne
	@JoinColumn(name = "id_terminalDeVenta")
	private TerminalDeVenta termianalDeVenta;

	@OneToOne
	@JoinColumn(name = "id_Usuario")
	private Usuario usuario;

	@CreationTimestamp
	private Date fecha;

	private LocalTime horainicio;

	private LocalTime horaFin;

	private float montoDeApertura;

	private String Observaciones;

	private int estado;

	public Sesion() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public LocalTime getHorainicio() {
		return horainicio;
	}

	public void setHorainicio(LocalTime horainicio) {
		this.horainicio = horainicio;
	}

	public LocalTime getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(LocalTime horaFin) {
		this.horaFin = horaFin;
	}

	public float getMontoDeApertura() {
		return montoDeApertura;
	}

	public void setMontoDeApertura(float montoDeApertura) {
		this.montoDeApertura = montoDeApertura;
	}

	public String getObservaciones() {
		return Observaciones;
	}

	public void setObservaciones(String observaciones) {
		Observaciones = observaciones;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public TerminalDeVenta getTermianalDeVenta() {
		return termianalDeVenta;
	}

	public Usuario getUsuario() {
		return usuario;
	}

}
