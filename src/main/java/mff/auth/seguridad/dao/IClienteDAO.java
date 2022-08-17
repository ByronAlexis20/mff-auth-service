package mff.auth.seguridad.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import mff.auth.seguridad.entity.Cliente;

public interface IClienteDAO extends CrudRepository<Cliente, Integer> {

	public Optional<Cliente> findByCorreo(String correo);
	
	@Query("Select c from Cliente c where c.estado = 'A' and c.idCliente = ?1")
	public Cliente buscarPorIdCliente(Integer idCliente);
	
}
