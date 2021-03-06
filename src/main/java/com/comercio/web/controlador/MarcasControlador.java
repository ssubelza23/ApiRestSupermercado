package com.comercio.web.controlador;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.comercio.web.dao.MarcaDao;
import com.comercio.web.dao.RolesDao;
import com.comercio.web.model.Dato;
import com.comercio.web.model.Marca;
import com.comercio.web.model.Usuario;

@Controller
@RequestMapping(value="marcas")
public class MarcasControlador {
	
	@Autowired
	private MarcaDao marcaDao;
	
	@Autowired
	private RolesDao rolDao;
	
	@Autowired
	private HttpSession httpSession;
	
	@GetMapping(value="")
	public String Marcas(Model model){
		Usuario usuario =  (Usuario) httpSession.getAttribute("userLog");
		if (usuario != null) {
			model.addAttribute("usulog", usuario);
			model.addAttribute("dato", 1);
			
			model.addAttribute("proceso", "Marcas");
			model.addAttribute("descripcion", "Gestion Marcas");

			model.addAttribute("fragmento", "marcas");
			model.addAttribute("plantilla", "marcas");
			
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
