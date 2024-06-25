package com.proyectoweb.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.ui.Model;

import com.proyectoweb.web.interfaces.ClienteServiceInterface;
import com.proyectoweb.web.interfaces.CuentaServiceInterface;
import com.proyectoweb.web.interfaces.UsuarioServiceInterface;
import com.proyectoweb.web.model.ClienteModel;
import com.proyectoweb.web.model.CuentaModel;
import com.proyectoweb.web.model.UsuarioModel;

@RestController
@SessionAttributes({"run"})
@RequestMapping("/proyectoweb")
public class ClienteController {
	
	private CuentaServiceInterface cuentaServiceInterface;
	private ClienteServiceInterface clienteServiceInterface;
	private UsuarioServiceInterface usuarioServiceInterface;
	
	public ClienteController(
			CuentaServiceInterface cuentaServiceInterface, 
			ClienteServiceInterface clienteServiceInterface, 
			UsuarioServiceInterface usuarioServiceInterface 
			) {
		
		this.cuentaServiceInterface = cuentaServiceInterface;
		this.clienteServiceInterface = clienteServiceInterface;
		this.usuarioServiceInterface = usuarioServiceInterface;		
	}
	
    
    @GetMapping("/obtenerCliente/{Run}")
	public Map<String, Object> obtenerCliente( @PathVariable String Run){
		
		ClienteModel cliente= clienteServiceInterface.obtenerCliente(Run);
		
        Map<String, Object> json = new HashMap<>();
        //nroCuenta, alias, banco, saldo
        json.put("nombre1", cliente.getNombre1());
        json.put("nombre2", cliente.getNombre2());
        json.put("appaterno", cliente.getAppaterno());
        json.put("apmaterno", cliente.getApmaterno());
        
		return json;
		
	}

}
