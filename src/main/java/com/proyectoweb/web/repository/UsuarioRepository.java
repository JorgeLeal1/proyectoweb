package com.proyectoweb.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proyectoweb.web.model.UsuarioModel;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer> {
	
	@Query("SELECT u FROM UsuarioModel u WHERE u.id=:id ")
	UsuarioModel obtenerUsuarioPorId(@Param("id") Integer id);
	
	@Query("SELECT u FROM UsuarioModel u WHERE u.cliente.run=:run ")
	UsuarioModel consultarUsuarioPorRun(@Param("run") String run);
	
	@Query("SELECT count(*) FROM UsuarioModel u WHERE u.cliente.run=:run and u.contrasena=:contrasena")
	public int validarLogin(@Param("run") String run, @Param("contrasena") String contrasena);
	
	
	
}
