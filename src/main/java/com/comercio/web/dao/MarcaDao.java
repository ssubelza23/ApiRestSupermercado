package com.comercio.web.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

import com.comercio.web.model.Marca;

@Repository
@Transactional
public class MarcaDao {

	@PersistenceContext
	private EntityManager entityManager;
	

	
	public void create(Marca  p) {
		entityManager.persist(p);
		
	}
	public void delete(long id) {
		entityManager.createQuery("update Marca SET estado=0 where id=:id")
		.setParameter("id", id)
		.executeUpdate();
		
	}

	public void update(Marca p) {
		entityManager.merge(p);
		
	}

	public Marca getById(long id) {
		return entityManager.find(Marca.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Marca> getAll() {
		return entityManager.createQuery("select u from Marca u").getResultList();
	}
}
