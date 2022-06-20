package com.javainuse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javainuse.model.entity.Nota;
import com.javainuse.model.services.INotasService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class NotasController {

	@Autowired
	private INotasService notasService;
		
	@GetMapping("/notas")
	public List<Nota>ListarNotas(){
		return notasService.ListarNotas();
	}
	
	
	@PostMapping("/notas")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?>  create(@RequestBody Nota nota) {			
		HttpStatus strStatus = null; 
		Map<String, Object> response = new HashMap<>();
		
		try 
		{		
			Long xIdNota = notasService.generarCodigoNotas() == null ? 1 : notasService.generarCodigoNotas()+1;
			
			nota.setId_nota(xIdNota);
			nota.setId_alumno(nota.getId_alumno());
			nota.setNota(nota.getNota());
			nota.setId_estado(nota.getId_estado());			
			nota = notasService.RegistrarNota(nota);
			
			if(nota != null) {
				strStatus = HttpStatus.CREATED;
				response.put("estado", "OK");
				response.put("mensaje", "Se registró la nota con éxito!");
			}else {
				strStatus =  HttpStatus.NOT_MODIFIED;
				response.put("estado", "ERR");
				response.put("mensaje", "El nota no pudo ser registrado!");
			}
		}
		catch(DataAccessException e)
		{
			nota = null;
			strStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			response.put("estado", "ERR");
			response.put("mensaje", "Error al realizar insert en base datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			response.put("nota", nota);
			return new ResponseEntity<Map<String, Object>>(response, strStatus);
		}
		catch(Exception e)
		{
			nota = null;
			strStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			response.put("estado", "ERR");
			response.put("mensaje", "Error inesperado!");
			response.put("error", e.getMessage());
			response.put("nota", nota);
			return new ResponseEntity<Map<String, Object>>(response, strStatus);	
		}
			
		response.put("nota", nota);
		return new ResponseEntity<Map<String, Object>>(response, strStatus);		
	}
}
