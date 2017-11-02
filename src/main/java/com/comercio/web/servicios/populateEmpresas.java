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
		
		Empresa empresa1=new Empresa();
		empresa1.setNombre("Nini Centro de compras Mayoristas");
		empresa1.setEspecialidades("Abasto general");
		empresa1.setSector("Variedad de Productos");
		empresa1.setSede("La Plata. Buenos Aires");
		empresa1.setDireccioncomercial("Av 520 entre 23 y 25 -1900");
		empresa1.setEstado(1);
		empresaDao.create(empresa1);
		
		Empresa sancor=new Empresa();
		sancor.setNombre("Sancor, cooperativa Argentina de productores lecheros");
		sancor.setEspecialidades("Produccion, industrializacion distribucion y comercializacion de productos lecheros");
		sancor.setSector("Productos lecheros");
		sancor.setSede("La Plata. Buenos Aires");
		sancor.setDireccioncomercial("Av 520 entre 23 y 25 -1900");
		sancor.setEstado(1);
		empresaDao.create(sancor);
	}
		
}
