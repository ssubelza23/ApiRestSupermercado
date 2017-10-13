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
public class ProcesoDao {
	@PersistenceContext
	private EntityManager entityManager;

	//creacion de nuevo Rol
	public long create(Proceso proceso) {
		entityManager.merge(proceso);
		return proceso.getId();
	}
	@SuppressWarnings("unchecked")
	public List<Proceso> getAll() {
		return entityManager.createQuery("select p from Proceso p where estado=1").getResultList();
	}
	public void limpiarRol(long id) {
		entityManager.createQuery("update Proceso SET rol_id=null where rol_id=:id")
		.setParameter("id", id)
		.executeUpdate();
		
	}
	public Proceso getById(long id) {
		return entityManager.find(Proceso.class, id);
	}
	public void update(Proceso proceso) {
		entityManager.merge(proceso);
	}
	public void delete(long id) {
		entityManager.createQuery("update Proceso SET estado=0 where id=:id")
		.setParameter("id", id)
		.executeUpdate();
	}
	
	//Update procesos Set nombre='José',descripcion='des',enlace='usurios', logo='logo' Where id=1
	public void Update(long id,String nombre,String descripcion,String enlace,String logo){
	System.out.println(id+nombre+descripcion+enlace+logo+"*********************");
	
			entityManager.createNativeQuery("Update procesos  Set nombre=:nombre, descripcion=:descripcion, enlace=:enlace, logo=:logo Where id=:id")
			.setParameter("nombre", nombre).setParameter("descripcion", descripcion)
			.setParameter("enlace", enlace).setParameter("logo", logo).setParameter("id", id)
			.executeUpdate();
	

	}
	public void delRolProceso(long id){
		entityManager.createNativeQuery("DELETE FROM procesos p WHERE ye.id IN (:list)")
		.setParameter("id", id)
		.executeUpdate();
}
	//Update procesos Set nombre='José',descripcion='des',enlace='usurios', logo='logo' Where id=1
	public void UpdateRolProceso(Proceso procesos,long id){
			entityManager.createNativeQuery("Update roles  Set procesos=:procesos Where id=:id")
			.setParameter("procesos", procesos).setParameter("id", id)
			.executeUpdate();
	}
	public Proceso getByNombre(String nombre) {
		return  (Proceso) entityManager.createQuery("select d from Proceso d  where d.nombre=:nombre and d.estado=1")
				.setParameter("nombre", nombre)
				.getSingleResult();
	}
}
