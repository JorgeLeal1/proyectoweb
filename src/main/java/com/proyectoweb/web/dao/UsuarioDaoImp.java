package com.proyectoweb.web.dao;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.proyectoweb.web.interfaces.UsuarioDaoInterface;
import com.proyectoweb.web.model.ClienteModel;
import com.proyectoweb.web.model.UsuarioModel;

@Repository
public class UsuarioDaoImp implements UsuarioDaoInterface{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//public int validarLogin(String run, String contrasena) { // se puede crear con variables independientes
	@Override
	public int validarLogin(UsuarioModel usuario) { 
		try {
			//System.out.println( "UsuarioDaoImp, Run : " + usuario.getCliente().getRun() + " contrasena: "+ usuario.getContrasena()); //muestra los valores que pasaron del controlador
			
	        String sql = "SELECT count(*) "
		        		+ "FROM usuario "
		        		+ "WHERE run_cliente = ? "
		        		+ "and contrasena = ?";
	        Integer rowCounts = jdbcTemplate.queryForObject(sql, Integer.class, usuario.getCliente().getRun() , usuario.getContrasena());
	        //System.out.println( "Cantidad de filas : " + rowCounts);
	        return rowCounts; // retorna la cantidad de coincidencias 0:sino hay o >0: cuando existe
		}catch(Exception ex) {
			System.out.println("Error: "+ ex.getMessage());
		}
		return 0;
	}

	@Override
	public List<UsuarioModel> obtenerTodo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UsuarioModel consultarUsuarioPorRun(String run) {
		try {
			
		// selecciona todos los registros de la bd ordenado por run cliente
		String query = "SELECT id, nombre, correo_electronico, contrasena, run_cliente "
						+ "FROM usuario "
						+ "where run_cliente = ?";
				
		UsuarioModel lista = jdbcTemplate.queryForObject(query,
				  (ResultSet rs, int rowNum) -> {
			
					//Crea objeto usuario
					UsuarioModel usuario = new UsuarioModel();
					usuario.setId(rs.getInt("id")); 
					usuario.setNombre(rs.getString("nombre"));
					usuario.setCorreo_electronico(rs.getString("correo_electronico"));
					usuario.setContrasena(rs.getString("contrasena"));

					return usuario;
				},
				  new Object[] {run}	
				); 

		  return lista;
		
		}catch(Exception ex) {
			System.out.println("Error: "+ ex.getMessage());
			return null;
		}	
	}

	@Override
	public boolean insertarUsuario(UsuarioModel usuario) {
		try {
			String query = "INSERT INTO usuario (nombre, correo_electronico, contrasena, run_cliente)"
							+ "VALUES (?, ?, ?, ?)";
			
			jdbcTemplate.update(query, 
					usuario.getNombre(), 
					usuario.getCorreo_electronico(),
					usuario.getContrasena(),
					usuario.getCliente().getRun()
					);
			return true;
		}catch(Exception ex) {
			System.out.println("Error: "+ ex.getMessage());
			return false;
		}
		//System.out.println("Usuario creado correctamente.");

	}

	@Override
	public boolean actualizarUsuario(UsuarioModel usuario) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean eliminarUsuario(int id) {
		// TODO Auto-generated method stub
		return true;
	}


	
}
