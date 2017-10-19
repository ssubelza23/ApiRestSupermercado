package com.comercio.web.controlador;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.comercio.web.dao.SectorDao;
import com.comercio.web.model.Sector;
import com.comercio.web.model.bean.SectorBean;


@RestController
public class SectorRestControler {
	@Autowired
	SectorDao sectorDao;
	//cometario
	@GetMapping(value = "/listasectores")
	public List<Sector> tablas_sector() {
		return sectorDao.getAll();
	}
	@PostMapping(value = "/sectores")
	public String editarusuario(@RequestBody SectorBean s) {
		String mensaje="";
		Sector sector = new Sector();
		sector.setNombre(s.getNombre());
		sector.setDetalle(s.getDetalle());
		sector.setLogo(s.getLogo());
		sector.setEstado(1);
		if (s.getId() > 0) {
			sector.setId(s.getId());
			sectorDao.update(sector);
			mensaje = "Bien!, datos modificados correctamente";
		} else {
			mensaje = "Bien!, datos creados correctamente";
			sectorDao.create(sector);
		}
		return mensaje;
	}

	@DeleteMapping(value = "/sectores/{id}")
	public String eliminarProveedor(@PathVariable long id) {
		sectorDao.delete(id);
		return "Bien!. Proveedor eliminado correctamente.";
	}
}