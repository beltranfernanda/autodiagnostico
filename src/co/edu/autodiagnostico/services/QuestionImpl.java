package co.edu.autodiagnostico.services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import co.edu.autodiagnostico.model.Field;
import co.edu.autodiagnostico.model.Questions;
import co.edu.autodiagnostico.persistence.ConnectionDB;

@Path("/questionservice")
@Produces({MediaType.APPLICATION_JSON })

public class QuestionImpl implements QuestionApi{
	UtilApi utilObject =  new UtilImpl();
	Connection questionsConn = ConnectionDB.getConn();
	Statement sentencia;
	
	@GET
	@Path("/getquestion")
	@Override
	public Response getQuestions() {
		JSONObject jsonResponse = new JSONObject();
		JSONArray questions = new JSONArray();
		Questions questionObject = new Questions();
		try {
			sentencia = questionsConn.createStatement();
			String sql = "SELECT * FROM preguntas";
			ResultSet rs= sentencia.executeQuery(sql);
			while(rs.next()) {
				JSONObject questionjson = new JSONObject();
				questionObject.setIdQuestion(rs.getInt("idpreguntas"));
				questionObject.setQuestion(rs.getString("pregunta"));
				questionObject.setField(new Field(rs.getInt("idespecialidad")));
				questionjson.put("idpreguntas", questionObject.getIdQuestion());
				questionjson.put("pregunta", questionObject.getQuestion());
				questionjson.put("idespecialidad", questionObject.getField().getIdField());
				questions.put(questionjson);
			}
			jsonResponse = utilObject.createResponse(200);
			jsonResponse.put("data",questions);
		} catch (SQLException e) {
			System.out.println("Error consulting data base: "+e.getCause());
			jsonResponse = utilObject.createResponse(500);
			return Response.ok(jsonResponse.toString()).build();
		}		
		return  Response.ok(jsonResponse.toString()).build();
	}
	
	
	@GET
	@Path("/getquestion/{id}")
	@Override
	public Response getQuestions(@PathParam("id") int id) {
		JSONObject jsonResponse = new JSONObject();
		JSONArray questions = new JSONArray();
		Questions questionObject = new Questions();
		try {
			String sql = "SELECT * FROM preguntas WHERE idespecialidad= ?";
			PreparedStatement statement = questionsConn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet rs= statement.executeQuery();
			while(rs.next()) {
				JSONObject questionjson = new JSONObject();
				questionObject.setIdQuestion(rs.getInt("idpreguntas"));
				questionObject.setQuestion(rs.getString("pregunta"));
				questionObject.setField(new Field(rs.getInt("idespecialidad")));
				questionjson.put("idpreguntas", questionObject.getIdQuestion());
				questionjson.put("pregunta", questionObject.getQuestion());
				questionjson.put("idespecialidad", questionObject.getField().getIdField());
				questions.put(questionjson);
			}
			jsonResponse = utilObject.createResponse(200);
			jsonResponse.put("data",questions);
		} catch (SQLException e) {
			System.out.println("Error consulting data base: "+e.getCause());
			jsonResponse = utilObject.createResponse(500);
			return Response.ok(jsonResponse.toString()).build();
		}		
		return  Response.ok(jsonResponse.toString()).build();
	}

	@POST
	@Path("/addquestion")
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public Response addQuestion(Questions question) {
		JSONObject response = new JSONObject();
		try {
			if(!utilObject.ifExist("pregunta",question.getQuestion(), "preguntas")){
				String sql = "INSERT INTO preguntas (idespecialidad,pregunta)" + 
						     " VALUES (?,?)";
				PreparedStatement addStatement = questionsConn.prepareStatement(sql);
				addStatement.setInt(1 , question.getField().getIdField());
				addStatement.setString(2, question.getQuestion());
				addStatement.executeUpdate();
				response = utilObject.createResponse(200);
				response.put("mensaje", "Se agrego la pregunta correctamente");
			}else {
				response = utilObject.createResponse(400);
				response.put("mensaje", "La pregunta ya existe");
			}
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
	

}
