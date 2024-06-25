package com.proyectoweb.web.interfaces;

import java.util.List;

import com.proyectoweb.web.model.UsuarioModel;

public interface UsuarioDaoInterface {

	public int validarLogin(UsuarioModel usuario);
	
	public List<UsuarioModel> obtenerTodo();

	public UsuarioModel consultarUsuarioPorRun(String run);

	public boolean insertarUsuario(UsuarioModel usuario);

	public boolean actualizarUsuario(UsuarioModel usuario);

	public boolean eliminarUsuario(int id);

}
