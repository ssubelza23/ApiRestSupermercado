package com.comercio.web.controlador;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;


import com.comercio.web.dao.SectorDao;
import com.comercio.web.model.Sector;
import com.comercio.web.model.bean.SectorBean;
import com.comercio.web.model.bean.mensaje;



@RestController
public class SectorRestControler {
	@Autowired
	SectorDao sectorDao;
	@GetMapping(value = "/listasectores")
	public List<Sector> tablas_sector() {
		return sectorDao.getAll();
	}
	@PostMapping(value = "/sectores")
	public mensaje editarusuario(@RequestBody @Valid SectorBean s, Errors errors){
		String mensaje="";
		mensaje response=new mensaje();
		if(errors.hasErrors()) {
			for(int i=0;i<errors.getErrorCount();i++) {
				String f=errors.getFieldErrors().get(i).getField().toUpperCase();
				String m=errors.getFieldErrors().get(i).getDefaultMessage();
				mensaje=mensaje+f+" : "+m+"\n";
			}
			response.setError(true);
			response.setMensaje(mensaje);
			return response;
		}else {
		Sector sector = new Sector();
		sector.setNombre(s.getNombre());
		sector.setDetalle(s.getDetalle());
		sector.setLogo(s.getLogo());
		sector.setEstado(1);
		try {
		if (s.getId() > 0) {
			sector.setId(s.getId());
			sectorDao.update(sector);
		response.setMensaje("Bien!, datos modificados correctamente");
		} else {
			response.setMensaje("Bien!, datos creados correctamente");
			sectorDao.create(sector);
		}
		
		}catch (DataAccessException e) {
			response.setError(true);
		response.setMensaje("ya existe un sector con el mismo nombre");
	
	}
}
		return response;
	}

	@DeleteMapping(value = "/sectores/{id}")
	public String eliminarProveedor(@PathVariable long id) {
		sectorDao.delete(id);
		return "Bien!. Proveedor eliminado correctamente.";
	}
}