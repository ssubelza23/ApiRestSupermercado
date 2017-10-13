package com.comercio.web.controlador;



import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.comercio.web.dao.RolesDao;
import com.comercio.web.dao.UsuarioDao;
import com.comercio.web.model.Dato;
import com.comercio.web.model.Usuario;
import com.comercio.web.model.bean.UsuarioBean;

@Controller
@RequestMapping(value = "/usuarios")
public class UsuariosControlador {

	@Autowired
	private UsuarioDao usuarioDao;
	@Autowired
	private RolesDao rolDao;
	@Autowired
	private HttpSession httpSession;

	@GetMapping(value = "")

	public String Usuarios(Model model) {
		// Dato autor = (Dato) httpSession.getAttribute("userLog");
		System.out.println("llega aqui");
		List<Usuario> listUsuarios = usuarioDao.getAll();
		
		model.addAttribute("usuarios", listUsuarios);

		model.addAttribute("proceso", "Usuarios");
		model.addAttribute("descripcion","Gestion de usuarios, al añadir nuevo usuario se asigma el rol del usuario y contraseñas de acceso al sistema si aplica.");

		model.addAttribute("nuevoUsuario", new UsuarioBean());

		model.addAttribute("fragmento", "usuarios");
		model.addAttribute("plantilla", "usuarios");
		System.out.println("llega aqui");
		System.out.println(httpSession.getAttribute("userLog"));
		Usuario usuario =  (Usuario) httpSession.getAttribute("userLog");
		System.out.println("llega aqui");
		if (usuario != null) {
			model.addAttribute("usulog", usuario);
			model.addAttribute("dato", 1);
		}
		model.addAttribute("rolList", rolDao.getAll());

		return "Principal";
	}

}
