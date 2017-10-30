package com.comercio.web.controlador;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;

import com.comercio.web.dao.ProcesoDao;
import com.comercio.web.dao.RolesDao;
import com.comercio.web.model.Proceso;
import com.comercio.web.model.Rol;
import com.comercio.web.model.Usuario;
import com.comercio.web.model.bean.RolBean;

@Controller
@RequestMapping(value = "/roles")
public class RolesControlador {

	@Autowired
	private RolesDao rolesDao;
	@Autowired
	private ProcesoDao procesosDao;
	@Autowired
	private HttpSession httpSession;

	@GetMapping(value = "")
	public String ShowForm(Model model) {
		List<Rol> listRoles = rolesDao.getAll();
		model.addAttribute("listaRoles", listRoles);
		List<Proceso> listaProcesos = procesosDao.getAll();
		model.addAttribute("listaProcesos", listaProcesos);
		model.addAttribute("newRol", new RolBean());
		
		Usuario usuario =  (Usuario) httpSession.getAttribute("userLog");
		if (usuario != null) {
			model.addAttribute("usulog", usuario);
			model.addAttribute("dato", 1);
			
			model.addAttribute("proceso", "Roles");
			model.addAttribute("descripcion", "Gestion de roles cada rol cuenta con diferentes procesos");

			model.addAttribute("fragmento", "roles");
			model.addAttribute("plantilla", "roles");
			
			return "Principal";
		}else {
			model.addAttribute("proceso", "Acceso al sistema");
			model.addAttribute("descripcion", "Indroduzca sus datos de acceso.");

			model.addAttribute("fragmento", "login");
			model.addAttribute("plantilla", "formulario");
			return "Principal";
			
		}
		
		
	}



	@GetMapping(value = "/asignarProcesos/{id}")
	public String asignarProcesos(@PathVariable("id") long id, Model model) {
		Usuario usuario =  (Usuario) httpSession.getAttribute("userLog");
		if (usuario != null) {
			
			model.addAttribute("usulog", usuario);
			model.addAttribute("dato", 1);
			List<Rol> listRoles = rolesDao.getAll();
			model.addAttribute("listaRoles", listRoles);
			List<Proceso> listaProcesos = procesosDao.getAll();
			model.addAttribute("listaProcesos", listaProcesos);
			
			
			List<Proceso> ProcesosAsignados = rolesDao.getProcesosAsignados(id);

			List<Proceso> ProcesosNoAsignados = rolesDao.getProcesosNoAsignados(id);
		
	
			Rol rol_ref = new Rol();
			rol_ref = rolesDao.getById(id);
			model.addAttribute("proceso", "Asinar procesos");
			model.addAttribute("descripcion", "asignar");
			model.addAttribute("asignados", ProcesosAsignados);
			model.addAttribute("noasignados", ProcesosNoAsignados);
			model.addAttribute("rol_ref", rol_ref);

			model.addAttribute("fragmento", "asignarProcesos");
			model.addAttribute("plantilla", "roles");
			model.addAttribute("idRolModificar", id);
			
			return "Principal";
		}else {
			model.addAttribute("proceso", "Acceso al sistema");
			model.addAttribute("descripcion", "Indroduzca sus datos de acceso.");

			model.addAttribute("fragmento", "login");
			model.addAttribute("plantilla", "formulario");
			return "Principal";
			
		}
		
		
	}

}
