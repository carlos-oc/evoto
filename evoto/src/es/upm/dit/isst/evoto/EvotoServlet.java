package es.upm.dit.isst.evoto;

import java.io.IOException;
import javax.servlet.http.*;

import es.dit.upm.dit.isst.evoto.dao.CADAO;
import es.dit.upm.dit.isst.evoto.dao.CADAOImpl;
import es.upm.dit.isst.evoto.model.CA;

@SuppressWarnings("serial")
public class EvotoServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		
		CADAO dao = CADAOImpl.getInstance();
		
		//CA nuevoCA = new CA("000000A", "Fulanito", "de tal", "aaaaa2", "aaaaaa@prueba.es", "MADRID");
		//CA nuevoCA2 = new CA("66666666A", "Prueba3", "bbbb1", "bbbb2", "bbbbbbb@prueba.es", "MADRID");
		//dao.deleteCA("66666666A");
		//List<CA> res = dao.todosCA();
		CA dato =dao.leerCA("000000A");
		resp.getWriter().println(dato);
		/*
				for (CA res : dao.todosCA()) {
					resp .getWriter().println( res );
				}
		*/
		resp.getWriter().println("Hello, world");
	}
}
