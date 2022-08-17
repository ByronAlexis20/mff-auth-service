package mff.auth.seguridad.springSecurity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mff.auth.seguridad.dao.IClienteDAO;
import mff.auth.seguridad.dao.IUsuarioDAO;
import mff.auth.seguridad.entity.Cliente;
import mff.auth.seguridad.entity.Usuario;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	IUsuarioDAO usuarioDAO;
	
	@Autowired
	IClienteDAO clienteDAO;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
		if(!usuario.contains("@")) {
			Usuario user = usuarioDAO.findByUsuario(usuario)
					.orElseThrow(() -> new UsernameNotFoundException("Indetificación no encontrada; identificación: " + usuario));
			return UserDetailsImpl.build(user);
		}else {
			Cliente cli = clienteDAO.findByCorreo(usuario)
					.orElseThrow(() -> new UsernameNotFoundException("Indetificación no encontrada; identificación: " + usuario));
			System.out.println(cli.getApellidos());
			return UserDetailsImpl.buildCliente(cli);
		}
	}
}