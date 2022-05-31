package br.com.unicid.model;

public class CampusModel {
	
	private Integer idCampus;
	private String nomeCampus;
	
	public Integer getIdCampus() {
		return idCampus;
	}
	public void setIdCampus(Integer idCampus) {
		this.idCampus = idCampus;
	}
	public String getNomeCampus() {
		return nomeCampus;
	}
	public void setNomeCampus(String nomeCampus) {
		this.nomeCampus = nomeCampus;
	}
	@Override
	public String toString() {
		return  nomeCampus;
	}
	
	
}
