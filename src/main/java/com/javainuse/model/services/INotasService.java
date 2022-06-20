package com.javainuse.model.services;

import java.util.List;

import com.javainuse.model.entity.Nota;

public interface INotasService {

	public List<Nota> ListarNotas();

	public Long generarCodigoNotas();

	public Nota RegistrarNota(Nota nota);

}
