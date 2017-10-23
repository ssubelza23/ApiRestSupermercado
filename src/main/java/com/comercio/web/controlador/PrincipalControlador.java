package com.comercio.web.controlador;

import java.util.Calendar;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import com.comercio.web.dao.UsuarioDao;
import com.comercio.web.model.Usuario;
import com.comercio.web.model.bean.DatoBean;

@Controller
public class PrincipalControlador {
	@Autowired
	private HttpSession httpSession;

	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("datos", new DatoBean());
		model.addAttribute("hoy", Calendar.getInstance());
		return "index";
	}

	@GetMapping("/login")
	public String admin(Model model) {
		model.addAttribute("proceso", "Acceso al sistema");
		model.addAttribute("descripcion", "Indroduzca sus datos de acceso.");
		model.addAttribute("datos", new DatoBean());
		model.addAttribute("fragmento", "login");
		model.addAttribute("plantilla", "formulario");
		return "Principal";
	}

	@Autowired
	private UsuarioDao usuarioDao;

	@GetMapping(value = "/index/{idDato}")
	public String index(@PathVariable("idDato") long id, Model model) {
		Usuario usuariolog = new Usuario();
		usuariolog = usuarioDao.getById(id);
		model.addAttribute("proceso", "Notificaciones");

		model.addAttribute("fragmento", "notificaciones");
		model.addAttribute("plantilla", "notificaciones");
		model.addAttribute("usulog", usuariolog);
		model.addAttribute("dato", 1);

		return "Principal";
	}
	@GetMapping(value = "/logout")
	public String logout(Model model) {
		httpSession.removeAttribute("userLoggedIn");
		model.addAttribute("proceso", "Acceso al sistema");
		model.addAttribute("descripcion", "Indroduzca sus datos de acceso.");
		model.addAttribute("datos", new DatoBean());
		model.addAttribute("fragmento", "login");
		model.addAttribute("plantilla", "formulario");
		return "Principal";
	}
}