package es.upm.dit.isst.evoto;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.dit.upm.dit.isst.evoto.dao.CRVDAO;
import es.dit.upm.dit.isst.evoto.dao.CRVDAOImpl;
import es.upm.dit.isst.evoto.model.CRV;

@SuppressWarnings("serial")
public class SimulacionServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");

		CRVDAO dao1 = CRVDAOImpl.getInstance();

		for (CRV res : dao1.todosCRV()) {
			dao1.deleteCRV(res.getCodigo());
		}		
		
		dao1.crearCRVn((long) 20, "Pío García-Escudero Márquez", "madrid", "pp", 1, 1169967);
		dao1.crearCRVn((long) 21, "María Rosa Vindel López", "madrid", "pp", 1, 1141757);
		dao1.crearCRVn((long) 22, "Carlos Aragonés Mendiguchía", "madrid", "pp", 1, 1116463);
		dao1.crearCRVn((long) 23, "María Carlota Merchán Mesón", "madrid", "psoe", 2, 644617);
		dao1.crearCRVn((long) 24, "Doménec Miquel Ruiz Devesa", "madrid", "psoe", 2, 577911);
		dao1.crearCRVn((long) 25, "Lydia Martínez Mora", "madrid", "psoe", 2, 572275);
		dao1.crearCRVn((long) 26, "Ines Cortijo Castilla", "madrid", "podemos", 4, 718701);
		dao1.crearCRVn((long) 27, "Rosa Arauzo Quintero", "madrid", "podemos", 4, 645132);
		dao1.crearCRVn((long) 28, "German Cano Cuenca", "madrid", "podemos", 4, 603755);
		dao1.crearCRVn((long) 29, "Antonio José Pezzi Acosta", "madrid", "ciudadanos", 3, 524397);
		dao1.crearCRVn((long) 30, "Carlos Rodriguez Alemany", "madrid", "ciudadanos", 3, 465193);
		dao1.crearCRVn((long) 31, "Eva Sagardoy Cayetano", "madrid", "ciudadanos", 3, 454762);
		dao1.crearCRVn((long) 32, "Eva Abril Chaigne", "madrid", "iu", 7, 225405);
		dao1.crearCRVn((long) 33, "Jaldia Abuabarka Aueda", "madrid", "iu", 7, 151757);
		dao1.crearCRVn((long) 34, "Alberto Manuel Arregui Álava", "madrid", "iu", 7, 141817);
		dao1.crearCRVn((long) 35, "Fernando Savater", "madrid", "upyd",6, 118683);
		dao1.crearCRVn((long) 36, "Andrés Trapiello", "madrid", "upyd", 6, 62647);
		dao1.crearCRVn((long) 37, "Magdalena Oliva Díaz", "madrid", "upyd", 6, 51043);
		dao1.crearCRVn((long) 38, "Laura Duarte Domínguez", "madrid", "pacma", 5, 76327);
		dao1.crearCRVn((long) 39, "Luis Víctor Moreno Barbieri", "madrid", "pacma", 5, 46650);
		dao1.crearCRVn((long) 40, "Francisco García Leal", "madrid", "pacma", 5, 41491);
		dao1.crearCRVn((long) 41, "Francisco Javier Ortega Smith-Molina", "madrid", "vox", 8, 34216);
		dao1.crearCRVn((long) 42, "Ivan Espinosa de los Monteros de Simon", "madrid", "vox", 8, 26870);
		dao1.crearCRVn((long) 43, "Carmen Lomana", "madrid", "vox", 8, 23233);
		
		for (CRV res : dao1.todosCRV()) {
			resp .getWriter().println( res );
		}
	}
}
