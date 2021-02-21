package co.edu.autodiagnostico.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import co.edu.autodiagnostico.model.AnswersOptions;
import co.edu.autodiagnostico.persistence.ConnectionDB;

@Path("/answerservice")
@Produces({MediaType.APPLICATION_JSON })

public class AnswersOptionsImpl implements AnswersOptionsApi{
	UtilApi utilObject =  new UtilImpl();
	Connection connection = ConnectionDB.getConn();
	Statement sentencia;

	@POST
	@Path("/addanswer")
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public Response addAnswer(AnswersOptions answeroption) {
		JSONObject response = new JSONObject();
		try {
			String sql = "INSERT INTO opcionesrespuesta (opciondescripcion,idpreguntaor)" + 
					     " VALUES (?,?)";
			PreparedStatement addStatement = connection.prepareStatement(sql);
			addStatement.setString(1 , answeroption.getOptionDescription());
			addStatement.setInt(2, answeroption.getIdQuestion().getIdQuestion());
			addStatement.executeUpdate();
			response = utilObject.createResponse(200);
			response.put("mensaje", "Se agrego la respuesta correctamente");
		} catch (SQLException e) {
			response = utilObject.createResponse(500);
			response.put("mensaje", "Ocurrio un error al realizar la consulta");
			System.out.println("Ocurrio un error al consultar la DB: "+e.getCause());
			e.printStackTrace();
		}		
		return Response
				.ok(response.toString())
				.header("Access-Control-Allow-Origin","*")
				.build();
	}

	@Override
	public JSONArray getAnswers(int idQuestion) {
		JSONArray answersArray = new JSONArray();
		AnswersOptions answersOptionsOB = new AnswersOptions();
		try {
			String sql = "SELECT * FROM opcionesrespuesta where idpreguntaor = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1 , idQuestion);
			ResultSet rs= statement.executeQuery();
			while(rs.next()) {
				JSONObject answers = new JSONObject();
				answersOptionsOB.setIdOptions(rs.getInt("idopcionesrespuesta"));
				answersOptionsOB.setOptionDescription(rs.getString("opciondescripcion"));
				answers.put("idOption", answersOptionsOB.getIdOptions());
				answers.put("optionDesc", answersOptionsOB.getOptionDescription());
				answersArray.put(answers);		
			}
		} catch (SQLException e) {
			System.out.println("Ocurrio un error al consultar la DB: "+e.getCause());
			e.printStackTrace();
		}
		return answersArray;
	}

}
