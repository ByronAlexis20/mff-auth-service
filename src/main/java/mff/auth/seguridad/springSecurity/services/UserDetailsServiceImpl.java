package mff.auth.seguridad.springSecurity.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	//@Autowired
	//IUsuarioDAO usuarioDAO;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
			//Usuario user = usuarioDAO.findByUsuario(usuario)
			//		.orElseThrow(() -> new UsernameNotFoundException("Indetificación no encontrada; identificación: " + usuario));
			return null;//UserDetailsImpl.build(user);
	}
}