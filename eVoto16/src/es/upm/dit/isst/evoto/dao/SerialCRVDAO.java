package es.upm.dit.isst.evoto.dao;

import es.upm.dit.isst.evoto.model.SerialCRV;

public interface SerialCRVDAO {
	// Añadir serial a la BD
	public SerialCRV crearSerial(String serial);
	// Buscar serial en la BD
	public SerialCRV leerSerial(String serial);
	//Eliminar un serial de la BD (Sólo para pruebas)
	public void deleteSerial(String serial);
	
}
