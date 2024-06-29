package com.proyectoweb.web.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transaccion")
public class TransaccionModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "idusuarioenvia", referencedColumnName="id")	
	private UsuarioModel usuarioenvia;
	
	@ManyToOne
	@JoinColumn(name = "idusuariorecibe", referencedColumnName="id")		
	private UsuarioModel usuariorecibe;
	
	@Column(name="saldo")
	private double saldo;
	
	@Column(name="date")
	private Date date;
	
	@ManyToOne
	@JoinColumn(name = "idmoneda", referencedColumnName="id")	
	private MonedaModel moneda;
	
	@ManyToOne
	@JoinColumn(name = "idtipotransaccion", referencedColumnName="idtipotransaccion")		
	private TipoTransaccionModel tipotransaccion;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UsuarioModel getUsuarioenvia() {
		return usuarioenvia;
	}

	public void setUsuarioenvia(UsuarioModel usuarioenvia) {
		this.usuarioenvia = usuarioenvia;
	}

	public UsuarioModel getUsuariorecibe() {
		return usuariorecibe;
	}

	public void setUsuariorecibe(UsuarioModel usuariorecibe) {
		this.usuariorecibe = usuariorecibe;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public MonedaModel getMoneda() {
		return moneda;
	}

	public void setMoneda(MonedaModel moneda) {
		this.moneda = moneda;
	}

	public TipoTransaccionModel getTipotransaccion() {
		return tipotransaccion;
	}

	public void setTipotransaccion(TipoTransaccionModel tipotransaccion) {
		this.tipotransaccion = tipotransaccion;
	}

}
