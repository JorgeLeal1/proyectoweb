package com.proyectoweb.web.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.proyectoweb.web.interfaces.TransaccionDaoInterface;
import com.proyectoweb.web.model.MonedaModel;
import com.proyectoweb.web.model.TipoTransaccionModel;
import com.proyectoweb.web.model.TransaccionModel;
import com.proyectoweb.web.model.UsuarioModel;

@Repository
public class TransaccionDaoImp implements TransaccionDaoInterface{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<TransaccionModel> obtenerTodo() {
		try {
		// selecciona todos los registros de la bd ordenado por run cliente
		String query = "select t.saldo, t.date, uv.nombre as nombreEnv, uv.run_cliente as runEnv, ur.nombre as nombreRec, ur.run_cliente as runRec, m.nombre, m.signo, tt.tipotransaccion "
				+ "from transaccion t "
				+ "inner join usuario uv "
				+ "on t.idUsuarioEnvia = uv.id "
				+ "inner join usuario ur "
				+ "on t.idUsuarioRecibe = ur.id "
				+ "inner join moneda m "
				+ "on t.idMoneda = m.id "
				+ "inner join tipoTransaccion tt "
				+ "on t.idTipoTransaccion = tt.idTipoTransaccion"
				+ "order by t.date desc";
				
		  List<TransaccionModel> lista = jdbcTemplate.query(query,
				  (ResultSet rs, int rowNum) -> {
			
					//Crea objetos
					TransaccionModel transaccion = new TransaccionModel();
					transaccion.setSaldo(rs.getDouble("saldo"));
					transaccion.setDate(rs.getDate("date"));
					
					UsuarioModel usuarioEnv = new UsuarioModel();
					usuarioEnv.setNombre(rs.getString("nombreEnv"));
					
					UsuarioModel usuarioRec = new UsuarioModel();
					usuarioRec.setNombre(rs.getString("nombreRec"));
					
					MonedaModel moneda = new MonedaModel();
					moneda.setNombre(rs.getString("nombre"));
					moneda.setSigno(rs.getString("signo"));
					
					TipoTransaccionModel tipotransaccion = new TipoTransaccionModel();
					tipotransaccion.setTipoTransaccion(rs.getString("tipotransaccion"));
					
					transaccion.setUsuarioEnvia(usuarioEnv);
					transaccion.setUsuarioRecibe(usuarioRec);
					transaccion.setMoneda(moneda);
					transaccion.setTipoTransaccion(tipotransaccion);
					
					return transaccion;
				}); 

		  return lista;
		
		}catch(Exception ex) {
			System.out.println("Error: "+ ex.getMessage());
			return new ArrayList<>();
		}	
	}

	@Override
	public List<TransaccionModel> obtenerPorId(int id) {
		try {
		// selecciona todos los registros de la bd ordenado por run cliente
		String query = "select t.saldo, t.date, uv.nombre as nombreEnv, uv.run_cliente as runEnv, ur.nombre as nombreRec, ur.run_cliente as runRec, m.nombre, m.signo, tt.tipotransaccion "
				+ "from transaccion t "
				+ "inner join usuario uv "
				+ "on t.idUsuarioEnvia = uv.id "
				+ "inner join usuario ur "
				+ "on t.idUsuarioRecibe = ur.id "
				+ "inner join moneda m "
				+ "on t.idMoneda = m.id "
				+ "inner join tipoTransaccion tt "
				+ "on t.idTipoTransaccion = tt.idTipoTransaccion "
				+ "where t.idUsuarioEnvia = ? or t.idUsuarioRecibe = ? "
				+ "order by t.date desc";
				
		  List<TransaccionModel> lista = jdbcTemplate.query(query,
				  (ResultSet rs, int rowNum) -> {
			
					//Crea objetos
					TransaccionModel transaccion = new TransaccionModel();
					transaccion.setSaldo(rs.getDouble("saldo"));
					transaccion.setDate(rs.getDate("date"));
					
					UsuarioModel usuarioEnv = new UsuarioModel();
					usuarioEnv.setNombre(rs.getString("nombreEnv"));
					
					UsuarioModel usuarioRec = new UsuarioModel();
					usuarioRec.setNombre(rs.getString("nombreRec"));
					
					MonedaModel moneda = new MonedaModel();
					moneda.setNombre(rs.getString("nombre"));
					moneda.setSigno(rs.getString("signo"));
					
					TipoTransaccionModel tipotransaccion = new TipoTransaccionModel();
					tipotransaccion.setTipoTransaccion(rs.getString("tipotransaccion"));
					
					transaccion.setUsuarioEnvia(usuarioEnv);
					transaccion.setUsuarioRecibe(usuarioRec);
					transaccion.setMoneda(moneda);
					transaccion.setTipoTransaccion(tipotransaccion);
					
					return transaccion;
				},
				  new Object[] {id, id}	    
				); 

		  return lista;
		
		}catch(Exception ex) {
			System.out.println("Error: "+ ex.getMessage());
			return new ArrayList<>();
		}	
	}

	@Override
	public void insertarTransaccion(TransaccionModel transaccion) {

		try {
			//Inserta cliente 
			String query = "INSERT INTO transaccion (idUsuarioEnvia, idUsuarioRecibe, saldo, date ,idMoneda, idTipoTransaccion) "
							+ "VALUES (?, ?, ?, ?, ?, ?)";
	
			jdbcTemplate.update(query,
					transaccion.getUsuarioEnvia().getId(),
					transaccion.getUsuarioRecibe().getId(),
					transaccion.getSaldo(),
					transaccion.getDate(),
					transaccion.getMoneda().getId(),
					transaccion.getTipoTransaccion().getIdTipoTransaccion()
					
			);
			//System.out.println("transaccion creado correctamente.");
			
		}catch(Exception ex) {
			System.out.println("Error1: "+ ex.getMessage());
		
		}
		
	}
	


	
}
