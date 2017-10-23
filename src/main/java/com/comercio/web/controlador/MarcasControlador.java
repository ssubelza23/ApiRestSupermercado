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
		List<Marca> lista=marcaDao.getAll();
		model.addAttribute("lista",lista);
		
		model.addAttribute("proceso","Marcas");
		model.addAttribute("descripcion","Marcas que ofrece el comercio");
	
		model.addAttribute("fragmento", "marcas");
		model.addAttribute("plantilla", "marcas");
		
		@SuppressWarnings("unchecked")
		List<Dato> datos=(List<Dato>) httpSession.getAttribute("userLog");
		if(datos!=null) {
			model.addAttribute("usulog",datos);
			model.addAttribute("dato",1);
		}
		model.addAttribute("rolList",rolDao.getAll());
	
		return "Principal";
	}	

}
