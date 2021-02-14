package co.edu.autodiagnostico.services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import co.edu.autodiagnostico.model.Answers;
import co.edu.autodiagnostico.persistence.ConnectionDB;

@Path("/answersservice")
@Produces({MediaType.APPLICATION_JSON })

public class AnswersImpl implements AnswersApi{
	UtilApi utilObject =  new UtilImpl();
	Connection connection = ConnectionDB.getConn();
	
	@POST
	@Path("/addexam")
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public Response addExam(Answers[] answers) {
		JSONObject response = new JSONObject();
		JSONArray answersresult = new JSONArray();
		for(Answers ans : answers) {
			JSONObject responseDB = addAnswer(ans);
			answersresult.put(responseDB);
		}
		response.put("data", answersresult);
		return Response
				.ok(response.toString())
				.header("Access-Control-Allow-Origin","*")
				.build();
	}
	
	public JSONObject addAnswer(Answers answer) {
		JSONObject response = new JSONObject();
		try {
			if(!ifExistAnswer(answer.getUser().getIdUser(),answer.getQuestion().getIdQuestion())){
				String sql = "INSERT INTO respuestas (idusuario,idpregunta,respuesta)" + 
							" VALUES (?,?,?)";
				PreparedStatement addStatement = connection.prepareStatement(sql);
				addStatement.setInt(1 , answer.getUser().getIdUser());
				addStatement.setInt(2, answer.getQuestion().getIdQuestion());
				addStatement.setString(3, answer.getAnswerDesc());
				addStatement.executeUpdate();
				response = utilObject.createResponse(200);
				response.put("mensaje", "Se agrego la pregunta correctamente");
			}else {
				response = utilObject.createResponse(400);
				response.put("mensaje", "Las respuestas asociadas a dicho usuario ya se encuentran guardadas");
			}
		} catch (SQLException e) {
			response = utilObject.createResponse(500);
			response.put("mensaje", "Ocurrio un error al realizar la operacion");
			System.out.println("Ocurrio un error al consultar la DB: "+e.getCause());
			e.printStackTrace();
		}
		return response;
	}
	
	
	public Boolean ifExistAnswer(int idUser, int idQuestion) {		
		try {
			String sql = "SELECT COUNT(*) AS exist FROM respuestas WHERE  idusuario = ? AND idpregunta = ?";
			PreparedStatement consultExist = connection.prepareStatement(sql);
			consultExist.setInt(1, idUser);
			consultExist.setInt(2, idQuestion);
			ResultSet result = consultExist.executeQuery();
			while(result.next()) {
				System.out.println(result.getString("exist"));
				if(!result.getString("exist").equals("0")) {
					return true;
				}
			}
		} catch (SQLException e) {
			System.out.println("Ocurrio un error al consultar la BD. Error: "+e.getStackTrace());
			e.getMessage();
			e.printStackTrace();
		}
		return false;
	}

}
