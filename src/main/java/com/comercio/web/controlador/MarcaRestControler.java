package com.comercio.web.controlador;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.comercio.web.config.FileUploadUtility;
import com.comercio.web.dao.MarcaDao;
import com.comercio.web.model.Marca;


@RestController
public class MarcaRestControler {

	@Autowired
	MarcaDao marcaDao;
@GetMapping(value="/marcas")
public List<Marca> tablas_categoria(Model model) {
	return marcaDao.getAll();
}
@PostMapping(value="/marcas")
public String editarusuario(@RequestParam("nombre") String nombre,
		@RequestParam("id") String id,
		@RequestParam("detalle") String detalles,
		@RequestParam("logo") MultipartFile logo,
		HttpServletRequest request) {
	String mensaje="";
	Marca marca=new Marca();
	marca.setNombre(nombre);
	marca.setDetalle(detalles);
	
	String dni1="";
	if(!logo.getOriginalFilename().equals("")){
		FileUploadUtility.uploadFile(request,logo,dni1+nombre);
		marca.setLogo(nombre+".jpg");
	}
	if(!id.equals("")) {
		marca.setId(Long.parseLong(id));
		marcaDao.update(marca);
		mensaje="Bien! datos modificados correctamente";
	}else {
		marcaDao.create(marca);
		mensaje="Bien! datos creados correctamente";
	}
return mensaje;
}



@DeleteMapping(value="/marcas/{id}")
public String eliminarempresa(@PathVariable  long id) {
	System.out.println(id);
marcaDao.delete(id);
return "Bien!. Proveedor eliminado correctamente.";
}
}
	    