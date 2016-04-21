package es.upm.dit.isst.evoto;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.dit.upm.dit.isst.evoto.dao.CADAO;
import es.dit.upm.dit.isst.evoto.dao.CADAOImpl;
import es.upm.dit.isst.evoto.model.CA;

@SuppressWarnings("serial")
public class CAServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		RequestDispatcher view = req.getRequestDispatcher("ca.jsp");
        view.forward(req, resp);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		// Obtener instancia del objeto dao para manejar BD 
		CADAO dao = CADAOImpl.getInstance();

		// Obtener datos del formulario
		String nombre 		= 	req.getParameter("nombre");
		String apellido1 	= 	req.getParameter("apellido1");
		String apellido2 	= 	req.getParameter("apellido2");
		String dni			= 	req.getParameter("dni");
		String email 		=	req.getParameter("email");
		String provincia	=	req.getParameter("provincia");
		
		// Comprobar que se han rellenado los datos
		if(	
			nombre 		== 	null || nombre.isEmpty() 	||
			apellido1	== 	null || apellido1.isEmpty() ||
			apellido2	== 	null || apellido2.isEmpty() ||
			dni			== 	null || dni.isEmpty() 		||
			email		== 	null || email.isEmpty() 	||
			provincia	== 	null || provincia.isEmpty()
		){
			// Si no están rellenos todos se le devuelve a la página
			String errmsg = "Error. Debe rellenar todos los campos para poder realizar la solicitud";
			req.setAttribute("errmsg", errmsg); // Mensaje de error
			
			RequestDispatcher view = req.getRequestDispatcher("ca.jsp");
	        view.forward(req, resp);
	        
		} else {
			//Comporbar si existe en la base de datos
			CA res = dao.leerCA(dni);
			if (res == null){
				// TODO Generar certificado con OpenSSL
				// ...........
				
				//Guardar en BD
				dao.crearCA(dni, nombre, apellido1, apellido2, email, provincia);
				
				// Mostrar página
				RequestDispatcher view = req.getRequestDispatcher("enviado.jsp");
		        view.forward(req, resp);
			
			} else {
				// Si no está en la BD se le devuelve a la página
				// Si no están rellenos todos se le devuelve a la página
				String errmsg = "Error. Ese DNI ya está registrado en la base de datos.";
				req.setAttribute("errmsg", errmsg); // Mensaje de error
				
				RequestDispatcher view = req.getRequestDispatcher("ca.jsp");
		        view.forward(req, resp);
			}
		}
	}
}
