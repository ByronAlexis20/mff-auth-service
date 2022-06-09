package mff.auth.seguridad.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mff.auth.seguridad.dao.IPermisoDAO;
import mff.auth.seguridad.entity.Permiso;
import mff.auth.seguridad.service.IPermisoService;

@Service
public class PermisoImpl implements IPermisoService{

	@Autowired
	private IPermisoDAO permisoDAO;
	
	@Override
	@Transactional(readOnly = true)
	public List<Permiso> buscarPorIdPerfil(Integer idPerfil) {
		return this.permisoDAO.buscarPorIdPerfil(idPerfil);
	}

}
