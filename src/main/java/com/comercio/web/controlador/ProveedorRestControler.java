package com.comercio.web.controlador;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.comercio.web.dao.CategoriaDao;
import com.comercio.web.dao.EmpresaDao;
import com.comercio.web.dao.MarcaDao;
import com.comercio.web.dao.ProveedorDao;
import com.comercio.web.dao.RolesDao;
import com.comercio.web.dao.UsuarioDao;
import com.comercio.web.model.Proveedor;
import com.comercio.web.model.bean.ProveedoresBean;

@RestController
public class ProveedorRestControler {
	@Autowired
	UsuarioDao userDao;

	@Autowired
	RolesDao rolDao;
	@Autowired
	CategoriaDao categoriaDao;
	@Autowired
	EmpresaDao empresaDao;
	@Autowired
	MarcaDao marcaDao;
	@Autowired
	ProveedorDao proveedorDao;

	@GetMapping(value = "/listaproveedores")
	public List<Proveedor> tablas_categoria(Model model) {
		return proveedorDao.getAll();
	}

	@PostMapping(value = "/proveedores")
	public String editarusuario(@RequestBody ProveedoresBean p) {
		Proveedor proveedor = new Proveedor();
		proveedor.setDetalles(p.getDetalles());
		proveedor.setEstado(1);
		proveedor.setEmpresa(empresaDao.getById(p.getIdempresa()));
		String mensaje = "Bien!, Datos mofificados correctamente";
		proveedor.setContactos(userDao.getById(p.getIdcontacto()));
		proveedor.setMarcas(marcaDao.getById(p.getIdmarca()));
		System.out.println(proveedor);
		System.out.println(p.getIdcontacto());
		System.out.println(p.getIdempresa());
		System.out.println(p.getIdmarca());
		if(p.getId()>0) {
		proveedor.setId(p.getId());
		proveedorDao.update(proveedor);
		mensaje="Bien!, datos modificados correctamente";
		}else {
			mensaje="Bien!, datos creados correctamente";
			 proveedorDao.create(proveedor);
		}
		return mensaje;
	}

	@DeleteMapping(value="/proveedores/{id}")
	public String eliminarProveedor(@PathVariable long id) {
		empresaDao.limpiarEmpresa(id);
		proveedorDao.delete(id);
		return "Bien!. Proveedor eliminado correctamente.";
	}
}
