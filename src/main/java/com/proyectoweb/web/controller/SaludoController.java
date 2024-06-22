package com.proyectoweb.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/proyectoweb")
public class SaludoController {

	
	@GetMapping("/hola")
	public String holamundo() {
		return "HolaMundo";
	}
	
	@GetMapping("/holamundonombre/{nombre}/{edad}")
	public String holamundonombre(@PathVariable String nombre, @PathVariable int edad) {
		return "Hola Mundo! "+ nombre +" Tu Edad es: "+edad;
	}
	
	@GetMapping("/login2")
	public String login() {
		return "cuentas/nuevaCuentas";
	}
}
