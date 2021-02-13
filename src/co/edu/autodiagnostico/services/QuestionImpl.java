package co.edu.autodiagnostico.services;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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
	Connection documentsConn = ConnectionDB.getConn();
	Statement sentencia;
	
	@GET
	@Path("/getquestion")
	@Override
	public Response getQuestions() {
		
		return null;
	}

	@POST
	@Path("/addquestion")
	@Override
	public ResponsePost addQuestion(Questions question) {
		try {
			sentencia = documentsConn.createStatement();
			String sql = "INSERT INTO Preguntas (idpreguntas,idespecialidad,pregunta)"
					+ "VALUES ("+question.getIdQuestion()+","+question.getField().getIdField()+
					","+question.getQuestion()+")";
			sentencia.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return null;
	}
	

}
