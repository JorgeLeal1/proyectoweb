package com.proyectoweb.web.controller;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.proyectoweb.web.dao.ClienteDaoImp;
import com.proyectoweb.web.interfaces.ClienteServiceInterface;
import com.proyectoweb.web.interfaces.CuentaServiceInterface;
import com.proyectoweb.web.interfaces.UsuarioServiceInterface;
import com.proyectoweb.web.model.ClienteModel;
import com.proyectoweb.web.model.CuentaModel;
import com.proyectoweb.web.model.UsuarioModel;

@Controller
@SessionAttributes({"run"})
@RequestMapping("/proyectoweb")
public class CuentaController {
	
	private CuentaServiceInterface cuentaServiceInterface;
	private ClienteServiceInterface clienteServiceInterface;
	private UsuarioServiceInterface usuarioServiceInterface;
	
	public CuentaController(
			CuentaServiceInterface cuentaServiceInterface, 
			ClienteServiceInterface clienteServiceInterface, 
			UsuarioServiceInterface usuarioServiceInterface 
			) {
		
		this.cuentaServiceInterface = cuentaServiceInterface;
		this.clienteServiceInterface = clienteServiceInterface;
		this.usuarioServiceInterface = usuarioServiceInterface;		
	}
	
	
	@GetMapping("/registrar")
	public String registrar( Model model){
		return "registrar";
	}
		
	
	/*
	 * La anotación @ResponseBody indica que el valor devuelto debe ser tratado como el cuerpo de la respuesta HTTP. y no una vista
	 */
	@ResponseBody
	@PostMapping("/registrarCuenta")//ruta home
	public boolean registrar(
			@RequestParam String Run,
			@RequestParam String Nombre1, 
			@RequestParam String Nombre2,
			@RequestParam String Appaterno,
			@RequestParam String Apmaterno,
			
			@RequestParam double Nrocuenta,
			@RequestParam String Alias,
			@RequestParam String Banco,
			
			@RequestParam String NombreUsuario,
			@RequestParam String Email,
			@RequestParam String Contrasena
			){
		
			int nrocuenta = (int) Nrocuenta; ;
		
			/* System.out.println(Run); */
			ClienteModel cliente = new ClienteModel();
			cliente.setRun(Run);
			cliente.setNombre1(Nombre1);
			cliente.setNombre2(Nombre2);
			cliente.setAppaterno(Appaterno);
			cliente.setApmaterno(Apmaterno);
		
			
        	CuentaModel cuenta = new CuentaModel();
        	cuenta.setNroCuenta(nrocuenta);
        	cuenta.setAlias(Alias);
        	cuenta.setBanco(Banco);
        	cuenta.setSaldo(0);//cuenta nueva tiene el saldo en cero
        	cuenta.setCliente(cliente);
        	
        	
        	UsuarioModel usuario = new UsuarioModel();
        	usuario.setNombre(NombreUsuario);
        	usuario.setCorreo_electronico(Email);
        	usuario.setContrasena(Contrasena);
        	usuario.setCliente(cliente);
        	
			if(clienteServiceInterface.insertarCliente(cliente) && 
				cuentaServiceInterface.insertarCuenta(cuenta) && 
				usuarioServiceInterface.insertarUsuario(usuario)
			) {
				return true;
			}
				
		return false;

	}
	
	@ResponseBody
	@PostMapping("/consultarClientePorRun")//ruta home
	public int consultarClientePorRun( @RequestParam String Run) {
		return clienteServiceInterface.consultarClientePorRun(Run); // 0:noexiste || > 0 existe
	}
	
	
	@GetMapping("/depositar")
	public String depositar( Model model){
		String run = (String) model.getAttribute("run");
		model.addAttribute("saldo",cuentaServiceInterface.consultarSaldoPorRun(run));  
		return "cuenta/realizarDepositos";
	}
	
	@PostMapping("/RealizarDeposito")
	public String RealizarDeposito( @RequestParam Double saldo_actual,
									@RequestParam Double saldo,
									Model model){
		
			String run = (String) model.getAttribute("run");
			model.addAttribute("saldo",cuentaServiceInterface.consultarSaldoPorRun(run));  
			
			double saldo_nuevo = saldo + saldo_actual;
			cuentaServiceInterface.actualizarSaldoCuenta(run, saldo_nuevo);
			
		return "redirect:/proyectoweb/depositar";
	}
	
	
	@GetMapping("/retirar")
	public String retirar( Model model){
		String run = (String) model.getAttribute("run");
		model.addAttribute("saldo",cuentaServiceInterface.consultarSaldoPorRun(run));  		
		return "cuenta/realizarRetiro";
	}
	
	@PostMapping("/RealizarRetiro")
	public String RealizarRetiro( @RequestParam Double saldo_actual,
								  @RequestParam Double saldo,
								  Model model){
		
			String run = (String) model.getAttribute("run");
			model.addAttribute("saldo",cuentaServiceInterface.consultarSaldoPorRun(run));  
			
			double saldo_nuevo = saldo_actual - saldo;
			cuentaServiceInterface.actualizarSaldoCuenta(run, saldo_nuevo);
			
			return "redirect:/proyectoweb/retirar";
	}
	
	@GetMapping("/transferir")
	public String transferir( Model model){
		String run = (String) model.getAttribute("run");
		model.addAttribute("saldo",cuentaServiceInterface.consultarSaldoPorRun(run));  		
		return "cuenta/transferir";
	}
	
	
	
	
	/*
	@GetMapping("/transacciones")//ruta home
	public String viewAllCuentas( Model model){
		
		//obtiene de la variable de session el run del usuario logeado
		String run = (String) model.getAttribute("run");
		//System.out.println(run);
		
		//List<Cuenta> lista = dao.obtenerTodoCuenta();
		
		
			for (int i = 0; i < lista.size(); i++) {
				System.out.print("<tr>" + "" + "<td>" + lista.get(i).getId() + "</td>" + "" + "<td>" + lista.get(i).getTitular().getRun() + "</td>" + ""
						+ "<td>" + lista.get(i).getNroCuenta() + "</td>" + "" + "<td>" + lista.get(i).getAlias() + "</td>" + ""
						+ "<td>" + lista.get(i).getBanco() + "</td>" + "" + "<td>" + lista.get(i).getSaldo() + "</td></tr>");
			}
		
		
		model.addAttribute("mensaje","prueba");
		model.addAttribute("lista",dao.obtenerTodoCuenta()); 
		
		return "cuenta/viewAll";// retorna a la vista a home
		
	}
	*/

}
