package es.dit.upm.dit.isst.evoto.dao;

import java.util.List;

import es.upm.dit.isst.evoto.model.CA;

public interface CADAO {
	
	//M�todo para crear un objeto de la clase CA
	
	public CA crearCA(String dni, String nombre, String apellido1, String apellido2, String email, String provincia);
	
	// M�todo para leer de la base de datos
	
	public CA leerCA(String dni);
	
	// M�todo para leer todos los registrados
	
	public List<CA> todosCA();
	
	//M�todo para cambiar un elemento de la bd
	
	public void updateCA(CA cambiarCA);
	
	// M�todo para borrar elemento de la bd
	
	public void deleteCA(String dni);
	
}
