package com.proyectoweb.web.interfaces;

import java.util.List;
import com.proyectoweb.web.model.CuentaTerceroModel;

public interface CuentaTerceroDaoInterface {

	public List<CuentaTerceroModel> obtenerTodoCuentaTercero(int Nrocuenta);
	
	public List<CuentaTerceroModel> obtenerTodoCuentaTerceroNombre(int Nrocuenta, String Nombre);

	public boolean insertarCuentaTercero(CuentaTerceroModel cuenta);
	
	public boolean actualizarCuentaTercero(CuentaTerceroModel cuenta);

}
