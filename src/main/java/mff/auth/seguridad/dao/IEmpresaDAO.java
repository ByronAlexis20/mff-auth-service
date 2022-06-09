package mff.auth.seguridad.dao;

import org.springframework.data.repository.CrudRepository;

import mff.auth.seguridad.entity.Empresa;

public interface IEmpresaDAO extends CrudRepository<Empresa, Integer> {

}
