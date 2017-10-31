package com.comercio.web.controlador;




import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.comercio.web.model.Usuario;


@Controller
@RequestMapping(value = "/empresas")
public class EmpresasControlador {
	@Autowired
	private HttpSession httpSession;

	@GetMapping(value = "")
	public String ShowForm(Model model) {
	
		Usuario usuario =  (Usuario) httpSession.getAttribute("userLog");
		if (usuario != null) {
			model.addAttribute("usulog", usuario);
			model.addAttribute("dato", 1);
			
			model.addAttribute("proceso", "Empresas");
			model.addAttribute("descripcion", "Gestion de empresas");

			model.addAttribute("fragmento", "empresas");
			model.addAttribute("plantilla", "empresas");
			
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
