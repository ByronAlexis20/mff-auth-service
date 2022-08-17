package mff.auth.seguridad.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mff.auth.seguridad.dao.IClienteDAO;
import mff.auth.seguridad.entity.Cliente;
import mff.auth.seguridad.service.IClienteService;

@Service
public class ClienteImpl implements IClienteService {

	@Autowired
	private IClienteDAO clienteDao;
	
	@Override
	@Transactional(readOnly = true)
	public Cliente buscarPorId(Integer idCliente) {
		return this.clienteDao.buscarPorIdCliente(idCliente);
	}

}
