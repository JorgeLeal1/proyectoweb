package com.proyectoweb.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proyectoweb.web.model.CuentaModel;


public interface CuentaRepository extends JpaRepository<CuentaModel, Integer> {	
	
	
	@Query("SELECT u FROM CuentaModel u ORDER BY u.cliente.run ")
	List<CuentaModel> obtenerTodoCuenta();
	
	@Query("SELECT u FROM CuentaModel u WHERE u.cliente.run=:run ")
	CuentaModel obtenerCuenta(@Param("run") String run);
	
	@Modifying
	@Query("UPDATE CuentaModel c SET c.saldo =:saldo WHERE c.cliente.run =:run")
	void actualizarSaldoCuenta(@Param("saldo") Double saldo, @Param("run") String run);
	
	
}
