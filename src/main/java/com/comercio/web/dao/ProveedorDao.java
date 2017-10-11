package com.comercio.web.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import com.comercio.web.model.Proveedor;

@Repository
@Transactional
public class ProveedorDao {

	@PersistenceContext
	private EntityManager entityManager;
	

	
	public long create(Proveedor  p) {
		entityManager.persist(p);
		entityManager.flush();
		return p.getId();
	}
	public void delete(long id) {
		entityManager.createQuery("update Proveedor SET estado=0 where id=:id")
		.setParameter("id", id)
		.executeUpdate();
		
	}

	public void update(Proveedor p) {
		entityManager.merge(p);
		
	}

	public Proveedor getById(long id) {
		return entityManager.find(Proveedor.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Proveedor> getAll() {
		return entityManager.createQuery("select u from Proveedor u where estado=1").getResultList();
	}
}
