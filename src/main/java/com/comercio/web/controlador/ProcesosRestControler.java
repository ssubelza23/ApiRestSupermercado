package com.comercio.web.controlador;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.comercio.web.dao.CategoriaDao;
import com.comercio.web.dao.EmpresaDao;
import com.comercio.web.dao.MarcaDao;
import com.comercio.web.dao.ProcesoDao;
import com.comercio.web.dao.ProductoDao;
import com.comercio.web.dao.ProveedorDao;
import com.comercio.web.dao.RolesDao;
import com.comercio.web.dao.UsuarioDao;
import com.comercio.web.model.Proceso;
import com.comercio.web.model.Producto;
import com.comercio.web.model.Rol;
import com.comercio.web.model.bean.ProductoBean;

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
}
