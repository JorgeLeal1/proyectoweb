package com.proyectoweb.web.interfaces;

import java.util.List;

import com.proyectoweb.web.model.CuentaModel;

public interface CuentaDaoInterface {

	public List<CuentaModel> obtenerTodoCuenta();
	
	CuentaModel obtenerCuentaCliente(String run);
	
	CuentaModel obtenerCuenta(String run);

	public double consultarSaldoPorRun(String run);

	public boolean insertarCuenta(CuentaModel cuenta);

	public boolean actualizarCuenta(CuentaModel cuenta);
	
	public boolean actualizarSaldoCuenta(String run, Double saldo);

	public boolean eliminarCuenta(int id);
}
