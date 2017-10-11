package com.comercio.web.controlador;


import java.util.Calendar;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;


import com.comercio.web.dao.UsuarioDao;

import com.comercio.web.model.Dato;
import com.comercio.web.model.bean.DatoBean;

@Controller
public class PrincipalControlador {
	
	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("datos", new DatoBean());
		model.addAttribute("hoy", Calendar.getInstance());
		//model.addAttribute("usuario","usuario");
		return "index";
	}
	
	@RequestMapping("/tablas")
	public String tablas(Model model) {
		return "tablas";
	}
	@GetMapping("/login")
	public String admin(Model model) {
		model.addAttribute("proceso", "Acceso al sistema");
		model.addAttribute("descripcion", "Indroduzca sus datos de acceso.");
		model.addAttribute("datos",new DatoBean());
		model.addAttribute("fragmento", "login");
		model.addAttribute("plantilla", "formulario");
		return "Principal";
	}
	@Autowired
	private UsuarioDao usuarioDao;
	@GetMapping(value="/index/{idDato}")
	public String index(@PathVariable("idDato") long id, Model model) {
		List<Dato> datos=usuarioDao.getDatoById(id);
		model.addAttribute("proceso", "Acceso al sistema");
		model.addAttribute("descripcion", "Indroduzca sus datos de acceso.");
		model.addAttribute("fragmento", "notificaciones");
		model.addAttribute("plantilla", "notificaciones");
		model.addAttribute("usulog",datos);
		model.addAttribute("dato",1);
		
	return "Principal";
	}
	

	
}