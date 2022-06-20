package com.javainuse.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javainuse.model.dao.INotaDao;
import com.javainuse.model.entity.Nota;

@Service
public class NotaServiceImpl implements INotasService {

	@Autowired 
	private INotaDao notaDao;
	
	@Override
	public List<Nota> ListarNotas() {
		return notaDao.ListarNotas();
	}

	@Override
	@Transactional(readOnly = true)
	public Long generarCodigoNotas() {
		return notaDao.generarCodigoNotas();
	}

	@Override
	@Transactional
	public Nota RegistrarNota(Nota nota) {
		return notaDao.save(nota);
	}

}
