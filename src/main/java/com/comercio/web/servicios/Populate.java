package com.comercio.web.servicios;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.comercio.web.dao.DatoDao;
import com.comercio.web.dao.RolesDao;
import com.comercio.web.dao.UsuarioDao;
import com.comercio.web.model.Dato;
import com.comercio.web.model.Rol;
import com.comercio.web.model.Usuario;


@Component
@Transactional

public class Populate {
@Autowired
private UsuarioDao userDao;

@Autowired
private RolesDao rolDao;

@Autowired
private DatoDao datosDao;

@PostConstruct
public void createUsers() {
	
	Rol rolAdministrador=new Rol();
	rolAdministrador.setNombre("Administrador");
	rolAdministrador.setDescripcion("Accesos a todos los procvesos del sistema");
	rolAdministrador.setEstado(1);
	long idRolAdministrador=rolDao.create(rolAdministrador);
	
	
	Rol rolCajero=new Rol();
	rolCajero.setNombre("Cajero");
	rolCajero.setDescripcion("Acceso solo modulo de ventas");
	rolCajero.setEstado(1);
	long idrolCajero=rolDao.create(rolCajero);
	
	
	Rol rolGerenteGeneral=new Rol();
	rolGerenteGeneral.setNombre("Gerente General");
	rolGerenteGeneral.setDescripcion("Acceso solo a los reportes ");
	rolGerenteGeneral.setEstado(1);
	long idRolGerenteGeneral=rolDao.create(rolCajero);
	

	Rol rolCliente=new Rol();
	rolCliente.setNombre("Cliente");
	rolCliente.setDescripcion("Sin privilegios");
	rolCliente.setEstado(1);
	long idRolCliente=rolDao.create(rolCliente);
	
	
	//ROles para usuario
	List<Rol> roles=new ArrayList<>();
	roles.add(rolDao.getById(idRolAdministrador));
	roles.add(rolDao.getById(idRolCliente));
	
	//datos de acceso para el usuario
	Dato d=new Dato();
	d.setLogin("admin");
	d.setClave("123456");
	long idDatos=datosDao.create(d);
	
	
	
	Usuario usuario=new Usuario();
	usuario.setNombre("nonbre");
	usuario.setAp("ap");
	usuario.setAm("am");
	usuario.setDNI(123456789);
	usuario.setSexo('M');
	usuario.setDireccion("direccion");
	usuario.setTelefono(123456789);
	usuario.setCorreo("correo@gmsil.com");
	usuario.setEstado(1);
	usuario.setRoles(roles);
	usuario.setDatos(datosDao.getById(idDatos));
	
	userDao.create(usuario);

	
}
}
