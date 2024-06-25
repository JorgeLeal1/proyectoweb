package com.proyectoweb.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.proyectoweb.web.dao.TransaccionDaoImp;
import com.proyectoweb.web.interfaces.TransaccionServiceInterface;
import com.proyectoweb.web.model.TransaccionModel;

@Service
public class TransaccionServiceImp implements TransaccionServiceInterface{

	private TransaccionDaoImp transaccionDaoImp;
	
	public TransaccionServiceImp(TransaccionDaoImp transaccionDaoImp) {
		this.transaccionDaoImp = transaccionDaoImp;
	}

	@Override
	public List<TransaccionModel> obtenerTodo() {
		return transaccionDaoImp.obtenerTodo();
	}

	@Override
	public List<TransaccionModel> obtenerPorId(int id) {
		return transaccionDaoImp.obtenerPorId(id);
	}

	@Override
	public void insertarTransaccion(TransaccionModel transaccion) {
		transaccionDaoImp.insertarTransaccion(transaccion);
	}




}
