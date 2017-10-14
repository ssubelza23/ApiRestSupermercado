package com.comercio.web.controlador;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.comercio.web.dao.EmpresaDao;
import com.comercio.web.dao.MarcaDao;
import com.comercio.web.dao.RolesDao;
import com.comercio.web.dao.UsuarioDao;
import com.comercio.web.model.Rol;
import com.comercio.web.model.Usuario;

@Controller
@RequestMapping(value = "/proveedores")
public class ProveedorControlador {

	@Autowired
	private RolesDao rolDao;
	@Autowired
	private MarcaDao marcaDao;
	@Autowired
	private UsuarioDao userDao;
	@Autowired
	private EmpresaDao empresaDao;
	@Autowired
	private HttpSession httpSession;

	@GetMapping(value = "")
	public String Usuarios(Model model) {

		Rol roles = rolDao.getByNombre("Proveedor");
		long id = 0;
		if (!roles.equals(null)) {
			id = roles.getId();
		}

		model.addAttribute("proceso", "Proveedores");
		model.addAttribute("descripcion", "Entdades que proveen al comercio");

		model.addAttribute("fragmento", "proveedores");
		model.addAttribute("plantilla", "proveedores");

		model.addAttribute("empresas", empresaDao.getEmpresaSinProveedor());
		model.addAttribute("rolList", rolDao.getAll());
		model.addAttribute("contactos", userDao.getRol(id));
		model.addAttribute("marcas", marcaDao.getAll());

		Usuario usuario = (Usuario) httpSession.getAttribute("userLog");
		if (usuario != null) {
			model.addAttribute("usulog", usuario);
			model.addAttribute("dato", 1);
		}

		return "Principal";
	}

}
