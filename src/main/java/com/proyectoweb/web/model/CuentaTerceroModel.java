package com.proyectoweb.web.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cuentatercero")
public class CuentaTerceroModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idcuentatercero;
	
	@Column(name="nrocuentatercero")
	private Integer nrocuentatercero;	
	
	@ManyToOne
	@JoinColumn(name = "run_clientecuentatercero", referencedColumnName="run")
	private ClienteModel cliente;
	
	@ManyToOne
	@JoinColumn(name = "nrocuentaorigen", referencedColumnName="nrocuenta")
	private CuentaModel cuenta;
	
	@ManyToOne
	@JoinColumn(name = "run_clienteorigen", referencedColumnName="run")
	private ClienteModel clienteOrigen;
	
	public Integer getIdcuentatercero() {
		return idcuentatercero;
	}
	public void setIdcuentatercero(Integer idcuentatercero) {
		this.idcuentatercero = idcuentatercero;
	}
	public Integer getNrocuentatercero() {
		return nrocuentatercero;
	}
	public void setNrocuentatercero(Integer nrocuentatercero) {
		this.nrocuentatercero = nrocuentatercero;
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