package com.comercio.web.controlador;



import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.comercio.web.dao.SectorDao;
import com.comercio.web.model.Sector;
import com.comercio.web.model.Usuario;



@Controller
@RequestMapping(value="/sectores")
public class SectorControlador {
	

	@Autowired
	private SectorDao sectoresDao;
	@Autowired
	private HttpSession httpSession;
	@GetMapping(value="")
	
	public String Usuarios(Model model){
		List<Sector> lista=sectoresDao.getAll();
		model.addAttribute("lista",lista);
		
		model.addAttribute("proceso","Sector - Productos");
		model.addAttribute("descripcion","cada producto es clasificado por sectores.");
		
		model.addAttribute("fragmento", "sectores");
		model.addAttribute("plantilla", "sectores");
	
		
		Usuario usuario = (Usuario) httpSession.getAttribute("userLog");
		if (usuario != null) {
			model.addAttribute("usulog", usuario);
			model.addAttribute("dato", 1);
		}
		
		return "Principal";
	}	

	
	
}
