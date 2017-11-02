package com.comercio.web.controlador;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


import com.comercio.web.config.FileUploadProductos;
import com.comercio.web.dao.CategoriaDao;
import com.comercio.web.dao.EmpresaDao;
import com.comercio.web.dao.MarcaDao;
import com.comercio.web.dao.ProductoDao;
import com.comercio.web.dao.ProveedorDao;
import com.comercio.web.dao.RolesDao;
import com.comercio.web.dao.SectorDao;
import com.comercio.web.dao.UsuarioDao;
import com.comercio.web.model.Categoria;
import com.comercio.web.model.FeedBack;
import com.comercio.web.model.Marca;
import com.comercio.web.model.Producto;
import com.comercio.web.model.Proveedor;
import com.comercio.web.model.Sector;
import com.comercio.web.model.bean.ProductoBean;





@RestController
public class ProductoRestControler {
	@Autowired
	UsuarioDao userDao;

	@Autowired
	RolesDao rolDao;
	@Autowired
	CategoriaDao categoriaDao;
	@Autowired
	EmpresaDao empresaDao;
	@Autowired
	SectorDao sectorDao;
	@Autowired
	MarcaDao marcaDao;
	@Autowired
	ProveedorDao proveedorDao;
	
	@Autowired
	ProductoDao productoDao;

	@GetMapping(value = "/listaproductos")
	public List<Producto> tablas_categoria(Model model) {
		return productoDao.getAll();
	}

	@PostMapping(value = "/productos")
	public FeedBack  editarusuario(@ModelAttribute @Valid ProductoBean p,BindingResult result,
		HttpServletRequest request ){
	FeedBack feedBack = new FeedBack();
	if (result.hasErrors()) {
		feedBack.setLista_errores(result.getAllErrors());
		return feedBack;
	}
	try {
	Producto producto = new Producto();
	producto.setCodigoBarra(p.getCodigoBarras());
	producto.setNombre(p.getNombre());
	producto.setContenidoneto(p.getContenidoNeto());
	producto.setDetalle(p.getDetalles());
	producto.setPresentacion(p.getPresentacion());
	producto.setEstado(1);
	producto.setImagen(p.getNombre()+".jpg");
	producto.setMarca(marcaDao.getById(Long.parseLong(p.getIdMarca())));
	producto.setCategoria(categoriaDao.getById(Long.parseLong(p.getIdCategoria())));
	producto.setSector(sectorDao.getById(Long.parseLong(p.getIdSector())));
	producto.setProveedor(proveedorDao.getById(Long.parseLong(p.getIdProveedor())));
	
	
	
	
	
	String dni1 = "";
	if (!p.getImagen().getOriginalFilename().equals("")) {
		FileUploadProductos.uploadFile(request, p.getImagen(), dni1 + p.getNombre());
		producto.setImagen(p.getNombre() + ".jpg");
	}
	if (!p.getId().equals("")) {
		producto.setId(Long.parseLong(p.getId()));
		productoDao.update(producto);
		feedBack.setMensaje("Bien! datos modificados correctamente");
	
		
	} else {
		long id=productoDao.create(producto);
		feedBack.setMensaje("Bien! datos creados correctamente");
	
		
	}

	} catch (Exception error) {
		error.printStackTrace();
		feedBack.setMensaje("existe");
	}
	
	return feedBack;
}
	@DeleteMapping(value = "/productos/{id}")
	public String eliminarProveedor(@PathVariable long id) {
		productoDao.delete(id);
		return "Bien!. Proveedor eliminado correctamente.";
	}
}
