package com.proyectoweb.web.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.proyectoweb.web.model.ClienteModel;
import com.proyectoweb.web.model.UsuarioModel;
import com.proyectoweb.web.repository.UsuarioRepository;

import org.springframework.ui.Model;

@Controller
@SessionAttributes({ "run" })
@RequestMapping("/proyectoweb")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioR;
	
	@GetMapping("/nuevo")
	private ResponseEntity<UsuarioModel> crearUsuario(){
		
		UsuarioModel usuario = new UsuarioModel();
		usuario.setNombre("JorgeJPA2");
		usuario.setContrasena("111");
		//usuario.setContrasena(new BCryptPasswordEncoder().encode("111"));
		usuario.setCorreo_electronico("Correo@JPA.cl");

		ClienteModel cliente = new ClienteModel();
		cliente.setRun("16330225-k");
		
		usuario.setCliente(cliente);
		usuario = usuarioR.save(usuario);
		
		return new ResponseEntity<>(usuario, HttpStatus.OK);
	}
	

	@GetMapping("/obtenerId/{id}")
	private ResponseEntity<UsuarioModel> obtenerId(@PathVariable("id") Integer id){
		
		Optional<UsuarioModel> optional = usuarioR.findById(id);
		if(optional.isPresent())
			return new ResponseEntity<>(optional.get(), HttpStatus.OK);
		
		return new ResponseEntity<>(new UsuarioModel(), HttpStatus.NOT_FOUND);		
	}	
	

	@GetMapping("/obtenerUsuarioPorId/{id}")
	private ResponseEntity<UsuarioModel> obtenerUsuarioPorId(@PathVariable("id") Integer id){
		
		UsuarioModel usuario = usuarioR.obtenerUsuarioPorId(id);
		//System.out.println(usuario.getNombre());
		
		long Filas = usuarioR.count();
		//System.out.println("Cantidad de filas: "+Filas);
		
		boolean resultado = usuarioR.existsById(id);
		if(resultado) {
			//System.out.println("Existe");
		}else {
			//System.out.println("Existe");
		}
		
		return new ResponseEntity<>(usuario, HttpStatus.OK);
	}	
	
	
	@ResponseBody
	@PostMapping("/validarLogin")
	public int validarLogin(@RequestParam String run, @RequestParam String contrasena, Model model) {
		// muestra las variables del formulario
		// System.out.println( "Run : " + run+ " contrasena: "+ contrasena);
		int res = 0;
		res = usuarioR.validarLogin(run, contrasena);
		//System.out.println(usuarioR.validarLogin(run, contrasena));
		
		if (res > 0) {
			model.addAttribute("run", run);
			return res;
		} 
		return res;
	}
	
}
