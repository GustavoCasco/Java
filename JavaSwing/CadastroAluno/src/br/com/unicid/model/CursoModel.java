package br.com.unicid.model;

public class CursoModel {
	
	private Integer idCurso;
	private String curso;
	private String periodo;
	private CampusModel campus;
	
	
	@Override
	public String toString() {
		return  curso;
	}



	public Integer getIdCurso() {
		return idCurso;
	}



	public void setIdCurso(Integer idCurso) {
		this.idCurso = idCurso;
	}



	public String getCurso() {
		return curso;
	}



	public void setCurso(String curso) {
		this.curso = curso;
	}



	public String getPeriodo() {
		return periodo;
	}



	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}



	public CampusModel getCampus() {
		return campus;
	}



	public void setCampus(CampusModel campus) {
		this.campus = campus;
	}
}