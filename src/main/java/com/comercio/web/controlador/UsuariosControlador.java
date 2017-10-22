package com.comercio.web.controlador;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.comercio.web.dao.RolesDao;
import com.comercio.web.model.Usuario;
import com.comercio.web.model.bean.DatoBean;


@Controller
@RequestMapping(value = "/usuarios")
public class UsuariosControlador {

	@Autowired
	private RolesDao rolDao;
	@Autowired
	private HttpSession httpSession;
	@GetMapping(value = "")
	public String Usuarios(Model model) {
		Usuario usuario =  (Usuario) httpSession.getAttribute("userLog");
		if (usuario != null) {
			model.addAttribute("usulog", usuario);
			model.addAttribute("dato", 1);
			
			model.addAttribute("proceso", "Usuarios");
			model.addAttribute("descripcion","Gestion de usuarios, al añadir nuevo usuario se asigma el rol del usuario y contraseñas de acceso al sistema si aplica.");
			
			model.addAttribute("fragmento", "usuarios");
			model.addAttribute("plantilla", "usuarios");
			model.addAttribute("rolList", rolDao.getAll());
			return "Principal";
		}else {
			model.addAttribute("proceso", "Acceso al sistema");
			model.addAttribute("descripcion", "Indroduzca sus datos de acceso.");
			model.addAttribute("datos", new DatoBean());
			model.addAttribute("fragmento", "login");
			model.addAttribute("plantilla", "formulario");
			return "Principal";
			
		}
	
	}

}
