package com.proyectoweb.web.interfaces;

import java.util.List;
import com.proyectoweb.web.model.ClienteModel;

public interface ClienteDaoInterface {
		
	public List<ClienteModel> obtenerTodo();

	public int consultarClientePorRun(String run);

	public boolean insertarCliente(ClienteModel cliente);

	public boolean actualizarCliente(ClienteModel cliente);

	public boolean eliminarCliente(String run);


}
