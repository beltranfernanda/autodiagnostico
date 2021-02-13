package co.edu.autodiagnostico.services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import co.edu.autodiagnostico.model.Questions;
import co.edu.autodiagnostico.model.ResponsePost;
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
		
		return null;
	}

	@POST
	@Path("/addquestion")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public ResponsePost addQuestion(Questions question) {
		ResponsePost response = new ResponsePost();
		try {
			if(!utilObject.ifExist("pregunta",question.getQuestion(), "preguntas")){
				String sql = "INSERT INTO preguntas (idespecialidad,pregunta)" + 
						     " VALUES (?,?)";
				PreparedStatement addStatement = questionsConn.prepareStatement(sql);
				addStatement.setInt(1 , question.getField().getIdField());
				addStatement.setString(2, question.getQuestion());
				addStatement.executeUpdate();
				response.setMessage("Se agrego la pregunta correctamente");
				response.setCode(200);
			}else {
				response.setMessage("La pregunta ya existe");
				response.setCode(400);
			}
		} catch (SQLException e) {
			response.setMessage("Ocurrio un error en la consulta");
			response.setCode(500);
			System.out.println("Ocurrio un error al consultar la DB: "+e.getCause());
			e.printStackTrace();
		}		
		return response;
	}
	

}
