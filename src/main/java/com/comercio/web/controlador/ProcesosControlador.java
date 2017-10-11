package com.comercio.web.controlador;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.comercio.web.dao.ProcesoDao;
import com.comercio.web.model.Dato;
import com.comercio.web.model.Proceso;
import com.comercio.web.model.bean.ProcesoBean;


@Controller
@RequestMapping(value="/procesos")
public class ProcesosControlador {
	
	@Autowired
	private ProcesoDao procesosDao;
	@Autowired
	private HttpSession httpSession;
	
	@GetMapping(value="")
	public String procesos(Model model){
		List<Proceso> listProcesos=procesosDao.getAll();
		model.addAttribute("listaProcesos",listProcesos);
		
		model.addAttribute("proceso","Procesos");
		model.addAttribute("descripcion","Los procesos son las tareas que el sistema gestiona, en diferentes modulos y asignado a los perfiles correpondientes");
		
		model.addAttribute("newProceso",new ProcesoBean());
		
		model.addAttribute("fragmento", "procesos");
		model.addAttribute("plantilla", "procesos");
		
		@SuppressWarnings("unchecked")
		List<Dato> datos=(List<Dato>) httpSession.getAttribute("userLog");
		System.out.println(datos+"*/////////////////////////////////////////");
		if(datos!=null) {
			model.addAttribute("usulog",datos);
			model.addAttribute("dato",1);
		}
		
		return "Principal";
	}
	@GetMapping(value="/editProceso/{id}")
	public String editProceso(@PathVariable("id") long id, Model model) {
		System.out.println("****************"+id);
	model.addAttribute("fragmento", "editprocesos");
	model.addAttribute("plantilla", "procesos");
	Proceso proceso=procesosDao.getById(id);
	model.addAttribute("fragmento", "editProceso");
	model.addAttribute("plantilla", "procesos");
	model.addAttribute("nuevoProceso",new ProcesoBean());
	model.addAttribute("proceso",proceso);
	return "principal";
	}
	
	
	
	@PostMapping(value="/addProceso")
	public String submitProceso(@ModelAttribute("nuevoProceso") ProcesoBean r,
	BindingResult result,Model model) throws ParseException{
		System.out.println(r);
	procesosDao.create(new Proceso(r.getNombre(),r.getDescripcion(),r.getEnlace(),r.getLogo(),1));
	return "redirect:/procesos";
	}
	
	
	@PostMapping(value="/modProceso")
	public String modProceso(@ModelAttribute("nuevoProceso") ProcesoBean r,
	BindingResult result,Model model) throws ParseException{
	procesosDao.Update(r.getId(), r.getNombre(), r.getDescripcion(), r.getEnlace(), r.getLogo());
	return "redirect:/procesos";
	}
	
	@GetMapping(value="/eliminarProceso/{id}")
	public String eliminarProceso(@PathVariable("id") long id, Model model) {
		model.addAttribute("usuarioLogin","Hugo Ldesma Galvan");
		model.addAttribute("rol","Gerente General");
	procesosDao.limpiarRol(id);
	procesosDao.delete(id);
	
	return "redirect:/procesos";
	}
	
}
