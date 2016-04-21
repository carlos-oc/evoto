package es.dit.upm.dit.isst.evoto.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.upm.dit.isst.evoto.model.CA;

public class CADAOImpl implements CADAO {

	// Patrón de diseño singleton para que sólo exista un objeto de esta clase
	private static CADAOImpl instance;
	
	private CADAOImpl(){
		
	}
	
	public static CADAOImpl getInstance(){
		if(instance == null){
			instance = new CADAOImpl();
		}
		return instance;
	}
	
	@Override
	public CA crearCA(String dni, String nombre, String apellido1, String apellido2, String email, String provincia) {
		
		// Crear objeto
		CA nuevoCA = new CA(dni, nombre, apellido1, apellido2, email, provincia);
		
		// Guardar en BD
		EntityManager em = EMFService.get().createEntityManager();
		em.persist(nuevoCA);
		em.close();
		return nuevoCA;
	}

	@Override
	public CA leerCA(String dni) {
		EntityManager em = EMFService.get().createEntityManager();
		CA resCA = em.find(CA.class, dni);

		em.close();
		return resCA;
//		CA resCA = null;
//		EntityManager em = EMFService.get().createEntityManager();
//		Query q = em.createQuery("SELECT c FROM CA c WHERE c.dni = :dni LIMIT 1");
//		q.setParameter("dni", dni);
//		List<CA> res = q.getResultList();
//		Iterator<CA> iterator = res.iterator();
//		while(iterator.hasNext()){
//			resCA = (CA)iterator.next();
//		}
//		em.close();
//		return resCA;
	}

	@Override
	public List<CA> todosCA() {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("SELECT c FROM CA c");
		List<CA> res = q.getResultList();
		em.close();
		return res;
	}

	@Override
	public void updateCA(CA cambiarCA) {
		EntityManager em = EMFService.get().createEntityManager();
		em.merge(cambiarCA);
		em.close();
	}

	@Override
	public void deleteCA(String dni) {		
		EntityManager em = EMFService.get().createEntityManager();
		CA ca = em.find(CA.class, dni);
		em.remove(ca);
		em.close();
	}

}
