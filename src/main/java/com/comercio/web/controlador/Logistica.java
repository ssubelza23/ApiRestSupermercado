package com.comercio.web.controlador;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import com.comercio.web.dao.ProductoDao;
import com.comercio.web.model.Producto;
import com.comercio.web.model.Usuario;



@Controller
@RequestMapping(value = "/logistica")
public class Logistica {

	@Autowired
	private ProductoDao productoDao;
	@Autowired
	private HttpSession httpSession;

	@GetMapping(value = "")
	public String procesos(Model model) {
		List<Producto> listaProdcutos = productoDao.getAll();
		model.addAttribute("listaProductos", listaProdcutos);
		
		Usuario usuario =  (Usuario) httpSession.getAttribute("userLog");
		if (usuario != null) {
			model.addAttribute("usulog", usuario);
			model.addAttribute("dato", 1);
			
			model.addAttribute("proceso", "Logistica de productos");
			model.addAttribute("descripcion", "Stock, precios y fechas de vencimiento");
			
			return "logistica";
		}else {
			model.addAttribute("proceso", "Acceso al sistema");
			model.addAttribute("descripcion", "Indroduzca sus datos de acceso.");

			model.addAttribute("fragmento", "login");
			model.addAttribute("plantilla", "formulario");
			return "Principal";
			
		}
		}
}

