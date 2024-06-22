package com.proyectoweb.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.proyectoweb.web.interfaces.ClienteDaoInterface;
import com.proyectoweb.web.interfaces.ClienteServiceInterface;
import com.proyectoweb.web.model.ClienteModel;

@Service
public class ClienteServiceImp implements ClienteServiceInterface{

	private ClienteDaoInterface clienteDaoInterface;
	
	public ClienteServiceImp(ClienteDaoInterface clienteDaoInterface) {
		this.clienteDaoInterface = clienteDaoInterface;
	}
	
	@Override
	public List<ClienteModel> obtenerTodo() {
		// TODO Auto-generated method stub
		return clienteDaoInterface.obtenerTodo();
	}

	@Override
	public int consultarClientePorRun(String run) {
		// TODO Auto-generated method stub
		return clienteDaoInterface.consultarClientePorRun(run);
	}

	@Override
	public boolean insertarCliente(ClienteModel cliente) {
		return clienteDaoInterface.insertarCliente(cliente);
		
	}

	@Override
	public boolean actualizarCliente(ClienteModel cliente) {
		return clienteDaoInterface.actualizarCliente(cliente);
		
	}

	@Override
	public boolean eliminarCliente(String run) {
		return clienteDaoInterface.eliminarCliente(run);
		
	}


}
