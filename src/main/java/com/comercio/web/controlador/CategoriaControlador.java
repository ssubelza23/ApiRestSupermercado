package com.comercio.web.controlador;



import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.comercio.web.dao.CategoriaDao;
import com.comercio.web.dao.RolesDao;
import com.comercio.web.model.Categoria;
import com.comercio.web.model.Dato;
import com.comercio.web.model.bean.CategoriaBean;



@Controller
@RequestMapping(value="/categorias")
public class CategoriaControlador {
	
	@Autowired
	private RolesDao rolDao;
	@Autowired
	private CategoriaDao categoriaDao;
	@Autowired
	private HttpSession httpSession;
	
	@GetMapping(value="")
	public String Usuarios(Model model){
		List<Categoria> lista=categoriaDao.getAll();
		model.addAttribute("lista",lista);
		
		model.addAttribute("proceso","Categoria - Productos");
		model.addAttribute("descripcion","cada producto es clasificado por categorias.");
		
		model.addAttribute("nuevo",new CategoriaBean());
		
		model.addAttribute("fragmento", "categorias");
		model.addAttribute("plantilla", "categorias");
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
