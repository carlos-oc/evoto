package es.upm.dit.isst.evoto.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CRV implements Serializable {
	
	private static final long serialVersionUID = 01L;
	
	@Id
	@Column
	private Long codigo;
	@Column
	private String nombreCompleto;
	@Column
	private String provincia;
	@Column
	private String partido;
	@Column
	private int codPartido;
	@Column
	private int nVotos;
	
	//Constructores
	// Con nVotos
	public CRV(Long codigo, String nombreCompleto, String provincia, String partido, int codPartido, int nVotos){
		this.codigo = codigo;
		this.nombreCompleto = nombreCompleto;
		this.provincia = provincia;
		this.partido = partido;
		this.codPartido = codPartido;
		this.nVotos = nVotos;
	}
	//Sin nVotos
	public CRV(Long codigo, String nombreCompleto, String provincia, String partido, int codPartido){
		this.codigo = codigo;
		this.nombreCompleto = nombreCompleto;
		this.provincia = provincia;
		this.partido = partido;
		this.codPartido = codPartido;
		this.nVotos = 0;
	}
	
	
	// Getters y Setters
	public Long getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	
	public String getProvincia() {
		return provincia;
	}
	
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	
	public String getPartido() {
		return partido;
	}
	
	public void setPartido(String partido) {
		this.partido = partido;
	}
	
	public int getCodPartido() {
		return codPartido;
	}
	
	public void setCodPartido(int codPartido) {
		this.codPartido = codPartido;
	}
	
	public int getnVotos() {
		return nVotos;
	}
	
	public void setnVotos(int nVotos) {
		this.nVotos = nVotos;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	// Equals

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CRV other = (CRV) obj;
		if (codPartido != other.codPartido)
			return false;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (nVotos != other.nVotos)
			return false;
		if (nombreCompleto == null) {
			if (other.nombreCompleto != null)
				return false;
		} else if (!nombreCompleto.equals(other.nombreCompleto))
			return false;
		if (partido == null) {
			if (other.partido != null)
				return false;
		} else if (!partido.equals(other.partido))
			return false;
		if (provincia == null) {
			if (other.provincia != null)
				return false;
		} else if (!provincia.equals(other.provincia))
			return false;
		return true;
	}

	// To string
	@Override
	public String toString() {
		return "CRV [codigo=" + codigo + ", nombreCompleto=" + nombreCompleto + ", provincia=" + provincia
				+ ", partido=" + partido + ", codPartido=" + codPartido + ", nVotos=" + nVotos + "]";
	}

}
