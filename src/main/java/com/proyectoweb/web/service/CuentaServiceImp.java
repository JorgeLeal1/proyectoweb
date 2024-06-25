package com.proyectoweb.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.proyectoweb.web.interfaces.CuentaDaoInterface;
import com.proyectoweb.web.interfaces.CuentaServiceInterface;
import com.proyectoweb.web.model.CuentaModel;

@Service
public class CuentaServiceImp implements CuentaServiceInterface{

	private CuentaDaoInterface cuentaDaoInterface;
	
	public CuentaServiceImp(CuentaDaoInterface cuentaDaoInterface) {
		this.cuentaDaoInterface = cuentaDaoInterface;
	}
	
	@Override
	public List<CuentaModel> obtenerTodoCuenta() {
		// TODO Auto-generated method stub
		return cuentaDaoInterface.obtenerTodoCuenta();
	}

	@Override
	public CuentaModel obtenerCuentaCliente(String run) {
		// TODO Auto-generated method stub
		return cuentaDaoInterface.obtenerCuentaCliente(run);
	}

	
	@Override
	public CuentaModel obtenerCuenta(String run) {
		// TODO Auto-generated method stub
		CuentaModel cuenta = cuentaDaoInterface.obtenerCuenta(run);
		return cuenta;
	}
	
	@Override
	public double consultarSaldoPorRun(String run) {
		// TODO Auto-generated method stub
		return cuentaDaoInterface.consultarSaldoPorRun(run);
	}

	@Override
	public boolean insertarCuenta(CuentaModel cuenta) {
		// TODO Auto-generated method stub
		return cuentaDaoInterface.insertarCuenta(cuenta);
		
	}

	@Override
	public boolean actualizarCuenta(CuentaModel cuenta) {
		// TODO Auto-generated method stub
		return cuentaDaoInterface.actualizarCuenta(cuenta);
		
	}

	@Override
	public boolean actualizarSaldoCuenta(String run, Double saldo) {
		// TODO Auto-generated method stub
		return cuentaDaoInterface.actualizarSaldoCuenta(run, saldo);
		
	}

	@Override
	public boolean eliminarCuenta(int id) {
		// TODO Auto-generated method stub
		return cuentaDaoInterface.eliminarCuenta(id);
		
	}



}
