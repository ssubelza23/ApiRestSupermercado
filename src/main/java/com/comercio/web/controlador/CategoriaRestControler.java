package com.comercio.web.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.comercio.web.dao.CategoriaDao;
import com.comercio.web.dao.RolesDao;
import com.comercio.web.dao.UsuarioDao;
import com.comercio.web.model.Categoria;

@RestController
public class CategoriaRestControler {
	@Autowired
	UsuarioDao userDao;

	@Autowired
	RolesDao rolDao;
	@Autowired
	CategoriaDao categoriaDao;

	@GetMapping(value = "/listaCategorias")
	public List<Categoria> tablas_categoria(Model model) {
		return categoriaDao.getAll();
	}

	@PostMapping(value = "/editarcategoria")
	public String editarusuario(@RequestParam("id") String id, @RequestParam("nombre") String nombre,
			@RequestParam("detalle") String detalle, @RequestParam("logo") String logo) {
		String mensaje = "";
		Categoria c = new Categoria();
		c.setNombre(nombre);
		c.setDetalle(detalle);
		c.setLogo(logo);
		if (!id.equals("")) {
			c.setId(Long.parseLong(id));
			categoriaDao.update(c);
			mensaje = "Bien!, Dato modificados correctamente";

		} else {
			categoriaDao.create(c);
			mensaje = "Bien!, Nueva categoria creada correctamente";
		}

		return mensaje;
	}

	@PostMapping(value = "/eliminarCategoria")
	public String eliminarUsuario(@RequestParam("id") long id) {
		Categoria c = categoriaDao.getById(id);
		categoriaDao.delete(c);
		return "Bien!. usuario eliminado correctamente.";
	}
}
