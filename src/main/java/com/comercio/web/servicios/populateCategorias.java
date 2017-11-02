package com.comercio.web.servicios;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.standard.expression.FragmentSignatureUtils;

import com.comercio.web.dao.CategoriaDao;
import com.comercio.web.dao.EmpresaDao;
import com.comercio.web.dao.SectorDao;
import com.comercio.web.model.Categoria;
import com.comercio.web.model.Empresa;
import com.comercio.web.model.Sector;


@Component
@Transactional

public class populateCategorias {
	@Autowired
	private CategoriaDao categoriaDao;


	@PostConstruct
	public void createSectores() {
		
		Categoria galletas =new Categoria();
		galletas.setNombre("Galletas y Dulces");
		galletas.setEstado(1);
		categoriaDao.create(galletas);
		
		Categoria enlatados =new Categoria();
		enlatados.setNombre("Enlatados");
		galletas.setEstado(1);
		categoriaDao.create(enlatados);
		
		Categoria bebidas =new Categoria();
		bebidas.setNombre("Bebidas sin alcohol");
		bebidas.setEstado(1);
		categoriaDao.create(bebidas);
		
		Categoria frios =new Categoria();
		frios.setNombre("Frios");
		frios.setEstado(1);
		categoriaDao.create(frios);
		
		Categoria limpieza =new Categoria();
		limpieza.setNombre("Limpieza");
		limpieza.setEstado(1);
		categoriaDao.create(limpieza);
		
		
	}
		
}
