package com.comercio.web.controlador;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.comercio.web.dao.CategoriaDao;
import com.comercio.web.dao.EmpresaDao;
import com.comercio.web.dao.MarcaDao;
import com.comercio.web.dao.ProductoDao;
import com.comercio.web.dao.ProveedorDao;
import com.comercio.web.dao.RolesDao;
import com.comercio.web.dao.UsuarioDao;
import com.comercio.web.model.Producto;
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
	public String editarusuario(@ModelAttribute ProductoBean p) {
		String mensaje = "llego";
		System.out.println(p.getNombre()+"nomrbes productos/////");
		return mensaje;
	}
	@DeleteMapping(value = "/productos/{id}")
	public String eliminarProveedor(@PathVariable long id) {
		productoDao.delete(id);
		return "Bien!. Proveedor eliminado correctamente.";
	}
}
