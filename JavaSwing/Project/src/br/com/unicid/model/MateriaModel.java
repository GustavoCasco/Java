package br.com.unicid.model;

public class MateriaModel {
	
	private Integer idMateria;
	private String nomeMateria;
	private SemestreModel semestre;
	
	public Integer getIdMateria() {
		return idMateria;
	}
	public void setIdMateria(Integer idMateria) {
		this.idMateria = idMateria;
	}
	public String getNomeMateria() {
		return nomeMateria;
	}
	public void setNomeMateria(String nomeMateria) {
		this.nomeMateria = nomeMateria;
	}
	public SemestreModel getSemestre() {
		return semestre;
	}
	public void setSemestre(SemestreModel semestre) {
		this.semestre = semestre;
	}
}
