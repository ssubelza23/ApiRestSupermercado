package com.comercio.web.controlador;

import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import com.comercio.web.model.FeedBack;
import com.comercio.web.model.Proveedor;
import com.comercio.web.model.bean.ProcesoBean;
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
//cometario
	@GetMapping(value = "/listaproveedores")
	public List<Proveedor> tablas_categoria(Model model) {
		return proveedorDao.getAll();
	}

	@PostMapping(value = "/proveedores")
	public FeedBack editarusuario(@Valid @RequestBody ProveedoresBean p, BindingResult result) {
		
	System.out.println(p.getIdcontacto());
	System.out.println(p.getIdempresa());
	System.out.println(p.getIdmarca());
	FeedBack feedBack = new FeedBack();
	if (result.hasErrors()) {
		feedBack.setLista_errores(result.getAllErrors());
		return feedBack;
	}
		try {
		
		Proveedor proveedor = new Proveedor();
		proveedor.setEstado(1);
		proveedor.setEmpresa(empresaDao.getById(Long.parseLong(p.getIdempresa())));
		proveedor.setContactos(userDao.getById(Long.parseLong(p.getIdcontacto())));
		proveedor.setMarcas(marcaDao.getById(Long.parseLong(p.getIdmarca())));
		
		if (Long.parseLong(p.getId()) > 0) {
			proveedor.setId(Long.parseLong(p.getId()));
			proveedorDao.update(proveedor);
			feedBack.setMensaje("Bien!, datos modificados correctamente");
		} else {
			feedBack.setMensaje("Bien!, datos creados correctamente");
			proveedorDao.create(proveedor);
		}
		}catch (Exception error) {
			error.printStackTrace();
			feedBack.setMensaje("existe");
		}
		return feedBack;
		
	}

	@DeleteMapping(value = "/proveedores/{id}")
	public String eliminarProveedor(@PathVariable long id) {
		empresaDao.limpiarEmpresa(id);
		proveedorDao.delete(id);
		return "Bien!. Proveedor eliminado correctamente.";
	}
}
