package com.proyectoweb.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.proyectoweb.web.model.ClienteModel;
import com.proyectoweb.web.model.CuentaModel;
import com.proyectoweb.web.model.MonedaModel;
import com.proyectoweb.web.model.TipoTransaccionModel;
import com.proyectoweb.web.model.TransaccionModel;
import com.proyectoweb.web.model.UsuarioModel;
import com.proyectoweb.web.repository.ClienteRepository;
import com.proyectoweb.web.repository.CuentaRepository;
import com.proyectoweb.web.repository.CuentaTerceroRepository;
import com.proyectoweb.web.repository.TransaccionRepository;
import com.proyectoweb.web.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

import org.springframework.ui.Model;

@Controller
@SessionAttributes({ "run", "usuario" })
@RequestMapping("/proyectoweb")
public class CuentaController {

	@Autowired
	private ClienteRepository clienteR;
	
	@Autowired
	private CuentaRepository cuentaR;
	
	@Autowired
	private CuentaTerceroRepository cuentaTerceroR;	
	
	@Autowired
	private UsuarioRepository usuarioR;	
	
	@Autowired
	private TransaccionRepository transaccionR;		
	
	/*
	@ResponseBody
	@PostMapping("/consultarClientePorRun")
	public boolean consultarClientePorRun(@RequestParam String run) {
		
		boolean resultado = clienteR.existsById(run);
		if(resultado) { return resultado; }
		
		return resultado;
	}
	*/
	
	/*-----------------------------------------------------------------------------------------*/
	
	@GetMapping("/registrar")
	public String registrar(Model model) {
		return "registrar";
	}
	/*
	 * La anotación @ResponseBody indica que el valor devuelto debe ser tratado como
	 * el cuerpo de la respuesta HTTP. y no una vista
	 */
	@Transactional 
	@ResponseBody
	@PostMapping("/registrarCuenta") // ruta home
	public boolean registrar(@RequestParam String Run, @RequestParam String Nombre1, @RequestParam String Nombre2,
			@RequestParam String Appaterno, @RequestParam String Apmaterno, @RequestParam double Nrocuenta,
			@RequestParam String Alias, @RequestParam String Banco, @RequestParam String NombreUsuario,
			@RequestParam String Email, @RequestParam String Contrasena) {

		int nrocuenta = (int) Nrocuenta;

		/* System.out.println(Run); */
		ClienteModel cliente = new ClienteModel();
		cliente.setRun(Run);
		cliente.setNombre1(Nombre1);
		cliente.setNombre2(Nombre2);
		cliente.setAppaterno(Appaterno);
		cliente.setApmaterno(Apmaterno);

		CuentaModel cuenta = new CuentaModel();
		cuenta.setNrocuenta(nrocuenta);
		cuenta.setAlias(Alias);
		cuenta.setBanco(Banco);
		cuenta.setSaldo(0);// cuenta nueva tiene el saldo en cero
		cuenta.setCliente(cliente);

		UsuarioModel usuario = new UsuarioModel();
		usuario.setNombre(NombreUsuario);
		usuario.setCorreo_electronico(Email);
		usuario.setContrasena(new BCryptPasswordEncoder().encode(Contrasena));
		usuario.setCliente(cliente);

		clienteR.save(cliente);
		cuentaR.save(cuenta);
		usuarioR.save(usuario);
		
		return true;

	}
	/*-----------------------------------------------------------------------------------------*/
	
	@ResponseBody
	@GetMapping("/obtenerCuentaCliente/{run}")
	public Map<String, Object> obtenerCuentaCliente(@PathVariable String run) {

		CuentaModel cuenta = cuentaR.obtenerCuenta(run);

		Map<String, Object> json = new HashMap<>();
		// nroCuenta, alias, banco, saldo
		//System.out.println(cuenta.getNrocuenta());
		
		json.put("nroCuenta", cuenta.getNrocuenta());
		json.put("alias", cuenta.getAlias());
		json.put("banco", cuenta.getBanco());

		json.put("run", cuenta.getCliente().getRun());
		json.put("nombre1", cuenta.getCliente().getNombre1());
		json.put("nombre2", cuenta.getCliente().getNombre2());
		json.put("appaterno", cuenta.getCliente().getAppaterno());
		json.put("apmaterno", cuenta.getCliente().getApmaterno());

		return json;

	}
	
	/*-----------------------------------------------------------------------------------------*/
	@GetMapping("/depositar")
	public String depositar(Model model) {
		String run = (String) model.getAttribute("run");
		model.addAttribute("saldo", cuentaR.obtenerCuenta(run).getSaldo());
		return "cuenta/realizarDepositos";
	}
	
	// Metodo para aumentar el saldo
	@Transactional 
	@PostMapping("/RealizarDeposito")
	public String RealizarDeposito(@RequestParam Double saldo_actual, @RequestParam Double saldo, Model model) {
		Date current = new Date();
		
		String run = (String) model.getAttribute("run");
		model.addAttribute("saldo", cuentaR.obtenerCuenta(run).getSaldo());

		double saldo_nuevo = saldo + saldo_actual;
		cuentaR.actualizarSaldoCuenta(saldo_nuevo, run);
		
		// Ingresamos el movimiento a la tabla transaccion
		UsuarioModel usuario = usuarioR.consultarUsuarioPorRun(run);

		TransaccionModel transaccion = new TransaccionModel();
		transaccion.setSaldo(saldo);
		transaccion.setDate(current);

		UsuarioModel usuarioEnv = new UsuarioModel();
		usuarioEnv.setId(usuario.getId());

		UsuarioModel usuarioRec = new UsuarioModel();
		usuarioRec.setId(usuario.getId());

		MonedaModel moneda = new MonedaModel();
		moneda.setId(1); // id = 1, CLP

		TipoTransaccionModel tipotransaccion = new TipoTransaccionModel();
		tipotransaccion.setIdtipotransaccion(1); // id = 1, DEPOSITO

		transaccion.setUsuarioenvia(usuarioEnv);
		transaccion.setUsuariorecibe(usuarioRec);
		transaccion.setMoneda(moneda);
		transaccion.setTipotransaccion(tipotransaccion);
		
		transaccionR.save(transaccion);
		
		return "redirect:/proyectoweb/depositar";
	}
	
	/*-----------------------------------------------------------------------------------------*/
	@GetMapping("/retirar")
	public String retirar(Model model) {
		String run = (String) model.getAttribute("run");
		model.addAttribute("saldo", cuentaR.obtenerCuenta(run).getSaldo());
		return "cuenta/realizarRetiro";
	}
	
	// Metodo para disminuir el saldo
	@Transactional 	
	@PostMapping("/RealizarRetiro")
	public String RealizarRetiro(@RequestParam Double saldo_actual, @RequestParam Double saldo, Model model) {

		Date current = new Date();

		String run = (String) model.getAttribute("run");
		model.addAttribute("saldo",cuentaR.obtenerCuenta(run).getSaldo());

		double saldo_nuevo = saldo_actual - saldo;
		cuentaR.actualizarSaldoCuenta(saldo_nuevo, run);

		// Ingresamos el movimiento a la tabla transaccion
		UsuarioModel usuario = usuarioR.consultarUsuarioPorRun(run);

		TransaccionModel transaccion = new TransaccionModel();
		transaccion.setSaldo(saldo);
		transaccion.setDate(current);

		UsuarioModel usuarioEnv = new UsuarioModel();
		usuarioEnv.setId(usuario.getId());

		UsuarioModel usuarioRec = new UsuarioModel();
		usuarioRec.setId(usuario.getId());

		MonedaModel moneda = new MonedaModel();
		moneda.setId(1); // id = 1, CLP

		TipoTransaccionModel tipotransaccion = new TipoTransaccionModel();
		tipotransaccion.setIdtipotransaccion(2);  // id = 2, RETIRO

		transaccion.setUsuarioenvia(usuarioEnv);
		transaccion.setUsuariorecibe(usuarioRec);
		transaccion.setMoneda(moneda);
		transaccion.setTipotransaccion(tipotransaccion);
		
		transaccionR.save(transaccion);

		return "redirect:/proyectoweb/retirar";
	}

	/*-----------------------------------------------------------------------------------------*/
	@GetMapping("/transferir")
	public String transferir(Model model) {
		String run = (String) model.getAttribute("run");
		//System.out.println(run);

		CuentaModel cuenta = cuentaR.obtenerCuenta(run);
		Integer NroCuenta = cuenta.getNrocuenta();
		double saldo =   cuenta.getSaldo();

		model.addAttribute("saldo", saldo);
		model.addAttribute("cuentaTercero", cuentaTerceroR.obtenerTodoCuentaTercero(NroCuenta));

		return "cuenta/transferir";
	}
	
	@Transactional 	
	@ResponseBody
	@PostMapping("/TranferirSaldo")
	public String TranferirSaldo(@RequestParam String Run, @RequestParam Double Saldo, Model model) {
		Date current = new Date();

		// SE ACTUALIZA CUENTA DEL USUARIO
		String run_usuario = (String) model.getAttribute("run");
		double saldo_actual_usuario = cuentaR.obtenerCuenta(run_usuario).getSaldo();

		double saldo_nuevo_usuario = saldo_actual_usuario - Saldo;
		cuentaR.actualizarSaldoCuenta(saldo_nuevo_usuario, run_usuario);
		
		// SE ACTUALIZA CUENTA TERCERO
		// obtenemos el saldo actual de la cuenta tercero
		double saldo_actual = cuentaR.obtenerCuenta(Run).getSaldo();
		double saldo_nuevo = Saldo + saldo_actual;
		cuentaR.actualizarSaldoCuenta(saldo_nuevo, Run);

		// Ingresamos el movimiento a la tabla transaccion
		UsuarioModel IdusuarioEnv = usuarioR.consultarUsuarioPorRun(run_usuario);
		UsuarioModel IdusuarioRec = usuarioR.consultarUsuarioPorRun(Run);

		TransaccionModel transaccion = new TransaccionModel();
		transaccion.setSaldo(Saldo);
		transaccion.setDate(current);

		// usuario que envia la transaccion
		UsuarioModel usuarioEnv = new UsuarioModel();
		usuarioEnv.setId(IdusuarioEnv.getId());

		// usuario que recive el deposito
		UsuarioModel usuarioRec = new UsuarioModel();
		usuarioRec.setId(IdusuarioRec.getId());

		MonedaModel moneda = new MonedaModel();
		moneda.setId(1); // id = 1, CLP

		TipoTransaccionModel tipotransaccion = new TipoTransaccionModel();
		tipotransaccion.setIdtipotransaccion(3); // id =3, TRANSFERENCIA

		transaccion.setUsuarioenvia(usuarioEnv);
		transaccion.setUsuariorecibe(usuarioRec);
		transaccion.setMoneda(moneda);
		transaccion.setTipotransaccion(tipotransaccion);

		transaccionR.save(transaccion);
		return " ";

	}
	

}
