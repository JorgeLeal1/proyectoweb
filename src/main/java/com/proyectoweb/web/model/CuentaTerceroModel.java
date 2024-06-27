package com.proyectoweb.web.model;

public class CuentaTerceroModel {

	private int idCuentaTercero;
	private int nroCuentaTercero;	
	private ClienteModel cliente;
	private CuentaModel cuenta;
	private ClienteModel clienteOrigen;
	

	public int getIdCuentaTercero() {
		return idCuentaTercero;
	}
	public void setIdCuentaTercero(int idCuentaTercero) {
		this.idCuentaTercero = idCuentaTercero;
	}
	public int getNroCuentaTercero() {
		return nroCuentaTercero;
	}
	public void setNroCuentaTercero(int nroCuentaTercero) {
		this.nroCuentaTercero = nroCuentaTercero;
	}
	public ClienteModel getCliente() {
		return cliente;
	}
	public void setCliente(ClienteModel cliente) {
		this.cliente = cliente;
	}
	public CuentaModel getCuenta() {
		return cuenta;
	}
	public void setCuenta(CuentaModel cuenta) {
		this.cuenta = cuenta;
	}

	public ClienteModel getClienteOrigen() {
		return clienteOrigen;
	}
	public void setClienteOrigen(ClienteModel clienteOrigen) {
		this.clienteOrigen = clienteOrigen;
	}
	
	
	
}