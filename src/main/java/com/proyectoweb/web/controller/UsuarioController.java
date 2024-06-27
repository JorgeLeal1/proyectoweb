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

import com.proyectoweb.web.interfaces.CuentaServiceInterface;
import com.proyectoweb.web.interfaces.UsuarioServiceInterface;
import com.proyectoweb.web.model.ClienteModel;
import com.proyectoweb.web.model.CuentaModel;
import com.proyectoweb.web.model.UsuarioModel;

@RestController
@SessionAttributes({ "run" })
@RequestMapping("/proyectoweb")
public class UsuarioController {

	private UsuarioServiceInterface usuarioServiceInterface;
	private CuentaServiceInterface cuentaServiceInterface;

	public UsuarioController(UsuarioServiceInterface usuarioServiceInterface,
			CuentaServiceInterface cuentaServiceInterface) {
		this.usuarioServiceInterface = usuarioServiceInterface;
		this.cuentaServiceInterface = cuentaServiceInterface;
	}

	// Consulta por ajax, para no recargar la pagina y poder validar por javascript
	@PostMapping("/validarLogin")
	public String validarLogin(@RequestParam String run, @RequestParam String contrasena, Model model) {
		// muestra las variables del formulario
		// System.out.println( "Run : " + run+ " contrasena: "+ contrasena);

		ClienteModel cliente = new ClienteModel();
		cliente.setRun(run);

		UsuarioModel usuario = new UsuarioModel();

		usuario.setCliente(cliente);
		usuario.setContrasena(contrasena);

		int res = usuarioServiceInterface.validarLogin(usuario);

		if (res > 0) {
			model.addAttribute("run", usuario.getCliente().getRun());
			return "1";
		} else {
			return "0";
		}

	}

	@GetMapping("/obtenerCuenta/{Run}")
	public Map<String, Object> obtenerCuenta(@PathVariable String Run) {

		CuentaModel cuenta = cuentaServiceInterface.obtenerCuenta(Run);

		Map<String, Object> json = new HashMap<>();
		// nroCuenta, alias, banco, saldo
		json.put("nroCuenta", cuenta.getNroCuenta());
		json.put("alias", cuenta.getAlias());
		json.put("banco", cuenta.getBanco());

		// System.out.println("banco: "+cuenta.getBanco());

		return json;

	}

	@GetMapping("/obtenerCuentaCliente/{Run}")
	public Map<String, Object> obtenerCuentaCliente(@PathVariable String Run) {

		CuentaModel cuenta = cuentaServiceInterface.obtenerCuentaCliente(Run);

		Map<String, Object> json = new HashMap<>();
		// nroCuenta, alias, banco, saldo
		json.put("nroCuenta", cuenta.getNroCuenta());
		json.put("alias", cuenta.getAlias());
		json.put("banco", cuenta.getBanco());

		json.put("run", cuenta.getCliente().getRun());
		json.put("nombre1", cuenta.getCliente().getNombre1());
		json.put("nombre2", cuenta.getCliente().getNombre2());
		json.put("appaterno", cuenta.getCliente().getAppaterno());
		json.put("apmaterno", cuenta.getCliente().getApmaterno());

		return json;

	}

	/*
	 * @PostMapping("/obtenerCuenta")//ruta home public String
	 * obtenerCuenta( @RequestParam String Run, Model model){ CuentaModel cuenta=
	 * cuentaServiceInterface.obtenerCuenta(Run);
	 * System.out.println("alias: "+cuenta.getAlias());
	 * 
	 * //model.addAttribute("run",Run);
	 * 
	 * return cuenta.getAlias();
	 * 
	 * //ejemplo ajax 2 console.log("/proyectoweb/obtenerCuenta"); $.ajax({ url:
	 * '/proyectoweb/obtenerCuenta', type: "POST", data: { Run: inputRun }, success:
	 * function(response2) { console.log("consultando");
	 * console.log("cuenta "+response2);
	 * 
	 * } , error: function() { console.error("Error al obtener datos"); }
	 * 
	 * });//AJAX //fin ejemplo ajax
	 * 
	 * 
	 * }
	 */

	@GetMapping("/datos")
	public ResponseEntity<Map<String, String>> obtenerDatos() {
		Map<String, String> datos = new HashMap<>();
		datos.put("mensaje", "Hola desde Spring Boot");
		return ResponseEntity.ok(datos);
	}

	/*
	 * @PostMapping("/obtenerCuenta2") public ResponseEntity<Map<String, String>>
	 * obtenerCuenta2( @RequestParam String Run){
	 * 
	 * CuentaModel cuenta= cuentaServiceInterface.obtenerCuenta(Run); Map<String,
	 * String> json = new HashMap<>(); json.put("getBanco", cuenta.getBanco());
	 * json.put("getAlias", cuenta.getAlias());
	 * System.out.println("banco: "+cuenta.getBanco());
	 * 
	 * return ResponseEntity.ok(json);
	 * 
	 * }
	 */

	/*
	 * se debe reemplazar @RestController por @Controller
	 * 
	 * 
	 * //funciona perfecto xd
	 * 
	 * @PostMapping("/validarLogin") public String registration(@ModelAttribute
	 * Usuario usuario, Model model){ //muestra las variables del formulario con
	 * objetos cueck //System.out.println( "Run : " + usuario.getRun()+
	 * " contrasena: "+ usuario.getContrasena());
	 * 
	 * Integer res= dao.validarLogin(usuario); if(res > 0) {
	 * model.addAttribute("run", usuario.getRun()); return
	 * "redirect:/alkewallet/home"; }else { //model.addAttribute("mensaje",
	 * "Error Favor ingresar datos nuevamente"); return "redirect:/alkewallet/"; }
	 * 
	 * }
	 */

	/*
	 * @GetMapping("/validarLogin") public String validar(Model modelo) {
	 * 
	 * modelo.addAttribute("mensaje", "ADIOS  desde thymeleaf"); return "home";
	 * 
	 * }
	 */

	/*
	 * @PostMapping("/validarLogin") public Usuario validar(@RequestBody Usuario
	 * usuario) { System.out.println( "index : " + usuario.getRun()); //return
	 * "home"; // modelo.addAttribute("mensaje",
	 * "ADIOS"+usuario+"a desde thymeleaf");
	 * 
	 * return usuario; }
	 * 
	 */

	/*
	 * @PostMapping("/validarLogin") public RedirectView validar(@RequestBody String
	 * usuario, Model modelo) { System.out.println( "index : " + usuario); //return
	 * "home"; modelo.addAttribute("mensaje", "ADIOS"+usuario+"a desde thymeleaf");
	 * 
	 * return new RedirectView("home");
	 * 
	 * }
	 */

	/*
	 * //funciona perfecto xd
	 * 
	 * @PostMapping("/validarLogin") public String
	 * registration(@ModelAttribute("user") Usuario usuario, BindingResult result,
	 * Model model){
	 * 
	 * System.out.println( "Run : " + usuario.getRun()+ " contrasena: "+
	 * usuario.getContrasena()); model.addAttribute("mensaje",
	 * "ADIOS"+usuario.getRun()+"a desde thymeleaf"); //return
	 * "redirect:/register?success"; return "home"; }
	 */

}
