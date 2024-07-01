package com.proyectoweb.web.serviceImp;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.proyectoweb.web.model.ClienteModel;
import com.proyectoweb.web.model.CuentaModel;
import com.proyectoweb.web.model.UsuarioModel;
import com.proyectoweb.web.repository.ClienteRepository;
import com.proyectoweb.web.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {
	
	@Autowired
	private UsuarioRepository usuarioR;
	@Autowired
	private ClienteRepository clienteR;	
	
	@InjectMocks
	private CuentaModel cuenta;
	
	@InjectMocks
	private ClienteModel cliente;
	
	@InjectMocks
	private UsuarioModel usuario;
	
	/*
	 * @BeforeAll: Esta anotación se utiliza para marcar un método que se ejecuta una
	 * sola vez antes de todas las pruebas en una clase. El método anotado
	 * debe ser estático.
	*/
	
    @BeforeAll
    static void setupAll() {
        System.out.println("Inicializando recursos para todos los tests.");
    }

	/*
	 * @BeforeEach: Esta anotación se utiliza para marcar un método que se ejecuta
	 * antes de cada prueba. Puedes usarlo para realizar configuraciones
	 * o inicializaciones necesarias antes de cada prueba.
	*/
    
    @BeforeEach
    void setup() {
    	System.out.println("Iniciando un test.");
    	
    	usuario.setNombre("Carlos.Cuevas");
    	usuario.setCorreo_electronico("cesarinfo@example.com");
   	
    	cliente.setRun("16330225-k");
    	cliente.setNombre1("César");    
    	cliente.setNombre2("Cuevas");    
    	cliente.setAppaterno("Fierro");    
    	cliente.setApmaterno("Puentes");
    	
    	cuenta.setNrocuenta(10001);
    	cuenta.setAlias("JLEstado");
    	cuenta.setBanco("BancoEstado");
    	cuenta.setSaldo(50000);
    	cuenta.setCliente(cliente);

    }
	
	/*
	 * @Test: Esta anotación se utiliza para marcar un método como una prueba unitaria.
	 * Los métodos anotados con @Test deben ser públicos y no devolver ningún valor.
	 */
    
	/*
	 * @DisplayName: Se utiliza para proporcionar un nombre descriptivo para una
	 * prueba o una clase de prueba
	 */
	@DisplayName("Verificar el nombre del cliente")
    @Test
    void testGetNombre() {
        assertEquals("César", cliente.getNombre1(), "El nombre del cliente debe ser César.");
    }
    
	@DisplayName("Verificar el email del cliente")	
    @Test
    void testGetEmail() {
        assertEquals("cesarinfo@example.com", usuario.getCorreo_electronico(), "El email del cliente debe ser cesarinfo@example.com");
    }
	
	@DisplayName("Test con parámetros")
	/*
	 * @ParameterizedTest: Permite ejecutar una misma prueba con diferentes
	 * conjuntos de parámetros.
	 */
	@ParameterizedTest
	@ValueSource(strings = { "cesar@example.com", "maria@example.com" })
	void testEmailsParametrizados(String email) {
		usuario.setCorreo_electronico(email);
		assertTrue(usuario.getCorreo_electronico().contains("@"));
	}	
	
    @Test
    void testGetSaldo() {
        assertEquals(50000.0, cuenta.getSaldo(), "El saldo debe ser 50000");
    }
	
	@Test
	public void testDepositar() {
		cuenta.setSaldo(50100.0);
		assertEquals(50100.0, cuenta.getSaldo(), "El saldo después de depostiar es 50100");
	}

	/*
	 * @AfterEach: Esta anotación se utiliza para marcar un método que se ejecuta
	 * después de cada prueba. Puedes usarlo para limpiar recursos o realizar
	 * acciones posteriores a cada prueba.
	*/
	    @AfterEach
	    void tearDown() {
	        System.out.println("Finalizando un test.");
	    }
	/*
	 *@AfterAll: Esta anotación se utiliza para marcar un método que se ejecuta una sola vez
	 *después de todas las pruebas en una clase. El método anotado debe ser estático.
	 */
	    @AfterAll
	    static void tearDownAll() {
	        System.out.println("Limpiando recursos después de todos los tests.");
	    }
	
}
