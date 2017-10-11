package com.comercio.web.controlador;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import com.comercio.web.dao.CategoriaDao;
import com.comercio.web.dao.MarcaDao;
import com.comercio.web.dao.ProductoDao;
import com.comercio.web.dao.ProveedorDao;
import com.comercio.web.dao.SectorDao;
import com.comercio.web.model.Dato;
import com.comercio.web.model.Producto;


@Controller
@RequestMapping(value="/productos")
public class ProductosControlador {
	
	@Autowired
	private ProductoDao productoDao;
	@Autowired
	private CategoriaDao categoriaDao;
	
	@Autowired
	private MarcaDao marcaDao;
	
	@Autowired
	private SectorDao sectorDao;
	
	
	@Autowired
	private ProveedorDao proveedorDao;
	
	@Autowired
	private HttpSession httpSession;
	
	@GetMapping(value="")
	public String procesos(Model model){
		List<Producto> listProcesos=productoDao.getAll();
		model.addAttribute("listaProcesos",listProcesos);
		
		model.addAttribute("proceso","Productos");
		model.addAttribute("descripcion","Gestion productos");
		

		model.addAttribute("fragmento", "productos");
		model.addAttribute("plantilla", "productos");
		
		model.addAttribute("categorias", categoriaDao.getAll());
		model.addAttribute("sectores", sectorDao.getAll());
		model.addAttribute("marcas", marcaDao.getAll());
		model.addAttribute("proveedores", proveedorDao.getAll());
		
		
		
		@SuppressWarnings("unchecked")
		List<Dato> datos=(List<Dato>) httpSession.getAttribute("userLog");
		if(datos!=null) {
			model.addAttribute("usulog",datos);
			model.addAttribute("dato",1);
		}
		
		return "Principal";
	}
}
