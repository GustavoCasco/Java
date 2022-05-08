package br.com.unicid.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.unicid.model.AlunoModel;
import br.com.unicid.model.CampusModel;
import br.com.unicid.model.CursoModel;
import br.com.unicid.util.ConnectionFactory;

public class AlunoDao {

	private Connection connection;

	public AlunoDao() {
		try {
			connection = new ConnectionFactory().estabeleceConexao();
			
		} catch (Exception e) {
		}
		
	}

	public List<AlunoModel> listar(){
		try {
			List<AlunoModel> alunoList = new ArrayList<>();
			String sql = "SELECT a from aluno a";
			
			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();
				
				try (ResultSet rst = pstm.getResultSet()) {
					while (rst.next()) {
						AlunoModel aluno = new AlunoModel();
						aluno.setIdAluno(rst.getInt(1));
						aluno.setNome(rst.getString(2));
						aluno.setRgm(rst.getInt(3));
						aluno.setDataNascimento(rst.getString(4));
						aluno.setCpf(rst.getString(5));
						aluno.setEmail(rst.getString(6));
						aluno.setCelular(rst.getString(7));
						aluno.setCep(rst.getString(8));
						aluno.setCelular(rst.getString(9));
						aluno.setLogradouro(rst.getString(10));
						aluno.setMunicipio(rst.getString(11));
						aluno.setEstado(rst.getString(12));
						
						alunoList.add(aluno);
					}
				}
			}
			return alunoList;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	public void salvar(AlunoModel aluno)  {	
		try {
			String sql = "INSERT INTO aluno(nome, rgm, dataNascimento, cpf, email, celular, cep, logradouro, municipio, estado, periodo, idCurso) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				
				pstm.setString(1, aluno.getNome());
				pstm.setInt(2, aluno.getRgm());
				pstm.setString(3, aluno.getDataNascimento());
				pstm.setString(4, aluno.getCpf());
				pstm.setString(5, aluno.getEmail());
				pstm.setString(6, aluno.getCelular());
				pstm.setString(7, aluno.getCep());
				pstm.setString(8, aluno.getLogradouro());
				pstm.setString(9, aluno.getMunicipio());
				pstm.setString(10, aluno.getEstado());
				pstm.setString(11, aluno.getPeriodo());
				pstm.setInt(12, aluno.getCurso().getIdCurso());
				
				pstm.execute();
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public AlunoModel getByRgm(int rgm){
		AlunoModel aluno = null;
		try {
			String sql = "SELECT * from aluno a \r\n"
					+ "INNER JOIN curso c ON a.idCurso = c.idCurso\r\n"
					+ "INNER JOIN campus cam ON c.idCampus = cam.idCampus\r\n"
					+ "WHERE a.rgm = ?;";
			
			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.setInt(1, rgm);
				pstm.execute();
				
				try (ResultSet rst = pstm.getResultSet()) {
					while (rst.next()) {
						aluno = new AlunoModel();
						aluno.setIdAluno(rst.getInt(1));
						aluno.setNome(rst.getString(2));
						aluno.setRgm(rst.getInt(3));
						aluno.setDataNascimento(rst.getString(4));
						aluno.setCpf(rst.getString(5));
						aluno.setEmail(rst.getString(6));
						aluno.setCelular(rst.getString(7));
						aluno.setCep(rst.getString(8));
						aluno.setLogradouro(rst.getString(9));
						aluno.setMunicipio(rst.getString(10));
						aluno.setEstado(rst.getString(11));
						aluno.setPeriodo(rst.getString(12));
						
						CursoModel curso = new CursoModel();
						curso.setIdCurso(rst.getInt(13));
						curso.setCurso(rst.getString(15));
						CampusModel campus = new CampusModel();
						campus.setIdCampus(rst.getInt(17));
						campus.setNomeCampus(rst.getString(18));
						curso.setCampus(campus);
						aluno.setCurso(curso);
					}
				}
			}
			return aluno;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public void delete(int rgm)  {	
		try {
			String sql = "DELETE FROM aluno WHERE rgm=?";
			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				pstm.setInt(1, rgm);
				pstm.executeUpdate();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public void update(AlunoModel aluno)  {	
		try {
			String sql = "UPDATE aluno SET nome=?, dataNascimento=?, cpf=?, email=?, celular=?, cep=?, logradouro=?, municipio=?, estado=?, periodo=?, idCurso=?"
					+ " WHERE rgm=?";
			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				
				pstm.setString(1, aluno.getNome());
				pstm.setString(2, aluno.getDataNascimento());
				pstm.setString(3, aluno.getCpf());
				pstm.setString(4, aluno.getEmail());
				pstm.setString(5, aluno.getCelular());
				pstm.setString(6, aluno.getCep());
				pstm.setString(7, aluno.getLogradouro());
				pstm.setString(8, aluno.getMunicipio());
				pstm.setString(9, aluno.getEstado());
				pstm.setString(10, aluno.getPeriodo());
				pstm.setInt(11, aluno.getCurso().getIdCurso());
				pstm.setInt(12, aluno.getRgm());
				
				pstm.executeUpdate();
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
