package com.proyectoweb.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.proyectoweb.web.model.CuentaTerceroModel;

public interface CuentaTerceroRepository extends JpaRepository<CuentaTerceroModel, Integer> {	
	
	@Query("SELECT c FROM CuentaTerceroModel c WHERE c.cuenta.nrocuenta=:NroCuenta")
	List<CuentaTerceroModel> obtenerTodoCuentaTercero(@Param("NroCuenta") Integer NroCuenta);
	
	@Query("SELECT c FROM CuentaTerceroModel c WHERE c.cuenta.nrocuenta=:NroCuenta and c.cliente.nombre1 LIKE :Nombre")
	List<CuentaTerceroModel> obtenerTodoCuentaTerceroNombre(@Param("NroCuenta") Integer NroCuenta, @Param("Nombre") String Nombre);
	
	@Query("SELECT count(*) "
			+ "FROM CuentaTerceroModel c "
			+ "WHERE c.nrocuentatercero=:nrocuentatercero "
			+ "and c.cliente.run=:run "
			+ "and c.cuenta.nrocuenta=:nrocuenta "
			+ "and c.clienteOrigen.run=:runOrigen ")
	public int verificarCuentaTercero(@Param("nrocuentatercero") Integer nrocuentatercero, 
									  @Param("run") String run,
									  @Param("nrocuenta") Integer nrocuenta,
									  @Param("runOrigen") String runOrigen
									  );
}
