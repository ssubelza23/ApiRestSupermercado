package com.comercio.web.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

import com.comercio.web.model.Producto;


@Repository
@Transactional
public class ProductoDao {

	@PersistenceContext
	private EntityManager entityManager;
	public long create(Producto  p) {
		entityManager.persist(p);
		entityManager.flush();
		return p.getId();
	}
	public void delete(long id) {
		entityManager.createQuery("update Producto SET estado=0 where id=:id")
		.setParameter("id", id)
		.executeUpdate();
		
	}
	public long update(Producto p) {
		entityManager.merge(p);
		return p.getId();
		
	}
	public Producto getById(long id) {
		return entityManager.find(Producto.class, id);
	}
	@SuppressWarnings("unchecked")
	public List<Producto> getAll() {
		return entityManager.createQuery("select u from Producto u where estado=1").getResultList();
	}
}
