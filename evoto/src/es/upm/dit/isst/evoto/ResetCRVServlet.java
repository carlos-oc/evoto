package es.upm.dit.isst.evoto;

import java.io.IOException;
import javax.servlet.http.*;

import es.dit.upm.dit.isst.evoto.dao.CADAO;
import es.dit.upm.dit.isst.evoto.dao.CADAOImpl;
import es.dit.upm.dit.isst.evoto.dao.CRVDAO;
import es.dit.upm.dit.isst.evoto.dao.CRVDAOImpl;
import es.upm.dit.isst.evoto.model.CA;
import es.upm.dit.isst.evoto.model.CRV;

@SuppressWarnings("serial")
public class ResetCRVServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		
		//CADAO dao = CADAOImpl.getInstance();
		// CA nuevoCA = new CA("000000A", "Fulanito", "de tal", "aaaaa2", "aaaaaa@prueba.es", "MADRID");
		// CA nuevoCA2 = new CA("66666666A", "Prueba3", "bbbb1", "bbbb2", "bbbbbbb@prueba.es", "MADRID");
		// dao.deleteCA("66666666A");
		// List<CA> res = dao.todosCA();
		// CA dato =dao.leerCA("000000A");
		// resp.getWriter().println(dato);
		
		CRVDAO dao1 = CRVDAOImpl.getInstance();
		
		for (CRV res : dao1.todosCRV()) {
			dao1.deleteCRV(res.getCodigo());
		}	
		
		dao1.crearCRV((long) 20, "P�o Garc�a-Escudero M�rquez", "madrid", "pp", 1);
		dao1.crearCRV((long) 21, "Mar�a Rosa Vindel L�pez", "madrid", "pp", 1);
		dao1.crearCRV((long) 22, "Carlos Aragon�s Mendiguch�a", "madrid", "pp", 1);
		dao1.crearCRV((long) 23, "Mar�a Carlota Merch�n Mes�n", "madrid", "psoe", 2);
		dao1.crearCRV((long) 24, "Dom�nec Miquel Ruiz Devesa", "madrid", "psoe", 2);
		dao1.crearCRV((long) 25, "Lydia Mart�nez Mora", "madrid", "psoe", 2);
		dao1.crearCRV((long) 26, "Ines Cortijo Castilla", "madrid", "podemos", 4);
		dao1.crearCRV((long) 27, "Rosa Arauzo Quintero", "madrid", "podemos", 4);
		dao1.crearCRV((long) 28, "German Cano Cuenca", "madrid", "podemos", 4);
		dao1.crearCRV((long) 29, "Antonio Jos� Pezzi Acosta", "madrid", "ciudadanos", 3);
		dao1.crearCRV((long) 30, "Carlos Rodriguez Alemany", "madrid", "ciudadanos", 3);
		dao1.crearCRV((long) 31, "Eva Sagardoy Cayetano", "madrid", "ciudadanos", 3);
		dao1.crearCRV((long) 32, "Eva Abril Chaigne", "madrid", "iu", 7);
		dao1.crearCRV((long) 33, "Jaldia Abuabarka Aueda", "madrid", "iu", 7);
		dao1.crearCRV((long) 34, "Alberto Manuel Arregui �lava", "madrid", "iu", 7);
		dao1.crearCRV((long) 35, "Fernando Savater", "madrid", "upyd",6);
		dao1.crearCRV((long) 36, "Andr�s Trapiello", "madrid", "upyd", 6);
		dao1.crearCRV((long) 37, "Magdalena Oliva D�az", "madrid", "upyd", 6);
		dao1.crearCRV((long) 38, "Laura Duarte Dom�nguez", "madrid", "pacma", 5);
		dao1.crearCRV((long) 39, "Luis V�ctor Moreno Barbieri", "madrid", "pacma", 5);
		dao1.crearCRV((long) 40, "Francisco Garc�a Leal", "madrid", "pacma", 5);
		dao1.crearCRV((long) 41, "Francisco Javier Ortega Smith-Molina", "madrid", "vox", 8);
		dao1.crearCRV((long) 42, "Ivan Espinosa de los Monteros de Simon", "madrid", "vox", 8);
		dao1.crearCRV((long) 43, "Carmen Lomana", "madrid", "vox", 8);

				for (CRV res : dao1.todosCRV()) {
					resp .getWriter().println( res );
				}

		resp.getWriter().println("Hello, world");
	}
}