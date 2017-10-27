package com.comercio.web.controlador;

import java.util.ArrayList;
import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.comercio.web.dao.ProcesoDao;
import com.comercio.web.dao.RolesDao;

import com.comercio.web.model.FeedBack;
import com.comercio.web.model.Proceso;
import com.comercio.web.model.Rol;
import com.comercio.web.model.bean.RolBean;
import com.comercio.web.model.bean.UsuarioBean;




@RestController
public class RolesRestControler {
	@Autowired
	RolesDao rolesDao;
	
	@Autowired
	ProcesoDao procesoDao;
	@GetMapping(value = "/listaRoles")
	public List<Rol> listaRoles() {
		return rolesDao.getAll();
	}
	
	@PostMapping(value = "/roles")
	public FeedBack editarRoles(@Valid @RequestBody RolBean r, BindingResult result) {
		System.out.println(r.getProcesos()+"123654");
		FeedBack feedBack = new FeedBack();
		if (result.hasErrors()) {
			feedBack.setLista_errores(result.getAllErrors());

			return feedBack;
		}

		try {
			
			List<Proceso> procesos=new ArrayList<>();
			for(int i=0;i<r.getProcesos().size();i++) {
				procesos.add(procesoDao.getById(r.getProcesos().get(i)));
				System.out.println(r.getProcesos().get(i));
			}
			System.out.println(procesos);
			Rol rol = new Rol();
			rol.setNombre(r.getNombre());
			rol.setDescripcion(r.getDescripcion());
			rol.setEstado(1);
			rol.setProcesos(procesos);
			
			if (r.getId()!=0) {
				rol.setId(r.getId());
				rolesDao.update(rol);
				feedBack.setMensaje("BIEN, datos modificados correctamenta");
			} else {
				rolesDao.create(rol);
				feedBack.setMensaje("BIEN, Rol creado correctamenta");
			}
		} catch (Exception e) {
			e.printStackTrace();
			feedBack.setMensaje("existe");
		}

		return feedBack;
	}


	@DeleteMapping(value = "/roles/{id}")
	public String eliminarProveedor(@PathVariable long id) {
		rolesDao.delete(id);
		return "Bien!. Rol Eliminado correctamente.";
	}
	////=============procesos=========================================================

}