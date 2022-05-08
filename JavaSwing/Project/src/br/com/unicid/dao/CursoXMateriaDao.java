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
import br.com.unicid.model.CursoXMateriaModel;
import br.com.unicid.model.MateriaModel;
import br.com.unicid.model.SemestreModel;
import br.com.unicid.util.ConnectionFactory;

public class CursoXMateriaDao {

	private Connection connection;

	public CursoXMateriaDao() {
		try {
			connection = new ConnectionFactory().estabeleceConexao();
			
		} catch (Exception e) {
		}
		
	}

	public List<CursoXMateriaModel> listarCursoeMaterias(String cursoFilter){
		try {
			List<CursoXMateriaModel> campusList = new ArrayList<>();
			String sql = "SELECT * from materiaXcurso mc\r\n"
					+ "INNER JOIN materia ma ON mc.idMateria = ma.idMateria\r\n"
					+ "INNER JOIN semestre se ON se.idSemestre = ma.idSemestre\r\n"
					+ "INNER JOIN curso c ON mc.idCurso = c.idCurso\r\n"
					+ "INNER JOIN campus cam ON c.idCampus = cam.idCampus\r\n"
					+ "WHERE c.curso = ?";
			
			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.setString(1, cursoFilter);
				pstm.execute();
				
				try (ResultSet rst = pstm.getResultSet()) {
					while (rst.next()) {
						CursoXMateriaModel cursoMateria = new CursoXMateriaModel();
						cursoMateria.setIdMateriaXCurso(rst.getInt(1));
						MateriaModel materia = new MateriaModel();
						materia.setIdMateria(rst.getInt(4));
						materia.setNomeMateria(rst.getString(5));
						SemestreModel semestre = new SemestreModel();
						semestre.setIdSemestre(rst.getInt(7));
						semestre.setSemestre(rst.getString(8));
						materia.setSemestre(semestre);
						CursoModel cursoModel = new CursoModel();
						cursoModel.setIdCurso(rst.getInt(9));
						cursoModel.setCurso(rst.getString(10));
						CampusModel campusModel = new CampusModel();
						campusModel.setIdCampus(rst.getInt(12));
						campusModel.setNomeCampus(rst.getString(13));
						cursoModel.setCampus(campusModel);
						cursoMateria.setCurso(cursoModel);
						cursoMateria.setMateria(materia);
						
						
						campusList.add(cursoMateria);
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
