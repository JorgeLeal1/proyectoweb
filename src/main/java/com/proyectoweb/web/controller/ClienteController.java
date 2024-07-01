package com.proyectoweb.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.proyectoweb.web.repository.ClienteRepository;

@Controller
@SessionAttributes({ "run", "usuario" })
@RequestMapping("/proyectoweb")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteR;
	
	@ResponseBody
	@PostMapping("/consultarClientePorRun")
	public boolean consultarClientePorRun(@RequestParam String Run) {
		
		boolean resultado = clienteR.existsById(Run);
		if(resultado) { return resultado; }
		
		return resultado;
	}
	
}
