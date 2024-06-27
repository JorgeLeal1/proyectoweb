package com.proyectoweb.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.proyectoweb.web.interfaces.CuentaServiceInterface;

@Controller
@SessionAttributes({ "run" })
@RequestMapping("/proyectoweb")
public class HomeController {

	private CuentaServiceInterface cuentaServiceInterface;

	public HomeController(CuentaServiceInterface cuentaServiceInterface) {
		this.cuentaServiceInterface = cuentaServiceInterface;
	}

	// llama a index el cual tiene el login para ingresar
	@GetMapping({ "/index" })
	public String index() {
		return "index";
	}

	@GetMapping("/home") // ruta home
	public String home(Model model) {

		// obtiene la variable session el run del usuario logeado
		String run = (String) model.getAttribute("run");
		// System.out.println(run);

		// obtiene el saldo del run seleccionado
		model.addAttribute("saldo", cuentaServiceInterface.consultarSaldoPorRun(run));

		return "home";// retorna a la vista a home
	}

}
