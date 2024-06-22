package com.proyectoweb.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.ui.Model;

import com.proyectoweb.web.interfaces.UsuarioServiceInterface;
import com.proyectoweb.web.model.ClienteModel;
import com.proyectoweb.web.model.UsuarioModel;

@RestController
@SessionAttributes({"run"})
@RequestMapping("/proyectoweb")
public class UsuarioController {
	
	private UsuarioServiceInterface usuarioServiceInterface;

	public UsuarioController(UsuarioServiceInterface usuarioServiceInterface) {
		this.usuarioServiceInterface = usuarioServiceInterface;
	}
	
	//Consulta por ajax, para no recargar la pagina y poder validar por javascript
	@PostMapping("/validarLogin")
	public String validarLogin(@RequestParam String run, @RequestParam String contrasena, Model model){
	     //muestra las variables del formulario
		 //System.out.println( "Run : " + run+ " contrasena: "+ contrasena);
		
		ClienteModel cliente = new ClienteModel();
		cliente.setRun(run);
		
		UsuarioModel usuario = new UsuarioModel();
		
		usuario.setCliente(cliente);
		usuario.setContrasena(contrasena);
		
		int res= usuarioServiceInterface.validarLogin(usuario);
		
		if(res > 0) {
			model.addAttribute("run", usuario.getCliente().getRun());
			return "1";
		}else {
			return "0";
		}
		
	 }
	
    @PostMapping("/login")
    public ResponseEntity<String> procesarInicioSesion(@RequestParam("run") String run,
                                                       @RequestParam("contrasena") String contrasena) {
    	
		ClienteModel cliente = new ClienteModel();
		cliente.setRun(run);
    	
    	UsuarioModel usuario = new UsuarioModel();
    	usuario.setCliente(cliente);
		usuario.setContrasena(contrasena);
		
		Integer res= usuarioServiceInterface.validarLogin(usuario);
		if(res > 0) {
			//model.addAttribute("run", usuario.getRun());
			//return "redirect:/proyectoweb/home";
			 return ResponseEntity.ok("Inicio de sesión exitoso");
		}
		
		return ResponseEntity.ok("Inicio de sesión exitoso");
    }
	
	
	/*
	 * se debe reemplazar @RestController por @Controller
	 * 
	 * 
	//funciona perfecto xd
	@PostMapping("/validarLogin")
	public String registration(@ModelAttribute Usuario usuario, Model model){
	     //muestra las variables del formulario con objetos cueck 
		//System.out.println( "Run : " + usuario.getRun()+ " contrasena: "+ usuario.getContrasena());
		
		Integer res= dao.validarLogin(usuario);
		if(res > 0) {
			model.addAttribute("run", usuario.getRun());
			return "redirect:/alkewallet/home";
		}else {
			//model.addAttribute("mensaje", "Error Favor ingresar datos nuevamente");
			return "redirect:/alkewallet/";
		}
		
	 }
	*/
	
	/*
	@GetMapping("/validarLogin")
	public String validar(Model modelo) {

		modelo.addAttribute("mensaje", "ADIOS  desde thymeleaf");
		return "home";
    
	}
	*/
	
	/*
	@PostMapping("/validarLogin")
	public Usuario validar(@RequestBody  Usuario usuario) {
		System.out.println( "index : " + usuario.getRun());
		//return "home";
	//	modelo.addAttribute("mensaje", "ADIOS"+usuario+"a desde thymeleaf");
		
		return usuario;
	}
	
	*/
	

	
	
	/*
	@PostMapping("/validarLogin")
	public RedirectView validar(@RequestBody String usuario, Model modelo) {
		System.out.println( "index : " + usuario);
		//return "home";
		modelo.addAttribute("mensaje", "ADIOS"+usuario+"a desde thymeleaf");
		
		return new RedirectView("home");
    
	}
	*/
	
/*
	//funciona perfecto xd
	 @PostMapping("/validarLogin")
	    public String registration(@ModelAttribute("user") Usuario usuario,
	                               BindingResult result,
	                               Model model){
	        
		 System.out.println( "Run : " + usuario.getRun()+ " contrasena: "+ usuario.getContrasena());
		 model.addAttribute("mensaje", "ADIOS"+usuario.getRun()+"a desde thymeleaf");
	        //return "redirect:/register?success";
			return "home";
	    }
	*/
	


}
