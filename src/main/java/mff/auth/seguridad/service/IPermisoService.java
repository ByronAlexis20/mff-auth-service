package mff.auth.seguridad.service;

import java.util.List;

import mff.auth.seguridad.entity.Permiso;

public interface IPermisoService {

	public List<Permiso> buscarPorIdPerfil(Integer idPerfil);
	
}
