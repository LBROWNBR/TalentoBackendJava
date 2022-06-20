package com.javainuse.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.javainuse.model.entity.Nota;

public interface INotaDao extends CrudRepository<Nota, Long>{

	@Query("select a from Nota a where a.id_estado = 1")
	public List<Nota> ListarNotas();

	@Query("SELECT max(cast(a.id_nota as int)) FROM Nota a")
	public Long generarCodigoNotas();

}
