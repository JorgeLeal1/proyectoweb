package com.proyectoweb.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.proyectoweb.web.interfaces.UsuarioDaoInterface;
import com.proyectoweb.web.interfaces.UsuarioServiceInterface;
import com.proyectoweb.web.model.UsuarioModel;

@Service
public class UsuarioServiceImp implements UsuarioServiceInterface{

	private UsuarioDaoInterface usuarioDaoInterface;
	
	public UsuarioServiceImp(UsuarioDaoInterface usuarioDaoInterface) {
		this.usuarioDaoInterface = usuarioDaoInterface;
	}

	@Override
	public int validarLogin(UsuarioModel usuario) {
		// TODO Auto-generated method stub
		return usuarioDaoInterface.validarLogin(usuario);
	}

	@Override
	public List<UsuarioModel> obtenerTodo() {
		// TODO Auto-generated method stub
		return usuarioDaoInterface.obtenerTodo();
	}

	@Override
	public int consultarUsuarioPorRun(String run) {
		// TODO Auto-generated method stub
		return usuarioDaoInterface.consultarUsuarioPorRun(run);
	}

	@Override
	public boolean insertarUsuario(UsuarioModel usuario) {
		// TODO Auto-generated method stub
		return usuarioDaoInterface.insertarUsuario(usuario);
		
	}

	@Override
	public boolean actualizarUsuario(UsuarioModel usuario) {
		// TODO Auto-generated method stub
		return usuarioDaoInterface.actualizarUsuario(usuario);
		
	}

	@Override
	public boolean eliminarUsuario(int id) {
		// TODO Auto-generated method stub
		return usuarioDaoInterface.eliminarUsuario(id);
		
	}
	


}
