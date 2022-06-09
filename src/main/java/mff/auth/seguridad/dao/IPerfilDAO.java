package mff.auth.seguridad.dao;

import org.springframework.data.repository.CrudRepository;

import mff.auth.seguridad.entity.Perfil;

public interface IPerfilDAO extends CrudRepository<Perfil, Integer> {

}
