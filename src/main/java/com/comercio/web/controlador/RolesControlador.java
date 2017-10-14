package com.comercio.web.controlador;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
		model.addAttribute("listRoles", listRoles);
		List<Proceso> listProcesos = procesosDao.getAll();
		model.addAttribute("listProcesos", listProcesos);
		model.addAttribute("newRol", new RolBean());

		model.addAttribute("proceso", "Roles");
		model.addAttribute("descripcion", "Gestion de roles cada rol cuenta con diferentes procesos");

		model.addAttribute("fragmento", "roles");
		model.addAttribute("plantilla", "roles");

		Usuario usuario = (Usuario) httpSession.getAttribute("userLog");
		if (usuario != null) {
			model.addAttribute("usulog", usuario);
			model.addAttribute("dato", 1);
		}
		return "Principal";
	}

	@PostMapping(value = "/addRol")
	public String submit(@ModelAttribute("newRol") RolBean r, BindingResult result, Model model) throws ParseException {
		Rol rol = new Rol();
		rol.setNombre(r.getNombre());
		rol.setDescripcion(r.getDescripcion());
		rol.setEstado(1);
		rolesDao.create(rol);
		model.addAttribute("proceso", "Roles");
		model.addAttribute("descripcion", "Gestion de roles cada rol cuenta con diferentes procesos");

		return "redirect:/roles";
	}

	@GetMapping(value = "/asignarProcesos/{id}")
	public String asignarProcesos(@PathVariable("id") long id, Model model) {
		List<Proceso> p = rolesDao.getProcesosAsignados(id);
		List<Proceso> pa = procesosDao.getAll();
		for (int i = 0; i < pa.size(); i++) {
			for (int j = 0; j < p.size(); j++) {
				if (pa.get(i).equals(p.get(j))) {
					pa.remove(i);
				}
			}
		}
		Rol rol_ref = new Rol();
		rol_ref = rolesDao.getById(id);
		model.addAttribute("proceso", "Asinar procesos");
		model.addAttribute("descripcion", "asignar");
		model.addAttribute("asignados", p);
		model.addAttribute("noasignados", pa);
		model.addAttribute("rol_ref", rol_ref);

		model.addAttribute("fragmento", "asignarProcesos");
		model.addAttribute("plantilla", "roles");

		return "Principal";
	}

	@GetMapping(value = "/addrolpro/{rol_id}/{procesos_id}/{bandera}")
	public String loq(@PathVariable("rol_id") long rol_id, @PathVariable("procesos_id") long procesos_id,
			@PathVariable("bandera") int bandera, Model model) {
		if (bandera == 1) {
			List<Proceso> procesos = new ArrayList<>();
			Rol rol = rolesDao.getById(rol_id);
			procesos = rol.getProcesos();
			procesos.add(procesosDao.getById(procesos_id));
			rol.setProcesos(procesos);

			rolesDao.update(rol);
		}
		if (bandera == 2) {
			Proceso proceso = procesosDao.getById(procesos_id);
			Rol rol = rolesDao.getById(rol_id);
			rol.removeProceso(proceso);
			rolesDao.update(rol);
		}

		return "redirect:/roles/asignarProcesos/" + rol_id;
	}

}
