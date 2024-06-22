package com.proyectoweb.web.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.proyectoweb.web.interfaces.ClienteDaoInterface;
import com.proyectoweb.web.model.ClienteModel;

@Repository
public class ClienteDaoImp implements ClienteDaoInterface{


	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<ClienteModel> obtenerTodo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int consultarClientePorRun(String run) {
		try {
	        String sql = "SELECT count(*) "
		        		+ "FROM cliente "
		        		+ "WHERE run = ? ";
	        Integer rowCounts = jdbcTemplate.queryForObject(sql, Integer.class, run);
	        //System.out.println( "Cantidad de filas : " + rowCounts);
	        return rowCounts; // retorna la cantidad de coincidencias 0:sino hay o >0: cuando existe
		}catch(Exception ex) {
			System.out.println("Error: "+ ex.getMessage());
		}
		return 0;
	}

	@Override
	public boolean insertarCliente(ClienteModel cliente) {
		try {
			//Inserta cliente 
			String query = "INSERT INTO cliente (run, nombre1, nombre2, appaterno, apmaterno) "
							+ "VALUES (?, ?, ?, ?, ?)";
	
			jdbcTemplate.update(query,
				cliente.getRun(),
				cliente.getNombre1(),
				cliente.getNombre2(),
				cliente.getAppaterno(),
				cliente.getApmaterno()
			);
			//System.out.println("Cliente creado correctamente.");
			return true;
			
		}catch(Exception ex) {
			System.out.println("Error1: "+ ex.getMessage());
			return false;
		}

	}

	@Override
	public boolean actualizarCliente(ClienteModel cliente) {
		// TODO Auto-generated method stub
		return true;
		
	}

	@Override
	public boolean eliminarCliente(String run) {
		// TODO Auto-generated method stub
		return true;
		
	}

}
