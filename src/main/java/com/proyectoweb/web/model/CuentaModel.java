package com.proyectoweb.web.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;

@Entity
@Table(name = "cuenta")
public class CuentaModel {
	
	@Id
	private Integer nrocuenta;
	
	@Column(name="alias")
	private String alias;
	
	@Column(name="Banco")
	private String Banco;
	
	@Column(name="saldo")
	private double saldo;	

	@ManyToOne
	@JoinColumn(name = "run_cliente", referencedColumnName="run")
	private ClienteModel cliente;
	
	// Metodos GET, SET
	// ---------------------------------------------

	public String getAlias() {
		return alias;
	}
	
	public Integer getNrocuenta() {
		return nrocuenta;
	}


	public void setNrocuenta(Integer nrocuenta) {
		this.nrocuenta = nrocuenta;
	}


	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	public String getBanco() {
		return Banco;
	}

	public void setBanco(String banco) {
		Banco = banco;
	}
	
	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	public ClienteModel getCliente() {
		return cliente;
	}

	public void setCliente(ClienteModel cliente) {
		this.cliente = cliente;
	}

}
