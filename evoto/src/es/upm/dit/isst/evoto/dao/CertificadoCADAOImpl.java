package es.upm.dit.isst.evoto.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.upm.dit.isst.evoto.model.CertificadoCA;


/**
 * Implementaci�n de la interfaz DAO para el Certificado de la CA
 * @author Carlos Ortego Casado
 *
 */
public class CertificadoCADAOImpl implements CertificadoCADAO {
	
	private static CertificadoCADAOImpl instance = null;
	
	/**
	 * Constructor privado para seguir un modelo singleton
	 */
	private CertificadoCADAOImpl(){
		
	}
	
	/**
	 * M�todo para obtener la instancia de la clase
	 * @return
	 */
	public static CertificadoCADAOImpl getInstance(){
		if(instance == null){
			instance = new CertificadoCADAOImpl();
		}
		return instance;
	}
	
	/**
	 * M�todo para crear un certificado CA
	 * S�lo crear� el certificado cuando no haya uno ya guardado en la base de datos
	 */
	@Override
	public void crearCertificadoCA() {

		CertificadoCA certdb = leerCertificadoCA();	
		if(certdb == null){
	        CertificadoCA cert = new CertificadoCA();
			EntityManager em = EMFService.get().createEntityManager();		
			em.persist(cert);
			em.close();
		} else {
			return;
		}
	}
	
	/**
	 * M�todo para obtener el certificado de la CA de la base de datos
	 */
	@Override
	public CertificadoCA leerCertificadoCA() {
		try{
		long id = 1;
		EntityManager em = EMFService.get().createEntityManager();
		CertificadoCA resCertificadoCA = em.find(CertificadoCA.class, id);
		em.close();
		return resCertificadoCA;
		} catch(Exception e){
			return null;
		}
	}
	
	/**
	 * M�todo para obtener todos los certificados registrados en la base de datos
	 * S�lo empleado para pruebas ya que a la hora de la implementaci�n s�lo habr� un certificado
	 * almacenado en la base de datos.
	 */
	@Override
	public List<CertificadoCA> todos(){
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("SELECT c FROM CertificadoCA c");
		List<CertificadoCA> lista = q.getResultList();
		em.close();
		return lista;
	}
	
	/**
	 * M�todo para resetear el certificado de la CA base de datos
	 * Emplear cuando hay un certificado de CA almacenado en la base de datos y se quiere crear uno nuevo
	 */
	@Override
	public boolean resetCertificadoCA(){
		try{
			EntityManager em = EMFService.get().createEntityManager();
			long id = 1;
			CertificadoCA cert = em.find(CertificadoCA.class, id);
			em.remove(cert);
			em.close();
			crearCertificadoCA();
			return true;
		} catch (Exception e){
			System.out.println(e);
			System.out.println("##########error de reset ca################");
			return false;
		}
	}

}
