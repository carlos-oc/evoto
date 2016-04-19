package es.dit.upm.dit.isst.evoto.dao;

import java.util.List;

import es.upm.dit.isst.evoto.model.CA;

public interface CADAO {
	
	//Método para crear un objeto de la clase CA
	
	public CA crearCA(String dni, String nombre, String apellido1, String apellido2, String email, String provincia);
	
	// Método para leer de la base de datos
	
	public CA leerCA(String dni);
	
	// Método para leer todos los registrados
	
	public List<CA> todosCA();
	
	//Método para cambiar un elemento de la bd
	
	public void updateCA(CA cambiarCA);
	
	// Método para borrar elemento de la bd
	
	public void deleteCA(String dni);
	
}
