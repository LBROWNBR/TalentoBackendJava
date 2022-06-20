package com.javainuse.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="nota")
public class Nota implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id_nota;	
	private double nota;
	private int id_alumno;
	private int id_estado;
	
	public Long getId_nota() {
		return id_nota;
	}
	public void setId_nota(Long id_nota) {
		this.id_nota = id_nota;
	}
	public double getNota() {
		return nota;
	}
	public void setNota(double nota) {
		this.nota = nota;
	}
	public int getId_alumno() {
		return id_alumno;
	}
	public void setId_alumno(int id_alumno) {
		this.id_alumno = id_alumno;
	}
	public int getId_estado() {
		return id_estado;
	}
	public void setId_estado(int id_estado) {
		this.id_estado = id_estado;
	}
	
		
	
}
