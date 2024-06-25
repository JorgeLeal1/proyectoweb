package com.proyectoweb.web.interfaces;

import java.util.List;
import com.proyectoweb.web.model.CuentaTerceroModel;

public interface CuentaTerceroServiceInterface {

	public List<CuentaTerceroModel> obtenerTodoCuentaTercero(int Nrocuenta);

	public boolean insertarCuentaTercero(CuentaTerceroModel cuenta);
	
	public boolean actualizarCuentaTercero(CuentaTerceroModel cuenta);

}
