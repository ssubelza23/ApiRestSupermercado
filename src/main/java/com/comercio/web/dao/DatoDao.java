package com.comercio.web.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

import com.comercio.web.model.Dato;
import com.comercio.web.model.Proveedor;

@Repository
@Transactional
public class DatoDao {

	@PersistenceContext
	private EntityManager entityManager;

	public long create(Dato p) {
		entityManager.merge(p);
		entityManager.flush();
		return p.getId();
	}

	public Dato getByLoginClave(String login, String clave) {
		return (Dato) entityManager.createQuery("select d from Dato d where d.login=MD5(:login) and d.clave=MD5(:clave)")
				.setParameter("login", login).setParameter("clave", clave).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<Dato> getByDatos(String login, String clave) {
		return (List<Dato>) entityManager.createQuery("select d from Dato d where d.login=MD5(:login) and d.clave=MD5(:clave)")
				.setParameter("login", login).setParameter("clave", clave).getResultList();
	}

	public void update(Dato p) {
		entityManager.merge(p);

	}

	public Dato getById(long id) {
		return entityManager.find(Dato.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Proveedor> getAll() {
		return entityManager.createQuery("select u from Dato u where estado=1").getResultList();
	}
}
