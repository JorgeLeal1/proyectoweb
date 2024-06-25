package com.proyectoweb.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.proyectoweb.web.interfaces.CuentaDaoInterface;
import com.proyectoweb.web.interfaces.CuentaTerceroDaoInterface;
import com.proyectoweb.web.interfaces.CuentaTerceroServiceInterface;
import com.proyectoweb.web.model.CuentaTerceroModel;

@Service
public class CuentaTerceroServiceImp implements CuentaTerceroServiceInterface{

	private CuentaTerceroDaoInterface cuentaTerceroDaoInterface;
	
	public CuentaTerceroServiceImp(CuentaTerceroDaoInterface cuentaTerceroDaoInterface) {
		this.cuentaTerceroDaoInterface = cuentaTerceroDaoInterface;
	}
	
	@Override
	public List<CuentaTerceroModel> obtenerTodoCuentaTercero(int Nrocuenta) {
		return cuentaTerceroDaoInterface.obtenerTodoCuentaTercero(Nrocuenta);
	}

	@Override
	public boolean insertarCuentaTercero(CuentaTerceroModel cuenta) {
		return cuentaTerceroDaoInterface.insertarCuentaTercero(cuenta);
	}

	@Override
	public boolean actualizarCuentaTercero(CuentaTerceroModel cuenta) {
		return cuentaTerceroDaoInterface.actualizarCuentaTercero(cuenta);
	}





}