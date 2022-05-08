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

public class CampusDao {

	private Connection connection;

	public CampusDao() {
		try {
			connection = new ConnectionFactory().estabeleceConexao();
			
		} catch (Exception e) {
		}
		
	}

	public List<CampusModel> listarCurso(){
		try {
			List<CampusModel> campusList = new ArrayList<>();
			String sql = "SELECT * from campus c";
			
			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();
				
				try (ResultSet rst = pstm.getResultSet()) {
					while (rst.next()) {
						CampusModel campus = new CampusModel();
						
						campus.setIdCampus(rst.getInt(1));
						campus.setNomeCampus(rst.getString(2));
						
						campusList.add(campus);
					}
				}
			}
			return campusList;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	public void salvar(CampusModel campus)  {	
		try {
			String sql = "INSERT INTO campus(nomeCampus) values (?)";
			
			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				
				pstm.setString(1, campus.getNomeCampus());

				
				pstm.execute();
				
				try (ResultSet rst = pstm.getGeneratedKeys()) {
					while (rst.next()) {
						campus.setIdCampus(rst.getInt(1));
					}
				}
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
