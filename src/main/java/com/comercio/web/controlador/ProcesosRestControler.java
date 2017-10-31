package com.comercio.web.controlador;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.comercio.web.dao.CategoriaDao;
import com.comercio.web.dao.EmpresaDao;
import com.comercio.web.dao.MarcaDao;
import com.comercio.web.dao.ProcesoDao;
import com.comercio.web.dao.ProductoDao;
import com.comercio.web.dao.ProveedorDao;

import com.comercio.web.dao.UsuarioDao;
import com.comercio.web.model.FeedBack;
import com.comercio.web.model.Proceso;

import com.comercio.web.model.bean.ProcesoBean;


@RestController
public class ProcesosRestControler {
	@Autowired
	UsuarioDao userDao;

	@Autowired
	ProcesoDao procesoDao;
	@Autowired
	CategoriaDao categoriaDao;
	@Autowired
	EmpresaDao empresaDao;
	@Autowired
	MarcaDao marcaDao;
	@Autowired
	ProveedorDao proveedorDao;
	@Autowired
	ProductoDao productoDao;

	@GetMapping(value = "/listaProcesos")
	public List<Proceso> listaRoles() {
		return procesoDao.getAll();
	}
	@PostMapping(value = "/procesos")
	public FeedBack editarRoles(@Valid @RequestBody ProcesoBean r, BindingResult result) {
		FeedBack feedBack = new FeedBack();
		if (result.hasErrors()) {
			feedBack.setLista_errores(result.getAllErrors());
			return feedBack;
		}
		try {
			Proceso p = new Proceso();
			p.setNombre(r.getNombre());
			p.setDescripcion(r.getDescripcion());
			p.setLogo(r.getLogo());
			p.setEnlace(r.getEnlace());
			p.setEstado(1);
			
			if (r.getId()!=0) {
				p.setId(r.getId());
				procesoDao.update(p);
				feedBack.setMensaje("BIEN, datos modificados correctamenta");
			} else {
				procesoDao.create(p);
				feedBack.setMensaje("BIEN, Proceso creado correctamenta");
			}
		} catch (Exception e) {
			e.printStackTrace();
			feedBack.setMensaje("existe");
		}

		return feedBack;
	}
	@DeleteMapping(value = "/procesos/{id}")
	public String eliminarProveedor(@PathVariable long id) {
		procesoDao.delete(id);
		return "Bien!. Proceso elimindo correctamente.";
	}

}
