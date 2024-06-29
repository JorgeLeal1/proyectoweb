package com.proyectoweb.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proyectoweb.web.model.ClienteModel;


public interface ClienteRepository extends JpaRepository<ClienteModel, String> {	
	
	
	@Query("SELECT u FROM ClienteModel u ORDER BY u.run ")
	List<ClienteModel> obtenerTodoCliente();
	
	@Query("SELECT u FROM ClienteModel u WHERE u.run=:run ")
	ClienteModel consultarClientePorRun(@Param("run") String run);
	
	
}
