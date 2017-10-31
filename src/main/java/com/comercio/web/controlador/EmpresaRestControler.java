package com.comercio.web.controlador;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.comercio.web.dao.CategoriaDao;
import com.comercio.web.dao.EmpresaDao;
import com.comercio.web.dao.ProveedorDao;
import com.comercio.web.dao.RolesDao;
import com.comercio.web.dao.UsuarioDao;
import com.comercio.web.model.Empresa;
import com.comercio.web.model.FeedBack;
import com.comercio.web.model.bean.EmpresaBean;

@RestController
public class EmpresaRestControler {
	@Autowired
	UsuarioDao userDao;

	@Autowired
	RolesDao rolDao;
	@Autowired
	CategoriaDao categoriaDao;
	@Autowired
	EmpresaDao empresaDao;

	@Autowired
	ProveedorDao proveedorDao;

	@GetMapping(value = "/listaempresas")
	public List<Empresa> tablas_categoria(Model model) {
		return empresaDao.getAll();
	}

	@PostMapping(value = "/empresas")
	public FeedBack editarusuario(@Valid @RequestBody EmpresaBean e,BindingResult result) {
		FeedBack feedBack = new FeedBack();
		if (result.hasErrors()) {
			feedBack.setLista_errores(result.getAllErrors());

			return feedBack;
		}
		try {
		Empresa empresa = new Empresa();
		empresa.setNombre(e.getNombre());
		empresa.setDireccioncomercial(e.getDireccioncomercial());
		empresa.setCorreo(e.getCorre());
		empresa.setSitioweb(e.getSitioweb());
		empresa.setTelefono(e.getTelefono());
		empresa.setFax(e.getFax());
		empresa.setDetalle(e.getDetalles());
		empresa.setEspecialidades(e.getEspecialidades());
		empresa.setSector(e.getSector());
		empresa.setSede(e.getSede());
		empresa.setEstado(1);
		if (e.getId() != 0) {
			empresa.setId(e.getId());
			empresaDao.update(empresa);
			feedBack.setMensaje("Bien! datos modificados correctamente");
		} else {
			empresaDao.create(empresa);
			feedBack.setMensaje("Bien! datos creados correctamente");
		}
		} catch (Exception error) {
			error.printStackTrace();
			feedBack.setMensaje("existe");
		}
		return feedBack;
}

	@DeleteMapping(value = "/empresas/{id}")
	public String eliminarempresa(@PathVariable long id) {
		empresaDao.delete(empresaDao.getById(id));
		return "Bien!. Datos eliminados correctmente.";
	}
}
