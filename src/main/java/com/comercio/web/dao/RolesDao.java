package com.comercio.web.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import com.comercio.web.model.Rol;

@Repository
@Transactional
public class RolesDao {
	@PersistenceContext
	private EntityManager entityManager;

	//creacion de nuevo Rol
	public long create(Rol rol) {
		entityManager.persist(rol);
		entityManager.flush();
		return rol.getId();
	}

	@SuppressWarnings("unchecked")
	public List<Rol> getAll() {
		return entityManager.createQuery("select r from Rol r where estado=1").getResultList();
	}
	public Rol getById(long id) {
		return entityManager.find(Rol.class, id);
	}
	@SuppressWarnings("unchecked")
	public List<Rol> getByNombre(String nombre) {
		return  entityManager.createQuery("select d from Rol d  where d.nombre=:nombre")
				.setParameter("nombre", nombre)
				.getResultList();
	}
	public void update(Rol rol) {
		entityManager.merge(rol);
	}

}
