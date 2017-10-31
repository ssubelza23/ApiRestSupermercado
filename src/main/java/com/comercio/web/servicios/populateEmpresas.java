package com.comercio.web.servicios;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.comercio.web.dao.EmpresaDao;
import com.comercio.web.model.Empresa;


@Component
@Transactional

public class populateEmpresas {
	@Autowired
	private EmpresaDao empresaDao;


	@PostConstruct
	public void createEmpresas() {
		Empresa empresa=new Empresa();
		empresa.setNombre("Coca-Cola Reginald Lee S.A.");
		empresa.setEspecialidades("Embotelladora y Distribuidora de Aguas y bebidas sin alcohol");
		empresa.setSector("Alimentación y bebidas");
		empresa.setSede("Camino Gral Belgrano Km 31.5 Ranelgh, Buenos Aires 1886 Argentina");
		empresa.setEstado(1);
		empresaDao.create(empresa);
	}
		
}
