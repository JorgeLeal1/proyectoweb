package com.proyectoweb.web.model;

import java.util.Date;

public class TransaccionModel {

	private int id;
	private UsuarioModel usuarioEnvia;
	private UsuarioModel usuarioRecibe;
	private double saldo;
	private Date date;
	private MonedaModel moneda;
	private TipoTransaccionModel tipoTransaccion;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public UsuarioModel getUsuarioEnvia() {
		return usuarioEnvia;
	}
	public void setUsuarioEnvia(UsuarioModel usuarioEnvia) {
		this.usuarioEnvia = usuarioEnvia;
	}
	public UsuarioModel getUsuarioRecibe() {
		return usuarioRecibe;
	}
	public void setUsuarioRecibe(UsuarioModel usuarioRecibe) {
		this.usuarioRecibe = usuarioRecibe;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public MonedaModel getMoneda() {
		return moneda;
	}
	public void setMoneda(MonedaModel moneda) {
		this.moneda = moneda;
	}
	public TipoTransaccionModel getTipoTransaccion() {
		return tipoTransaccion;
	}
	public void setTipoTransaccion(TipoTransaccionModel tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
		
	
}
