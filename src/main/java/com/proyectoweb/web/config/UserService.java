package com.proyectoweb.web.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.proyectoweb.web.model.ClienteModel;
import com.proyectoweb.web.model.UsuarioModel;
import com.proyectoweb.web.repository.UsuarioRepository;

import jakarta.servlet.http.HttpSession;


@Service
public class UserService implements UserDetailsService {

	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String Run) throws UsernameNotFoundException {
		
		// SE PUEDE OBTENER ESTE USUARIO DESDE LA DB
		UsuarioModel usuario = usuarioRepository.consultarUsuarioPorRun(Run);
		
		if(usuario.getId() == null) {
			return null;
		}
		
		//System.out.println(usuario.getNombre());

		//CREAMOS LOS PERMISOS DEL USUARIO
		List<GrantedAuthority> permisos = new ArrayList<>();
		GrantedAuthority p = new SimpleGrantedAuthority("ROLE_USER");//ROLE_USER
		
		permisos.add(p);
		
		// OBTENEMOS EL OBJETO DE HTTP SESSION PARA GUARDAR EN SESSION LOS DATOS DEL USUARIO
		ServletRequestAttributes atr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
		
		HttpSession session = atr.getRequest().getSession(true);
		session.setAttribute("usersession", usuario);
		
		//RETORNAMOS UN OBJETO UserDetail CON EL USUARIO OBTENIDO Y LOS PERMIOS GENERADOS
		return new User(usuario.getCliente().getRun(), "{noop}"+ usuario.getContrasena(), permisos);
		
		
	}
	
	public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return encoder;
    }


}
