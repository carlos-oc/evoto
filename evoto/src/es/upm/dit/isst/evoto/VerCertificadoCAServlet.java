package es.upm.dit.isst.evoto;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.evoto.dao.CertificadoCADAO;
import es.upm.dit.isst.evoto.dao.CertificadoCADAOImpl;
import es.upm.dit.isst.evoto.model.CertificadoCA;

@SuppressWarnings("serial")
public class VerCertificadoCAServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		resp.setContentType("text/plain");
		CertificadoCADAO dao = CertificadoCADAOImpl.getInstance();
		CertificadoCA cert = dao.leerCertificadoCA();
		try {
			resp.getWriter().println(cert.getCertificado());
		} catch (ClassNotFoundException e) {
			resp.getWriter().println("Error al mostrar el certificado");
			e.printStackTrace();
		}

	}
}