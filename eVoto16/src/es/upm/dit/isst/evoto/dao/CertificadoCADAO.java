package es.upm.dit.isst.evoto.dao;

import java.util.List;

import es.upm.dit.isst.evoto.model.CertificadoCA;


public interface CertificadoCADAO {
	public void crearCertificadoCA();
	
	public CertificadoCA leerCertificadoCA();
	
	public List<CertificadoCA> todos();
	
	public boolean resetCertificadoCA();
}
