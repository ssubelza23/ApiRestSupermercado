package com.comercio.web.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

import com.comercio.web.model.Marca;
import com.comercio.web.model.Sector;

@Repository
@Transactional
public class SectorDao {

	@PersistenceContext
	private EntityManager entityManager;
	

	
	public void create(Sector  p) {
		entityManager.persist(p);
		
	}
	public void delete(long id) {
		entityManager.createQuery("update Sector SET estado=0 where id=:id")
		.setParameter("id", id)
		.executeUpdate();
		
	}

	public void update(Sector p) {
		entityManager.merge(p);
		
	}

	public Sector getById(long id) {
		return entityManager.find(Sector.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Sector> getAll() {
		return entityManager.createQuery("select u from Sector u where estado=1").getResultList();
	}
}
