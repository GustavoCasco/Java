package br.com.unicid.model;

public class CursoXMateriaModel {
	
	private Integer idMateriaXCurso;
	private MateriaModel materia;
	private CursoModel curso;
	
	public Integer getIdMateriaXCurso() {
		return idMateriaXCurso;
	}
	public void setIdMateriaXCurso(Integer idMateriaXCurso) {
		this.idMateriaXCurso = idMateriaXCurso;
	}
	public MateriaModel getMateria() {
		return materia;
	}
	public void setMateria(MateriaModel materia) {
		this.materia = materia;
	}
	public CursoModel getCurso() {
		return curso;
	}
	public void setCurso(CursoModel curso) {
		this.curso = curso;
	}
	@Override
	public String toString() {
		return materia.getNomeMateria();
	}
}
