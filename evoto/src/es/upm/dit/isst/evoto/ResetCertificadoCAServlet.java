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
public class ResetCertificadoCAServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		resp.setContentType("text/plain");
		try {
			CertificadoCADAO dao = CertificadoCADAOImpl.getInstance();
			//Resetea el certificado de la CA
			dao.resetCertificadoCA();
			resp.getWriter().println("chachi");
			// Espera medio segundo porque a veces tarda la base de datos
			Thread.sleep(500);
			// Lee el certificado de la CA de la base de datos y lo muestra
			CertificadoCA cert = dao.leerCertificadoCA();
			resp.getWriter().println(cert.getCertificado());

		} catch (Exception e) {
			System.out.println("FALLO######");
			System.out.println(e);
		}
	}
}
