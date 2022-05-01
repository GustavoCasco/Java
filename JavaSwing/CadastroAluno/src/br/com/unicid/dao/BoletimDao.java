package br.com.unicid.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.unicid.model.BoletimModel;
import br.com.unicid.util.ConnectionFactory;

public class BoletimDao {

	private Connection connection;

	public BoletimDao() {
		try {
			connection = new ConnectionFactory().estabeleceConexao();
			
		} catch (Exception e) {
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
}