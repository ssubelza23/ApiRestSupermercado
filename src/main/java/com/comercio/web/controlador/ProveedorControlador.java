package com.comercio.web.controlador;



import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.comercio.web.dao.EmpresaDao;
import com.comercio.web.dao.MarcaDao;
import com.comercio.web.dao.RolesDao;
import com.comercio.web.dao.UsuarioDao;
import com.comercio.web.model.Dato;
import com.comercio.web.model.Rol;


@Controller
@RequestMapping(value="/proveedores")
public class ProveedorControlador {
	
	@Autowired
	private RolesDao rolDao;
	@Autowired
	private MarcaDao marcaDao;
	@Autowired
	private UsuarioDao userDao;
	@Autowired
	private EmpresaDao empresaDao;
	@Autowired
	private HttpSession httpSession;
	
	@GetMapping(value="")
	public String Usuarios(Model model){
	
		
		model.addAttribute("proceso","Proveedores");
		model.addAttribute("descripcion","Entdades que proveen al comercio");
		
		model.addAttribute("fragmento", "proveedores");
		model.addAttribute("plantilla", "proveedores");
		@SuppressWarnings("unchecked")
		List<Dato> datos=(List<Dato>) httpSession.getAttribute("userLog");
		if(datos!=null) {
			model.addAttribute("usulog",datos);
			model.addAttribute("dato",1);
		}
		Rol roles = rolDao.getByNombre("Proveedor");
		long id = 0;
		if (!roles.equals(null)) {
			
			id = roles.getId();
		}
		model.addAttribute("empresas",empresaDao.getEmpresaSinProveedor());
		
		model.addAttribute("rolList",rolDao.getAll());
		model.addAttribute("contactos",userDao.getRol(id));
		model.addAttribute("marcas",marcaDao.getAll());
	
		return "Principal";
	}	
	
	
	
}
