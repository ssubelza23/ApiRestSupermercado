package com.comercio.web.servicios;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.standard.expression.FragmentSignatureUtils;

import com.comercio.web.dao.EmpresaDao;
import com.comercio.web.dao.SectorDao;
import com.comercio.web.model.Empresa;
import com.comercio.web.model.Sector;


@Component
@Transactional

public class populateSectores {
	@Autowired
	private SectorDao sectorDao;


	@PostConstruct
	public void createSectores() {
		
		Sector frutasVerduras =new Sector();
		frutasVerduras.setNombre("Frutas y Verduras");
		frutasVerduras.setDetalle("frutas y verduras venta por peso y unidad");
		frutasVerduras.setLogo("filter_vintage");
		frutasVerduras.setEstado(1);
		sectorDao.create(frutasVerduras);
	
		Sector almacen =new Sector();
		almacen.setNombre("Almacen");
		almacen.setDetalle("Este sector comprede los productos o bienes de convivencia (caramelos,galletas,etc)");
		almacen.setLogo("store");
		almacen.setEstado(1);
		sectorDao.create(almacen);
		
		Sector externo =new Sector();
		externo.setNombre("Externo");
		externo.setDetalle("Este sector comprende a productos inflamables: carbon gas y leña");
		externo.setLogo("local_gas_station");
		externo.setEstado(1);
		sectorDao.create(externo);
		
		Sector alimento =new Sector();
		alimento.setNombre("Alimento");
		alimento.setDetalle("Este sector comprende a alimento para mascotas");
		alimento.setLogo("pets");
		alimento.setEstado(1);
		sectorDao.create(alimento);
		
	}
		
}
