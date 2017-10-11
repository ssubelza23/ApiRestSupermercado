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

import com.comercio.web.dao.RolesDao;
import com.comercio.web.dao.UsuarioDao;
import com.comercio.web.model.Rol;
import com.comercio.web.model.Usuario;
import com.comercio.web.model.bean.UsuarioBean;

@RestController
public class ContactosProveedorRestControler {

	@Autowired
	UsuarioDao usuarioDao;
	@Autowired
	RolesDao rolDao;

	@GetMapping(value = "/contactos")
	public List<Usuario> tablas_categoria(Model model) {
		List<Rol> roles = rolDao.getByNombre("Proveedor");
		Rol rol = new Rol();
		long id = 0;
		if (!roles.isEmpty()) {
			rol = roles.get(0);
			id = rol.getId();
		}
		return usuarioDao.getRol(id);
	}

	@PostMapping("/contactos")
	public String editarusuario(@RequestBody UsuarioBean u) {
		Usuario user=new Usuario();
		String mensaje="";
		List<Rol> roles = rolDao.getByNombre("Proveedor");
		Rol rol = new Rol();
		if (roles.size()>0) {
			rol = roles.get(0);
			
			
		}else {
			long idRol=rolDao.create(new Rol("Proveedor","sin privilegios",1));	
			System.out.println(rolDao.getById(idRol)+"seras");
			rol=rolDao.getById(idRol);
		}
		user.setNombre(u.getNombre());
		user.setAp(u.getAp());
		user.setAm(u.getAm());
		user.setDNI(u.getDNI());
		user.setSexo(u.getSexo());
		user.setPuestoTrabajo(u.getPuestotrabajo());
		user.setMovil(u.getMovil());
		user.setCorreo(u.getCorreo());
		user.addRol(rol);
		user.setEstado(1);
	System.out.println(u.getId()+"id para modifcar");
		
		if(u.getId()>0) {
			user.setId(u.getId());
			usuarioDao.update(user);
			mensaje="BIEN, datos modificados correctamenta";
			
		}else {
			usuarioDao.create(user);
			mensaje="BIEN, datos creados correctamente";
		}
	
		
		

		return mensaje;
	}

	@DeleteMapping(value = "/contactos/{id}")
	public String eliminarempresa(@PathVariable long id) {
		usuarioDao.delete(id);
		return "Bien!. Proveedor eliminado correctamente.";
	}
}
