package br.com.unicid.model;

public class BoletimModel {
	
	private Integer idBoletim;
	private Double notas;
	private Integer faltas;
	private AlunoModel aluno;
	private CursoXMateriaModel cursoxmateria;
	
	public Integer getIdBoletim() {
		return idBoletim;
	}
	public void setIdBoletim(Integer idBoletim) {
		this.idBoletim = idBoletim;
	}
	public Double getNotas() {
		return notas;
	}
	public void setNotas(Double notas) {
		this.notas = notas;
	}
	public Integer getFaltas() {
		return faltas;
	}
	public void setFaltas(Integer faltas) {
		this.faltas = faltas;
	}
	public AlunoModel getAluno() {
		return aluno;
	}
	public void setAluno(AlunoModel aluno) {
		this.aluno = aluno;
	}
	public CursoXMateriaModel getCursoxmateria() {
		return cursoxmateria;
	}
	public void setCursoxmateria(CursoXMateriaModel cursoxmateria) {
		this.cursoxmateria = cursoxmateria;
	}
}
