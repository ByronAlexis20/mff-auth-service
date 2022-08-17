package mff.auth.seguridad.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mff.auth.seguridad.entity.Cliente;
import mff.auth.seguridad.service.IClienteService;
import mff.auth.seguridad.springSecurity.entity.JwtRespuesta;
import mff.auth.seguridad.springSecurity.entity.LoginSolicitud;
import mff.auth.seguridad.springSecurity.jwt.JwtUtils;
import mff.auth.seguridad.springSecurity.services.UserDetailsImpl;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/authcliente")
public class AuthClienteController {

	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired 
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUserCliente(@Valid LoginSolicitud LoginSolicitud)  {
		String clave_final = LoginSolicitud.getPassword();
		encoder.encode(clave_final);
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(LoginSolicitud.getUsuario(), clave_final));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		
		Cliente usu = this.clienteService.buscarPorId(userDetails.getCodUsuario());
		
		return ResponseEntity.ok(new JwtRespuesta( 	jwt, 
													usu.getIdCliente(), 
													usu.getCedula(),
													usu.getNombres(), 
													usu.getApellidos(),
													null,
													null
													));
	}
	
}
