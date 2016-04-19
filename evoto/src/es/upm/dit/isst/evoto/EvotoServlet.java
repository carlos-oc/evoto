package es.upm.dit.isst.evoto;

import java.io.IOException;
import javax.servlet.http.*;

import es.dit.upm.dit.isst.evoto.dao.CADAO;
import es.dit.upm.dit.isst.evoto.dao.CADAOImpl;
import es.upm.dit.isst.evoto.model.CA;

@SuppressWarnings("serial")
public class EvotoServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		CADAO dao = CADAOImpl.getInstance();
		CA nuevoCA = dao.crearCA("123456789A", "Prueba", "apellido1", "apellido2", "prueba@prueba.es", "MADRID");
		
		resp.setContentType("text/plain");
		//resp.getWriter().println(nuevoCA.toString());
		resp.getWriter().println("Hello, world");
	}
}
