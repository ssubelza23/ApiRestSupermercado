
package com.comercio.web.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.comercio.web.dao.CategoriaDao;
import com.comercio.web.dao.DatoDao;
import com.comercio.web.dao.EmpresaDao;
import com.comercio.web.dao.ProcesoDao;
import com.comercio.web.dao.ProveedorDao;
import com.comercio.web.dao.RolesDao;
import com.comercio.web.dao.SectorDao;
import com.comercio.web.dao.UsuarioDao;
import com.comercio.web.dao.MarcaDao;
import com.comercio.web.model.Categoria;
import com.comercio.web.model.Dato;
import com.comercio.web.model.Empresa;
import com.comercio.web.model.Marca;
import com.comercio.web.model.Proceso;
import com.comercio.web.model.Proveedor;
import com.comercio.web.model.Rol;
import com.comercio.web.model.Sector;
import com.comercio.web.model.Usuario;

@Component
@Transactional

public class Populate {
	@Autowired
	private SectorDao sectorDao;

	@Autowired
	private CategoriaDao categoriaDao;
	@Autowired
	private UsuarioDao userDao;

	@Autowired
	private RolesDao rolDao;

	@Autowired
	private DatoDao datosDao;

	@Autowired
	private ProcesoDao procesoDao;
	
	@Autowired
	private MarcaDao MarcaDao;

	@Autowired
	private EmpresaDao empresaDao;
	
	@Autowired
	private ProveedorDao proveedorDao;

	@PostConstruct
	public void createUsers() {
		// Creacion de Procesos

		// Proceso Usuarios
		Proceso procesoUsuarios = new Proceso();
		procesoUsuarios.setNombre("Usuarios");
		procesoUsuarios.setEnlace("usuarios");
		procesoUsuarios.setDescripcion(
				"Gestion de Usuarios, en este modulo se puede dar de alta a  nuevos usuarios,asignar  el rol correspondiente y si corresponde Datos de acceso al sistema");
		procesoUsuarios.setLogo("people");
		procesoUsuarios.setEstado(1);
		long idProcesoUsuario = procesoDao.create(procesoUsuarios);

		System.out.println(idProcesoUsuario);

		// Proceso Roles
		Proceso procesoRoles = new Proceso();
		procesoRoles.setNombre("Roles");
		procesoRoles.setEnlace("roles");
		procesoRoles.setDescripcion(
				"Gestion de Roles, cada rol define que acciones y tareas (procesos) puede realizar el usuario, en este modulo puede crear nuevos roles y asignar tareas (Procesos) segun correponda");
		procesoRoles.setLogo("recent_actors");
		procesoRoles.setEstado(1);

		procesoDao.create(procesoRoles);

		// Proceso Roles
		Proceso procesoProcesos = new Proceso();
		procesoProcesos.setNombre("Procesos");
		procesoProcesos.setEnlace("procesos");
		procesoProcesos.setDescripcion(
				"Gestion de Procesos, los procesos son el conjunto de tareas que se asigna a diferentes roles");
		procesoProcesos.setLogo("event_note");
		procesoProcesos.setEstado(1);
		procesoDao.create(procesoProcesos);

		// Proceso Proveedores
		Proceso procesoProveedores = new Proceso();
		procesoProveedores.setNombre("Proveedores");
		procesoProveedores.setEnlace("proveedores");
		procesoProveedores.setDescripcion("Gestion de Proveedores, Entidades de aprovicionamiento del comercio");
		procesoProveedores.setLogo("local_shipping");
		procesoProveedores.setEstado(1);
		procesoDao.create(procesoProveedores);
		
		

		// Proceso Productos
		Proceso procesoProductos = new Proceso();
		procesoProductos.setNombre("Productos");
		procesoProductos.setEnlace("productos");
		procesoProductos.setDescripcion("Gestion de Productos, ...............");
		procesoProductos.setLogo("shopping_basket");
		procesoProductos.setEstado(1);
		procesoDao.create(procesoProductos);

		// Proceso Ventas
		Proceso procesoVentas = new Proceso();
		procesoVentas.setNombre("Ventas");
		procesoVentas.setEnlace("ventas");
		procesoVentas.setDescripcion("Gestion de Ventas, ...............");
		procesoVentas.setLogo("trending_up");
		procesoVentas.setEstado(1);
		procesoDao.create(procesoVentas);
		
		//empresas
		// Proceso Proveedores
		Proceso procesoEmpresas = new Proceso();
		 procesoEmpresas.setNombre("Empresas");
		 procesoEmpresas.setEnlace("empresas");
		 procesoEmpresas.setDescripcion("Gestion empresas");
		 procesoEmpresas.setLogo("domain");
		 procesoEmpresas.setEstado(1);
		 procesoDao.create( procesoEmpresas);
		
		// Proceso Marcas
		Proceso procesoMarcas = new Proceso();
		procesoMarcas.setNombre("Marcas");
		procesoMarcas.setEnlace("marcas");
		procesoMarcas.setDescripcion("marcas");
		procesoMarcas.setLogo("child_care");
		procesoMarcas.setEstado(1);
		procesoDao.create(procesoMarcas);
		
		// datos de marcas
		Marca crearMarcas1 = new Marca();
		crearMarcas1.setNombre("cocacola");
		crearMarcas1.setLogo("cocacola.jpg");
		crearMarcas1.setEstado(1);
		MarcaDao.create(crearMarcas1);
		
		
		Marca crearMarcas2 = new Marca();
		crearMarcas2.setNombre("pepsico");
		crearMarcas2.setLogo("pepsico.jpg");
		crearMarcas2.setEstado(1);
		MarcaDao.create(crearMarcas2);
		
		Marca crearMarcas3 = new Marca();
		crearMarcas3.setNombre("gillette");
		crearMarcas3.setLogo("gillette.jpg");
		crearMarcas3.setEstado(1);
		MarcaDao.create(crearMarcas3);
		
		Marca crearMarcas4 = new Marca();
		crearMarcas4.setNombre("head&shoulders");
		crearMarcas4.setLogo("head&shoulders.jpg");
		crearMarcas4.setEstado(1);
		MarcaDao.create(crearMarcas4);
		
		Marca crearMarcas5 = new Marca();
		crearMarcas5.setNombre("oldspice");
		crearMarcas5.setLogo("oldspice.jpg");
		crearMarcas5.setEstado(1);
		MarcaDao.create(crearMarcas5);
		
		Marca crearMarcas6 = new Marca();
		crearMarcas6.setNombre("pantene");
		crearMarcas6.setLogo("pantene.jpg");
		crearMarcas6.setEstado(1);
		MarcaDao.create(crearMarcas6);
		
		Marca crearMarcas7 = new Marca();
		crearMarcas7.setNombre("ace");
		crearMarcas7.setLogo("ace.jpg");
		crearMarcas7.setEstado(1);
		MarcaDao.create(crearMarcas7);
		
		Marca crearMarcas8 = new Marca();
		crearMarcas8.setNombre("ariel");
		crearMarcas8.setLogo("ariel.jpg");
		crearMarcas8.setEstado(1);
		MarcaDao.create(crearMarcas8);
		
		Marca crearMarcas9 = new Marca();
		crearMarcas9.setNombre("vicks");
		crearMarcas9.setLogo("vicks.jpg");
		crearMarcas9.setEstado(1);
		MarcaDao.create(crearMarcas9);
		
		Marca crearMarcas10= new Marca();
		crearMarcas10.setNombre("pampers");
		crearMarcas10.setLogo("pampers.jpg");
		crearMarcas10.setEstado(1);
		MarcaDao.create(crearMarcas10);
		

		// Proceso pedidos
		Proceso procesoPedidos = new Proceso();
		procesoPedidos.setNombre("Pedidos");
		procesoPedidos.setEnlace("pedidos");
		procesoPedidos.setDescripcion("Gestion de Pedidos, ...............");
		procesoPedidos.setLogo("assignment");
		procesoPedidos.setEstado(1);
		procesoDao.create(procesoPedidos);
		// Creacion de Roles: Administrador, Cajero, Gerente General y cliente

		// procesos para administrador
		List<Proceso> procesos = new ArrayList<>();
		procesos.add(procesoDao.getByNombre("Usuarios"));
		procesos.add(procesoDao.getByNombre("Roles"));
		procesos.add(procesoDao.getByNombre("Procesos"));
		procesos.add(procesoDao.getByNombre("Proveedores"));
		procesos.add(procesoDao.getByNombre("Productos"));
		procesos.add(procesoDao.getByNombre("Pedidos"));
		procesos.add(procesoDao.getByNombre("Marcas"));

		Rol rolAdministrador = new Rol();
		// Rol Administrador
		rolAdministrador.setNombre("Administrador");
		rolAdministrador.setDescripcion("Accesos a todos los procvesos del sistema");
		rolAdministrador.setEstado(1);
		rolAdministrador.setProcesos(procesos);
		rolDao.create(rolAdministrador);

		// Rol Cajero
		Rol rolCajero = new Rol();
		rolCajero.setNombre("Cajero");
		rolCajero.setDescripcion("Acceso solo modulo de ventas");
		rolCajero.setEstado(1);
		rolDao.create(rolCajero);

		// Rol Gerente General
		Rol rolGerenteGeneral = new Rol();
		rolGerenteGeneral.setNombre("Gerente General");
		rolGerenteGeneral.setDescripcion("Acceso solo a los reportes ");
		rolGerenteGeneral.setEstado(1);
		rolDao.create(rolGerenteGeneral);

		// Rol CLiente
		Rol rolCliente = new Rol();
		rolCliente.setNombre("Cliente");
		rolCliente.setDescripcion("Sin privilegios");
		rolCliente.setEstado(1);
		rolDao.create(rolCliente);

		// Rol Proveedor
		Rol rolProveedor = new Rol();
		rolProveedor.setNombre("Proveedor");
		rolProveedor.setDescripcion("Sin privilegios");
		rolProveedor.setEstado(1);
		rolDao.create(rolProveedor);

		// Roles para usuario Admin prueba
		List<Rol> roles = new ArrayList<>();
		roles.add(rolDao.getByNombre("Administrador"));
		roles.add(rolDao.getByNombre("Cajero"));
		roles.add(rolDao.getByNombre("Proveedor"));


		// datos de acceso para el usuario
		Dato d = new Dato();
		d.setLogin("admin");
		d.setClave("123456");
		datosDao.create(d);

		Usuario usuario = new Usuario();
		usuario.setNombre("Juan");
		usuario.setAp("Peres");
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
		
		// Roles para usuario2
				List<Rol> roles1 = new ArrayList<>();
				roles.add(rolDao.getByNombre("Cajero"));
				// datos de acceso para el usuario
				Dato d1 = new Dato();
				d1.setLogin("cliente");
				d1.setClave("123456");
				datosDao.create(d1);

				Usuario usuario1 = new Usuario();
				usuario1.setNombre("Maria");
				usuario1.setAp("Angeles");
				usuario1.setAm("Castrillo");
				usuario1.setDNI(112233445);
				usuario1.setSexo('F');
				usuario1.setDireccion("direccion");
				usuario1.setTelefono(123456789);
				usuario1.setCorreo("prueba@gmsil.com");
				usuario1.setEstado(1);
				usuario1.setDatos(datosDao.getByLoginClave("cliente", "123456"));
				usuario1.setRoles(roles1);
				userDao.create(usuario1);
				
		
				
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
