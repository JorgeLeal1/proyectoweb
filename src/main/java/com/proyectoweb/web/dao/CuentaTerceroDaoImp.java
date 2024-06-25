package com.proyectoweb.web.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.proyectoweb.web.interfaces.CuentaTerceroDaoInterface;
import com.proyectoweb.web.model.ClienteModel;
import com.proyectoweb.web.model.CuentaModel;
import com.proyectoweb.web.model.CuentaTerceroModel;

@Repository
public class CuentaTerceroDaoImp implements CuentaTerceroDaoInterface {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	//obtiene el listado por la cuenta de origen
	@Override
	public List<CuentaTerceroModel> obtenerTodoCuentaTercero(int NroCuenta) {
		try {
		// selecciona todos los registros de la bd ordenado por run cliente
		String query = "select nombre1, appaterno, nroCuentaTercero, alias, banco "
						+ "from cuentatercero cu  "
						+ "inner join cliente cl "
						+ "on cu.run_clienteCuentaTercero = cl.run "
						+ "inner join cuenta c  "
						+ "on c.nroCuenta = cu.nroCuentaTercero "
						+ "where cu.nroCuentaOrigen = ?";
				
		  List<CuentaTerceroModel> lista = jdbcTemplate.query(query,
				  (ResultSet rs, int rowNum) -> {
			
					CuentaTerceroModel cuentaTercero = new CuentaTerceroModel();
					cuentaTercero.setNroCuentaTercero(rs.getInt("nroCuentaTercero"));
						
					ClienteModel cliente = new ClienteModel();
					cliente.setNombre1(rs.getString("nombre1"));
					cliente.setAppaterno(rs.getString("appaterno"));
					
			        CuentaModel cuenta = new CuentaModel();
					cuenta.setAlias(rs.getString("alias"));
					cuenta.setBanco(rs.getString("banco"));
			        	
					cuentaTercero.setCliente(cliente);
					cuentaTercero.setCuenta(cuenta);
					
					return cuentaTercero;
					
				},
				  new Object[] {NroCuenta}	  
				); 

		  return lista;
		
		}catch(Exception ex) {
			System.out.println("Error: "+ ex.getMessage());
			return new ArrayList<>();
		}	
		
	}

	@Override
	public boolean insertarCuentaTercero(CuentaTerceroModel cuentaTercero) {
		try {
			//Inserta cliente 
			String query = "INSERT INTO cuentatercero (nroCuentaTercero, run_clienteCuentaTercero, nroCuentaOrigen) "
							+ "VALUES (?, ?, ?)";
	
			jdbcTemplate.update(query,
					cuentaTercero.getNroCuentaTercero(),
					cuentaTercero.getCliente().getRun(),
					cuentaTercero.getCuenta().getNroCuenta()
			);
			//System.out.println("CuentaTercero creado correctamente.");
			return true;
			
		}catch(Exception ex) {
			System.out.println("Error1: "+ ex.getMessage());
			return false;
		}
	}

	@Override
	public boolean actualizarCuentaTercero(CuentaTerceroModel cuenta) {
		// TODO Auto-generated method stub
		return false;
	}
	
}