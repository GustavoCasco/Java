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

public class CursoDao {

	private Connection connection;

	public CursoDao() {
		try {
			connection = new ConnectionFactory().estabeleceConexao();
			
		} catch (Exception e) {
		}
		
	}

	public List<CursoModel> listarCurso(){
		try {
			List<CursoModel> cursoList = new ArrayList<>();
			String sql = "SELECT * from curso c";
			
			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();
				
				try (ResultSet rst = pstm.getResultSet()) {
					while (rst.next()) {
						CursoModel curso = new CursoModel();
						
						curso.setCampus(new CampusModel());
						curso.setIdCurso(rst.getInt(1));
						curso.setCurso(rst.getString(2));
						curso.getCampus().setIdCampus(rst.getInt(3));
						cursoList.add(curso);
					}
				}
			}
			return cursoList;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

}
