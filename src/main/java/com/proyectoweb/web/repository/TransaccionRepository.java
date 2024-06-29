package com.proyectoweb.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.proyectoweb.web.model.TransaccionModel;


public interface TransaccionRepository extends JpaRepository<TransaccionModel, Integer> {	
	
	@Query("SELECT t FROM TransaccionModel t WHERE t.usuarioenvia.id=:idEnvia OR t.usuariorecibe.id=:idRecive ORDER BY t.date DESC")
	List<TransaccionModel> obtenerPorId(@Param("idEnvia") Integer idEnvia, @Param("idRecive") Integer idRecive);
	

}
