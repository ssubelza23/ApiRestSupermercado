package com.comercio.web.servicios;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.comercio.web.dao.DatoDao;
import com.comercio.web.dao.ProcesoDao;
import com.comercio.web.dao.RolesDao;
import com.comercio.web.dao.UsuarioDao;
import com.comercio.web.model.Dato;
import com.comercio.web.model.Proceso;
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

@Autowired
private ProcesoDao procesoDao;



@PostConstruct
public void createUsers() {
	//Creacion de Procesos 
	

	
	//Proceso Usuarios
	Proceso procesoUsuarios=new Proceso();
	procesoUsuarios.setNombre("Usuarios");
	procesoUsuarios.setEnlace("usuarios");
	procesoUsuarios.setDescripcion("Gestion de Usuarios, en este modulo se puede dar de alta a  nuevos usuarios,asignar  el rol correspondiente y si corresponde Datos de acceso al sistema");
	procesoUsuarios.setLogo("people");
	procesoUsuarios.setEstado(1);
	long idProcesoUsuario=procesoDao.create(procesoUsuarios);
	
	System.out.println(idProcesoUsuario);
	
	//Proceso Roles
	Proceso procesoRoles=new Proceso();
	procesoRoles.setNombre("Roles");
	procesoRoles.setEnlace("roles");
	procesoRoles.setDescripcion("Gestion de Roles, cada rol define que acciones y tareas (procesos) puede realizar el usuario, en este modulo puede crear nuevos roles y asignar tareas (Procesos) segun correponda");
	procesoRoles.setLogo("recent_actors");
	procesoRoles.setEstado(1);
	
	long idProcesoRoles=procesoDao.create(procesoRoles);
	
	//Proceso Roles
	Proceso procesoProcesos=new Proceso();
	procesoProcesos.setNombre("Procesos");
	procesoProcesos.setEnlace("procesos");
	procesoProcesos.setDescripcion("Gestion de Procesos, los procesos son el conjunto de tareas que se asigna a diferentes roles");
	procesoProcesos.setLogo("event_note");
	procesoProcesos.setEstado(1);
	long idProcesoProcesos=procesoDao.create(procesoProcesos);
	 // Creacion de Roles: Administrador, Cajero, Gerente General y cliente
	
	//procesos para administrador
		List<Proceso> procesos=new ArrayList<>();
		procesos.add(procesoDao.getByNombre("Usuarios"));
		procesos.add(procesoDao.getByNombre("Roles"));
		procesos.add(procesoDao.getByNombre("Procesos"));
	System.out.println(procesos);

	Rol rolAdministrador=new Rol();
	//Rol Administrador
	rolAdministrador.setNombre("Administrador");
	rolAdministrador.setDescripcion("Accesos a todos los procvesos del sistema");
	rolAdministrador.setEstado(1);
	rolAdministrador.setProcesos(procesos);
	rolDao.create(rolAdministrador);
	
	//Rol Cajero
	Rol rolCajero=new Rol();
	rolCajero.setNombre("Cajero");
	rolCajero.setDescripcion("Acceso solo modulo de ventas");
	rolCajero.setEstado(1);
	rolDao.create(rolCajero);
	
	
	//Rol Gerente General
	Rol rolGerenteGeneral=new Rol();
	rolGerenteGeneral.setNombre("Gerente General");
	rolGerenteGeneral.setDescripcion("Acceso solo a los reportes ");
	rolGerenteGeneral.setEstado(1);
	rolDao.create(rolGerenteGeneral);
	

	//Rol CLiente
	Rol rolCliente=new Rol();
	rolCliente.setNombre("Cliente");
	rolCliente.setDescripcion("Sin privilegios");
	rolCliente.setEstado(1);
	rolDao.create(rolCliente);
	

	
	
	
	
	//ROles para usuario
	List<Rol> roles=new ArrayList<>();
	roles.add(rolDao.getByNombre("Administrador"));
	roles.add(rolDao.getByNombre("Cajero"));
	
	//datos de acceso para el usuario
	Dato d=new Dato();
	d.setLogin("admin");
	d.setClave("123456");
	datosDao.create(d);
	

	
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
	usuario.setDatos(datosDao.getByLoginClave("admin", "123456"));
	usuario.setRoles(roles);
	userDao.create(usuario);
	

	
}
}
