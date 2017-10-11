package com.comercio.web.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

import com.comercio.web.model.Empresa;

@Repository
@Transactional
public class EmpresaDao {

	@PersistenceContext
	private EntityManager entityManager;
	

	
	public void create(Empresa  p) {
		entityManager.persist(p);
		
	}
	public void delete(Empresa p) {
		if (entityManager.contains(p))
			entityManager.remove(p);
		else
			entityManager.remove(entityManager.merge(p));
		
	}

	public void update(Empresa p) {
		entityManager.merge(p);
		
	}

	public Empresa getById(long id) {
		return entityManager.find(Empresa.class, id);
	}
	@SuppressWarnings("unchecked")
	public List<Empresa> getEmpresaSinProveedor() {
		return entityManager.createQuery("select e from Empresa e where not exists (select p.empresa from Proveedor p where e=p.empresa)").getResultList();
	}
	public void limpiarEmpresa(long id) {
		entityManager.createQuery("update Empresa SET proveedor_id=null where proveedor_id=:id")
		.setParameter("id", id)
		.executeUpdate();
		
	}
	@SuppressWarnings("unchecked")
	public List<Empresa> getAll() {
		return entityManager.createQuery("select u from Empresa u").getResultList();
	}
}
