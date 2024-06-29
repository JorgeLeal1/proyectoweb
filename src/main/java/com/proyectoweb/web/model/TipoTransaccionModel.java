package com.proyectoweb.web.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipotransaccion")
public class TipoTransaccionModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idtipotransaccion;
	
	@Column(name="tipotransaccion")
	private String tipotransaccion;

	public Integer getIdtipotransaccion() {
		return idtipotransaccion;
	}

	public void setIdtipotransaccion(Integer idtipotransaccion) {
		this.idtipotransaccion = idtipotransaccion;
	}

	public String getTipotransaccion() {
		return tipotransaccion;
	}

	public void setTipotransaccion(String tipotransaccion) {
		this.tipotransaccion = tipotransaccion;
	}
	


}
