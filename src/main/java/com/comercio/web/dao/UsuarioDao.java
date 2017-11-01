package com.comercio.web.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import com.comercio.web.model.Dato;
import com.comercio.web.model.Rol;
import com.comercio.web.model.Usuario;



@Repository
// inicio y fin de las transacciones automaticamente
@Transactional
public class UsuarioDao {

	@PersistenceContext
	private EntityManager entityManager;
	

	//creacion de usuaio
	public long create(Usuario usuario) {
		entityManager.merge(usuario);
		entityManager.flush();
		return usuario.getId();
	}
	
	//asignacion de clave
	public void AsignarDatos(Dato dato) {
		entityManager.persist(dato);
	}

	public void delete(long id) {
		
	entityManager.createQuery("update Usuario SET estado=0 where id=:id")
	.setParameter("id", id)
	.executeUpdate();
	
	}

	public void update(Usuario usuario) {
		entityManager.merge(usuario);
	}

	public Usuario getById(long id) {
		return entityManager.find(Usuario.class, id);
	}
	@SuppressWarnings("unchecked")
	public List<Usuario> getAll() {
		
		return entityManager.createQuery("select u from Usuario u where estado=1").getResultList();
	}
	
	public Usuario getByDatos(Dato datos) {
		return (Usuario) entityManager.createQuery("select u from Usuario u where u.datos=:datos and estado=1")
				.setParameter("datos", datos)
				.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> getUsuRol() {
		return entityManager.createQuery("select u from Usuario u").getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<Usuario> getByDato(String login,String clave) {
		return   entityManager.createQuery("select d from Usuario d  where d.datos.login=:login and d.datos.clave=:clave and d.Estado=1")
				.setParameter("login", login).setParameter("clave", clave)
				.getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<Usuario> getByRoles(List<Rol> roles) {
		return  entityManager.createQuery("select u from Usuario u join  u.roles=:roles ")
				.setParameter("roles", roles)
				.getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<Dato> getDatoById(long id) {
		return  entityManager.createQuery("select d from Dato d  where d.id=:id")
				.setParameter("id", id)
				.getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<Usuario> getRol(long id) {
		return  entityManager.createQuery("select d from Usuario d join  d.roles r where r.id=:id and d.Estado=1")
				.setParameter("id", id)
				.getResultList();
	}
	public void Update(long id,String nombre,String descripcion,String enlace,String logo){
			entityManager.createNativeQuery("Update procesos  Set nombre=:nombre, descripcion=:descripcion, enlace=:enlace, logo=:logo Where id=:id")
			.setParameter("nombre", nombre).setParameter("descripcion", descripcion)
			.setParameter("enlace", enlace).setParameter("logo", logo).setParameter("id", id)
			.executeUpdate();
	

	}


	

}
