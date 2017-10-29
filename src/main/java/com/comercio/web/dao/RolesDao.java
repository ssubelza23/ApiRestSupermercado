package com.comercio.web.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.comercio.web.model.Proceso;
import com.comercio.web.model.Rol;

@Repository
@Transactional
public class RolesDao {
	@PersistenceContext
	private EntityManager entityManager;

	
	public long create(Rol rol) {
		entityManager.merge(rol);
		entityManager.flush();

		return rol.getId();
	}

	@SuppressWarnings("unchecked")
	public List<Rol> getAll() {
		return entityManager.createQuery("select r from Rol r where estado=1").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Proceso> getProcesosAsignados(long id) {
		return entityManager
				.createQuery("select r.procesos from Rol r where r.id=:id ")
				.setParameter("id", id).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Proceso> getProcesosNoAsignados(long id) {
		return entityManager
				.createQuery("select p from Proceso p,Rol r where not exists(select rp from r.procesos rp where p=rp) and  r.id=:id ")
				.setParameter("id", id).getResultList();
	}

	public Rol getById(long id) {
		return entityManager.find(Rol.class, id);
	}

	public Rol getByNombre(String nombre) {
		return (Rol) entityManager.createQuery("select d from Rol d  where d.nombre=:nombre")
				.setParameter("nombre", nombre).getSingleResult();
	}
	public void delete(long id) {
		entityManager.createQuery("update Rol SET estado=0 where id=:id").setParameter("id", id).executeUpdate();
	}

	public void update(Rol rol) {
		entityManager.merge(rol);
	}

}
