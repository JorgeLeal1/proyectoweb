package com.proyectoweb.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.proyectoweb.web.model.ClienteModel;
import com.proyectoweb.web.model.CuentaModel;
import com.proyectoweb.web.model.CuentaTerceroModel;
import com.proyectoweb.web.model.UsuarioModel;
import com.proyectoweb.web.repository.CuentaRepository;
import com.proyectoweb.web.repository.CuentaTerceroRepository;
import com.proyectoweb.web.repository.TransaccionRepository;
import com.proyectoweb.web.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

import org.springframework.ui.Model;

@Controller
@SessionAttributes({ "run", "usuario" })
@RequestMapping("/proyectoweb")
public class CuentaTerceroController {

	@Autowired
	private CuentaRepository cuentaR;
	
	@Autowired
	private UsuarioRepository usuarioR;	
	
	@Autowired
	private TransaccionRepository transaccionR;		
	
	@Autowired
	private CuentaTerceroRepository cuentaTerceroR;	
	
	@ResponseBody
	@Transactional 	
	@PostMapping("/registrarCuentaTercero")
	public String registrar(@RequestParam String Run, @RequestParam int NroCuenta, Model model) {
		
		// Run usuario
		String runUsuario = (String) model.getAttribute("run");
		// nrocuenta Origen
		CuentaModel cuentaOrigenModel = cuentaR.obtenerCuenta(runUsuario);

		CuentaModel cuentaOrigen = new CuentaModel();
		cuentaOrigen.setNrocuenta(cuentaOrigenModel.getNrocuenta());

		ClienteModel clienteOrigen = new ClienteModel();
		clienteOrigen.setRun(runUsuario);

		///////////////////////////////////////////////////
		// datos cuentaTercero
		CuentaTerceroModel cuentaTercero = new CuentaTerceroModel();
		cuentaTercero.setNrocuentatercero(NroCuenta);

		ClienteModel clienteTercero = new ClienteModel();
		clienteTercero.setRun(Run);

		cuentaTercero.setCliente(clienteTercero);
		cuentaTercero.setCuenta(cuentaOrigen);
		cuentaTercero.setClienteOrigen(clienteOrigen);
		/*
		System.out.println("Cuenta Tercero: "+ NroCuenta);
		System.out.println("Run Tercero: "+ Run);
		System.out.println("cuentaOrigen: "+ cuentaOrigenModel.getNrocuenta());
		System.out.println("runUsuario: "+ runUsuario);
		*/
		
		if (runUsuario.equals(Run)) {	
			return "El usuario no puede ser creado en cuentaTercero!";

		} else {
			// verifica que la cuenta ingresada solo exista 1 vez por el run logeado
			if (cuentaTerceroR.verificarCuentaTercero(NroCuenta, Run, cuentaOrigenModel.getNrocuenta(), runUsuario ) > 0) {
				
				return "La cuenta ya se encuentra ingresada en sus contactos!";
			} else {
				if (cuentaTerceroR.save(cuentaTercero) != null) {
					return "Cuenta registrada correctamente!";
				}
			}
		}

		return "";

	}
	
	@PostMapping("/buscarCuentaTercero")
	public String buscarCuentaTercero(@RequestParam String inputBuscarContacto, Model model) {

		// Run usuario
		String runUsuario = (String) model.getAttribute("run");
		
		// nrocuenta Origen
		CuentaModel cuenta = cuentaR.obtenerCuenta(runUsuario);

		Integer NroCuenta = cuenta.getNrocuenta();
		double saldo = cuenta.getSaldo();
		
		String Nombre = "%" + inputBuscarContacto + "%";

		model.addAttribute("saldo", saldo);
		model.addAttribute("cuentaTercero", cuentaTerceroR.obtenerTodoCuentaTerceroNombre(NroCuenta, Nombre));	

		return "cuenta/transferir";

	}
	
	/*-----------------------------------------------------------------------------------------*/	
	@GetMapping("/movimientos")
	public String movimientos(Model model) {

		// Run usuario
		String run = (String) model.getAttribute("run");
		UsuarioModel usuario = usuarioR.consultarUsuarioPorRun(run);

		Integer idEnvia = usuario.getId();
		Integer idRecive = usuario.getId();
		
		//System.out.println(usuario.getId());
		model.addAttribute("movimientos", transaccionR.obtenerPorId(idEnvia, idRecive));

		return "cuenta/movimientos";
	}
	
	
}
