package es.upm.dit.isst.evoto;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.dit.upm.dit.isst.evoto.dao.CRVDAO;
import es.dit.upm.dit.isst.evoto.dao.CRVDAOImpl;

@SuppressWarnings("serial")
public class CRVServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		// Mostrar página
		RequestDispatcher view = req.getRequestDispatcher("crv.jsp");
        view.forward(req, resp);
	}
}
