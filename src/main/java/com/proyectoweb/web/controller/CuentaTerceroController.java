package com.proyectoweb.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.proyectoweb.web.interfaces.ClienteServiceInterface;
import com.proyectoweb.web.interfaces.CuentaServiceInterface;
import com.proyectoweb.web.interfaces.CuentaTerceroServiceInterface;
import com.proyectoweb.web.model.ClienteModel;
import com.proyectoweb.web.model.CuentaModel;
import com.proyectoweb.web.model.CuentaTerceroModel;

@Controller
@SessionAttributes({"run"})
@RequestMapping("/proyectoweb")
public class CuentaTerceroController {
	
	private CuentaServiceInterface cuentaServiceInterface;
	private ClienteServiceInterface clienteServiceInterface;
	private CuentaTerceroServiceInterface cuentaTerceroServiceInterface;
	
	public CuentaTerceroController(
			CuentaServiceInterface cuentaServiceInterface, 
			ClienteServiceInterface clienteServiceInterface, 
			CuentaTerceroServiceInterface cuentaTerceroServiceInterface 
			) {
		
		this.cuentaServiceInterface = cuentaServiceInterface;
		this.clienteServiceInterface = clienteServiceInterface;
		this.cuentaTerceroServiceInterface = cuentaTerceroServiceInterface;		
	}
	
	/*
	 * La anotación @ResponseBody indica que el valor devuelto debe ser tratado como el cuerpo de la respuesta HTTP. y no una vista
	 */
	
	@ResponseBody
	@PostMapping("/registrarCuentaTercero")
	public boolean registrar( @RequestParam String Run, @RequestParam int NroCuenta, Model model ){
		
			//Run usuario
			String runUsuario = (String) model.getAttribute("run");
			//nrocuenta Origen
			CuentaModel cuentaModel = cuentaServiceInterface.obtenerCuenta(runUsuario);
			
			//**** falta validar si la cuenta que se quiere registrar esta en la BD
			// se debe consultar si existe el Nrocuentatercero y el NrocuentaOrigen
			
			//datos cuentaTercero
			CuentaTerceroModel cuentaTercero = new CuentaTerceroModel();
			cuentaTercero.setNroCuentaTercero(NroCuenta);
			
			ClienteModel cliente = new ClienteModel();
			cliente.setRun(Run);
			
			///////////////////////////////////////////////////
        	CuentaModel cuenta = new CuentaModel();
        	cuenta.setNroCuenta(cuentaModel.getNroCuenta());
        	
        	cuentaTercero.setCliente(cliente);
        	cuentaTercero.setCuenta(cuenta);
        	
			if(cuentaTerceroServiceInterface.insertarCuentaTercero(cuentaTercero)) {
				return true;
			}
				
		return false;

	}
	
	@PostMapping("/buscarCuentaTercero")
	public String buscarCuentaTercero(@RequestParam String inputBuscarContacto, Model model ){
		
			//Run usuario
			String runUsuario = (String) model.getAttribute("run");
			//nrocuenta Origen
			CuentaModel cuentaModel = cuentaServiceInterface.obtenerCuenta(runUsuario);
			int NroCuenta = cuentaModel.getNroCuenta();
			
			String Contacto2 = "%"+inputBuscarContacto+"%";

			model.addAttribute("saldo",cuentaServiceInterface.consultarSaldoPorRun(runUsuario));  
        	model.addAttribute("cuentaTercero", cuentaTerceroServiceInterface.obtenerTodoCuentaTerceroNombre(NroCuenta, Contacto2));
        	
        	return "cuenta/transferir";

	}
	
}
