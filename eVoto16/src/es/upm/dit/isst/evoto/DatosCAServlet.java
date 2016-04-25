package es.upm.dit.isst.evoto;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.evoto.dao.CADAOImpl;
import es.upm.dit.isst.evoto.dao.CADAO;
import es.upm.dit.isst.evoto.model.CA;

@SuppressWarnings("serial")
public class DatosCAServlet extends HttpServlet {


	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		
		CADAO dao = CADAOImpl.getInstance();

		
		for (CA res : dao.todosCA()) {
			resp.getWriter().println(res);
		}	
	
	}

}
