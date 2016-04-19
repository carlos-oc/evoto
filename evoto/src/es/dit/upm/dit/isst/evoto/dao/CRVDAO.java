package es.dit.upm.dit.isst.evoto.dao;

import java.util.List;

import es.upm.dit.isst.evoto.model.CRV;

public interface CRVDAO {
	
	// Para añadir político
	public CRV crearCRV(Long codigo, String nombreCompleto, String provincia, String partido, int codPartido, int nVotos);
	//Buscar individual
	public CRV leerCRV(Long codigo);
	//Buscar por partido
	public List<CRV> leerPorPartido(int codPartido);
	//Buscar todos
	public List<CRV> todosCRV();
	//Actualizar un registro
	public void updateCRV(CRV cambiarCRV);
	//Eliminar registro
	public void deleteCRV(Long codigo);
	
	
}
