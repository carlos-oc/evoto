package es.dit.upm.dit.isst.evoto.dao;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.upm.dit.isst.evoto.model.CA;
import es.upm.dit.isst.evoto.model.CRV;

public class CRVDAOImpl implements CRVDAO {
	
	// Patrón de diseño singleton para que sólo exista un objeto de esta clase
	private static CRVDAOImpl instance;
	
	private CRVDAOImpl(){
		
	}
	
	public static CRVDAOImpl getInstance(){
		if(instance == null){
			instance = new CRVDAOImpl();
		}
		return instance;
	}
	
	@Override
	public CRV crearCRV(Long codigo, String nombreCompleto, String provincia, String partido, int codPartido,
			int nVotos) {
		// Crear objeto
		CRV nuevoCRV = new CRV(codigo, nombreCompleto, provincia, partido, codPartido);
		// Guardar en BD
		EntityManager em = EMFService.get().createEntityManager();
		em.persist(nuevoCRV);
		em.close();
		return nuevoCRV;
	}

	@Override
	public CRV leerCRV(Long codigo) {
//		CRV resCRV = null;
//		EntityManager em = EMFService.get().createEntityManager();
//		Query q = em.createQuery("SELECT c FROM CRV c WHERE c.codigo = :codigo LIMIT 1");
//		q.setParameter("codigo", codigo);
//		List<CRV> res = q.getResultList();
//		Iterator<CRV> iterator = res.iterator();
//		while(iterator.hasNext()){
//			resCRV = (CRV)iterator.next();
//		}
//		em.close();
//		return resCRV;
		
		EntityManager em = EMFService.get().createEntityManager();
		CRV resCRV = em.find(CRV.class, codigo);

		em.close();
		return resCRV;
	}

	@Override
	public List<CRV> leerPorPartido(int codPartido) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("SELECT c FROM CRV c WHERE c.codPartido = :codPartido");
		q.setParameter("codPartido", codPartido);
		List<CRV> res = q.getResultList();
		em.close();
		return res;
	}

	@Override
	public List<CRV> todosCRV() {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("SELECT c FROM CRV c");
		List<CRV> res = q.getResultList();
		em.close();
		return res;
	}

	@Override
	public void updateCRV(CRV cambiarCRV) {
		EntityManager em = EMFService.get().createEntityManager();
		em.merge(cambiarCRV);
		em.close();
	}

	@Override
	public void deleteCRV(Long codigo) {
		EntityManager em = EMFService.get().createEntityManager();
		CRV ca = em.find(CRV.class, codigo);
		em.remove(ca);
		em.close();
	}

}
