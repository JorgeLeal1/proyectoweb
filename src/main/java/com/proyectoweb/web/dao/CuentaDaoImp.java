package com.proyectoweb.web.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.proyectoweb.web.interfaces.CuentaDaoInterface;
import com.proyectoweb.web.model.ClienteModel;
import com.proyectoweb.web.model.CuentaModel;

@Repository
public class CuentaDaoImp implements CuentaDaoInterface {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<CuentaModel> obtenerTodoCuenta() {
		try {
		// selecciona todos los registros de la bd ordenado por run cliente
		String query = "SELECT id, nroCuenta, alias, banco, saldo, run_cliente "
						+ "FROM cuenta "
						+ "order by run_cliente";
				
		  List<CuentaModel> lista = jdbcTemplate.query(query,
				  (ResultSet rs, int rowNum) -> {
			
					//Crea objeto cliente
					ClienteModel cliente = new ClienteModel();
					cliente.setRun(rs.getString("run_cliente")); 
					
					CuentaModel cuenta = new CuentaModel();
					
					cuenta.setId(rs.getInt("id"));
					cuenta.setNroCuenta(rs.getInt("nroCuenta"));
					cuenta.setAlias(rs.getString("alias"));
					cuenta.setBanco(rs.getString("banco"));
					cuenta.setSaldo(rs.getDouble("saldo"));
					cuenta.setCliente(cliente);
					
					return cuenta;
				}); 

		  return lista;
		
		}catch(Exception ex) {
			System.out.println("Error: "+ ex.getMessage());
			return new ArrayList<>();
		}		
		
	}

	@Override
	public double consultarSaldoPorRun(String run) { 
		try {
	        String sql = "SELECT saldo "
		        		+ "FROM cuenta "
		        		+ "WHERE run_cliente = ? "
		        		+ "LIMIT 1";
	        Double saldo = jdbcTemplate.queryForObject(sql, Double.class, run);

	        return saldo; // retorna el saldo del cliente
		}catch(Exception ex) {
			System.out.println("Error: "+ ex.getMessage());
		}
		return 0.0;
	
	}
	
	@Override
	public boolean insertarCuenta(CuentaModel cuenta) { 
		try {
			//Inserta cuenta 
			String query = "INSERT INTO cuenta (nroCuenta, alias, banco, saldo, run_cliente) "
						+ "VALUES (?, ?, ?, ?, ?)";
	
			jdbcTemplate.update(query,
				cuenta.getNroCuenta(),
				cuenta.getAlias(),
				cuenta.getBanco(),
				cuenta.getSaldo(),
				cuenta.getCliente().getRun()
			);
			return true;
		}catch(Exception ex) {
			System.out.println("Error: "+ ex.getMessage());
			return false;
		}	
			//System.out.println("Cuenta creada correctamente.");
	}

	@Override
	public boolean actualizarCuenta(CuentaModel cuenta) { 
		return true;
	}

	@Override
	public boolean actualizarSaldoCuenta(String run, Double saldo) { 
		try {
			//Inserta cuenta 
			String query = "UPDATE cuenta "
						 + " SET saldo = ? "
						 + " WHERE run_cliente = ?";
	
			jdbcTemplate.update(query, saldo, run);
			
			return true;
		}catch(Exception ex) {
			System.out.println("Error: "+ ex.getMessage());
			return false;
		}	
		

	}
	
	@Override
	public boolean eliminarCuenta(int id) {
		return true;
	}

}
