package com.comercio.web.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

import com.comercio.web.model.Categoria;

@Repository
@Transactional
public class CategoriaDao {

	@PersistenceContext
	private EntityManager entityManager;
	

	
	public void create(Categoria  categoria) {
		entityManager.persist(categoria);
		
	}
	public void delete(Categoria categoria) {
		if (entityManager.contains(categoria))
			entityManager.remove(categoria);
		else
			entityManager.remove(entityManager.merge(categoria));
		
	}

	public void update(Categoria categoria) {
		entityManager.merge(categoria);
		
	}

	public Categoria getById(long id) {
		return entityManager.find(Categoria.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Categoria> getAll() {
		return entityManager.createQuery("select u from Categoria u").getResultList();
	}
}
