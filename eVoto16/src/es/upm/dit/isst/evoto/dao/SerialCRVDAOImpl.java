package es.upm.dit.isst.evoto.dao;

import javax.persistence.EntityManager;

import es.upm.dit.isst.evoto.model.SerialCRV;

public class SerialCRVDAOImpl implements SerialCRVDAO {
	// Patr�n de dise�o singleton para que s�lo exista un objeto de esta clase
		private static SerialCRVDAOImpl instance;
		
		private SerialCRVDAOImpl(){
			
		}
		
		public static SerialCRVDAOImpl getInstance(){
			if(instance == null){
				instance = new SerialCRVDAOImpl();
			}
			return instance;
		}
	@Override
	public SerialCRV crearSerial(String serial) {
		SerialCRV nuevoSerial = new SerialCRV(serial);
		EntityManager em = EMFService.get().createEntityManager();
		em.persist(nuevoSerial);
		em.close();
		return nuevoSerial;
	}

	@Override
	public SerialCRV leerSerial(String serial) {
		EntityManager em = EMFService.get().createEntityManager();
		SerialCRV serialRes = em.find(SerialCRV.class, serial); 
		em.close();
		return serialRes;
	}

	@Override
	public void deleteSerial(String serial) {
		EntityManager em = EMFService.get().createEntityManager();
		SerialCRV serialRes = em.find(SerialCRV.class, serial); 
		em.remove(serialRes);
		em.close();	

	}

}
