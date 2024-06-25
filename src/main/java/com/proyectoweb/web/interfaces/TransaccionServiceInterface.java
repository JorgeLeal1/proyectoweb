package com.proyectoweb.web.interfaces;

import java.util.List;

import com.proyectoweb.web.model.TransaccionModel;

public interface TransaccionServiceInterface {

	public List<TransaccionModel> obtenerTodo();
	
	public List<TransaccionModel> obtenerPorId(int id);

	public void insertarTransaccion(TransaccionModel transaccion);

}
