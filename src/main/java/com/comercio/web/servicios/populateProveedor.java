package com.comercio.web.servicios;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.standard.expression.FragmentSignatureUtils;

import com.comercio.web.dao.CategoriaDao;
import com.comercio.web.dao.EmpresaDao;
import com.comercio.web.dao.ProveedorDao;
import com.comercio.web.dao.SectorDao;
import com.comercio.web.dao.UsuarioDao;
import com.comercio.web.model.Categoria;
import com.comercio.web.model.Empresa;
import com.comercio.web.model.Proveedor;
import com.comercio.web.model.Sector;
import com.comercio.web.model.Usuario;


@Component
@Transactional

public class populateProveedor {
	@Autowired
	private ProveedorDao proveedorDao;
	@Autowired
	private UsuarioDao usuarioDao;


	@PostConstruct
	public void createProveedor() {
		
		
		
		
	}
		
}
