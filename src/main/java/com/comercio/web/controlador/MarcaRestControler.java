package com.comercio.web.controlador;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.comercio.web.config.FileUploadMarcas;
import com.comercio.web.config.FileUploadUtility;
import com.comercio.web.dao.MarcaDao;
import com.comercio.web.model.FeedBack;
import com.comercio.web.model.Marca;
import com.comercio.web.model.bean.MarcaBean;
import com.comercio.web.model.bean.UsuarioBean;

@RestController
public class MarcaRestControler {

	@Autowired
	MarcaDao marcaDao;

	@GetMapping(value = "/listamarcas")
	public List<Marca> tablas_categoria(Model model) {
		return marcaDao.getAll();
	}
	@PostMapping(value = "/marcas")
	public FeedBack editarusuario(@Valid @ModelAttribute MarcaBean m,
			BindingResult result,
			HttpServletRequest request ){
		FeedBack feedBack = new FeedBack();
		System.out.println(m.getNombre()+"8888888888");
		if (result.hasErrors()) {
			feedBack.setLista_errores(result.getAllErrors());
			return feedBack;
		}
		try {
		Marca marca = new Marca();
		marca.setNombre(m.getNombre());
		marca.setEstado(1);
		String dni1 = "";
		if (!m.getLogo().getOriginalFilename().equals("")) {
			FileUploadMarcas.uploadFile(request, m.getLogo(), dni1 + m.getNombre());
			marca.setLogo(m.getNombre() + ".jpg");
		}
		if (!m.getId().equals("")) {
			marca.setId(Long.parseLong(m.getId()));
			marcaDao.update(marca);
			feedBack.setMensaje("Bien! datos modificados correctamente");
		} else {
			marcaDao.create(marca);
			feedBack.setMensaje("Bien! datos creados correctamente");
		}
		} catch (Exception error) {
			error.printStackTrace();
			feedBack.setMensaje("existe");
		}
		return feedBack;
	}
	@DeleteMapping(value = "/marcas/{id}")
	public String eliminarempresa(@PathVariable long id) {
		System.out.println(id);
		marcaDao.delete(id);
		return "Bien!. Datos eliminado correctamente.";
	}
}
