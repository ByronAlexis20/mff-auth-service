package mff.auth.seguridad.controller;

import java.net.UnknownHostException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mff.auth.seguridad.entity.Cliente;
import mff.auth.seguridad.entity.Permiso;
import mff.auth.seguridad.entity.Usuario;
import mff.auth.seguridad.service.IClienteService;
import mff.auth.seguridad.service.IPermisoService;
import mff.auth.seguridad.service.IUsuarioService;
import mff.auth.seguridad.springSecurity.entity.JwtRespuesta;
import mff.auth.seguridad.springSecurity.entity.LoginSolicitud;
import mff.auth.seguridad.springSecurity.jwt.JwtUtils;
import mff.auth.seguridad.springSecurity.services.UserDetailsImpl;

//@CrossOrigin(origins = { "http://" + DatosSesionUtil.urlServidorFrontVue }, maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired 
	AuthenticationManager authenticationManager;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private IPermisoService permisoService;
	
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUserFuncionario(@Valid @RequestBody LoginSolicitud LoginSolicitud) throws UnknownHostException {
		String clave_final = LoginSolicitud.getPassword();
		encoder.encode(clave_final);
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(LoginSolicitud.getUsuario(), clave_final));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		
		Usuario usu = this.usuarioService.buscarPorIdUsuario(userDetails.getCodUsuario());
		
		List<Permiso> listaPermiso = this.permisoService.buscarPorIdPerfil(usu.getPerfil().getIdPerfil());
		
		return ResponseEntity.ok(new JwtRespuesta( 	jwt, 
													usu.getIdUsuario(), 
													usu.getCedula(),
													usu.getNombre(), 
													usu.getApellido(),
													usu.getPerfil(),
													listaPermiso
													));
	}
	
	@PostMapping("/logincliente")
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

	@GetMapping(value = "/test")
	public ResponseEntity<?> test() {
		String data = "Ingresa";
		return new ResponseEntity<String>(data, HttpStatus.OK);
	}
}
