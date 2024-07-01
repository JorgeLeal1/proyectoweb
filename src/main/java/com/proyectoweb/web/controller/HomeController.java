package com.proyectoweb.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.proyectoweb.web.model.CuentaModel;
import com.proyectoweb.web.repository.CuentaRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Controller
@SessionAttributes({ "run", "usuario" })
@RequestMapping("/proyectoweb")
public class HomeController {

	@Autowired
	private CuentaRepository cuentaR;
	
	//	@Secured("ROLE_USER")
	// llama a index el cual tiene el login para ingresar
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
        session.removeAttribute("run"); // Elimina la variable "run" de la sesión
        session.removeAttribute("usuario"); // Elimina la variable "run" de la sesión
		return "redirect:/proyectoweb/index";
	}
	

	@GetMapping("/home") // ruta home
	@Transactional 
	public String home(Model model) {

		// obtiene la variable session el run del usuario logeado
		String run = (String) model.getAttribute("run");
		
		CuentaModel cuenta = cuentaR.obtenerCuenta(run);
		//System.out.println(cuenta.getSaldo());
		
		//cuentaR.actualizarSaldoCuenta(300.0, "16330225-k");
		
		// obtiene el saldo del run seleccionado
		model.addAttribute("saldo", cuenta.getSaldo());
		return "home";// retorna a la vista a home
	}
	
	/*
	@PostMapping("/home") // ruta home
	public ResponseEntity<CuentaModel> home(Model model) {

		// obtiene la variable session el run del usuario logeado
		//String run = (String) model.getAttribute("run");
		
		CuentaModel cuenta = cuentaR.consultarSaldoPorRun("16330225-k");
		System.out.println(cuenta.getSaldo());
		
		// obtiene el saldo del run seleccionado
		model.addAttribute("saldo", cuenta.getSaldo());

		return new ResponseEntity<>(cuenta, HttpStatus.OK);// retorna a la vista a home
	}
	*/
}
