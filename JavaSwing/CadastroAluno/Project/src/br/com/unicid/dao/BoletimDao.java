package br.com.unicid.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.unicid.model.AlunoModel;
import br.com.unicid.model.BoletimModel;
import br.com.unicid.model.CampusModel;
import br.com.unicid.model.CursoModel;
import br.com.unicid.util.ConnectionFactory;

public class BoletimDao {

	private Connection connection;

	public BoletimDao() {
		try {
			connection = new ConnectionFactory().estabeleceConexao();
			
		} catch (Exception e) {
		}
		
	}

	
	
	public BoletimModel listaNotasFaltas(int idAluno, int idMateriaXCurso) {
		BoletimModel boletim = null;
		try {
			String sql = "select b.notas, b.faltas from boletim b\r\n"
					+ "WHERE idAluno = ? and idMateriaXCurso = ?\r\n"
					+ ";";
			
			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.setInt(1, idAluno);
				pstm.setInt(2, idMateriaXCurso);
				pstm.execute();
				
				try (ResultSet rst = pstm.getResultSet()) {
					while (rst.next()) {
						boletim = new BoletimModel();
						boletim.setNotas(rst.getDouble(1));
						boletim.setFaltas(rst.getInt(2));
					}
				}
			}
			return boletim;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	
	public void salvar(BoletimModel boletim)  {	
		try {
			String sql = "INSERT INTO boletim(notas, faltas, idAluno, idMateriaXCurso) "
					+ "VALUES (?,?,?,?)";
			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				
				pstm.setDouble(1, boletim.getNotas());
				pstm.setInt(2, boletim.getFaltas());
				pstm.setInt(3, boletim.getAluno().getIdAluno());
				pstm.setInt(4, boletim.getCursoxmateria().getIdMateriaXCurso());
				
				pstm.execute();
			}
			
		} catch (SQLException e) {
//			.throw new RuntimeException(e);
			e.printStackTrace();
		}
	}
	
	public void deleteAlunoByMateria(int idAluno, int idMateriaXCurso)  {	
		try {
			String sql = "DELETE FROM boletim WHERE idAluno=? AND idMateriaXCurso=?";
			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				pstm.setInt(1, idAluno);
				pstm.setInt(2, idMateriaXCurso);
				pstm.executeUpdate();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void delete(int idAluno)  {	
		try {
			String sql = "DELETE FROM boletim WHERE idAluno=?";
			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				pstm.setInt(1, idAluno);
				pstm.executeUpdate();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}



	public void update(BoletimModel model) {
		String sql = "UPDATE boletim SET notas=?, faltas=? WHERE idAluno=? AND idMateriaXCurso=?";
		try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			
			pstm.setDouble(1, model.getNotas());
			pstm.setInt(2, model.getFaltas());
			pstm.setInt(3, model.getAluno().getIdAluno());
			pstm.setInt(4, model.getCursoxmateria().getIdMateriaXCurso());
			
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}