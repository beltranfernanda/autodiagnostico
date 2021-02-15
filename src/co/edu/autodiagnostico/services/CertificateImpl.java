package co.edu.autodiagnostico.services;
import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import co.edu.autodiagnostico.model.User;
import co.edu.autodiagnostico.model.UserType;
import co.edu.autodiagnostico.persistence.ConnectionDB;
import co.edu.autodiagnostico.model.Document;
import co.edu.autodiagnostico.model.Field;

@Path("/certificateservice")

public class CertificateImpl implements CertificateApi{
	UtilApi utilObject = new UtilImpl();
	Connection objectConn = ConnectionDB.getConn();
	Statement sentencia;

	@GET
	@Path("/getPdf/{documentNumber}/{field}")
	@Produces("application/pdf")  
	@Override
	public Response generateCertificate(@PathParam("documentNumber") String documentNumber, @PathParam("field") int field) {
		User userOb = getUser(documentNumber);
		Field fieldOB = getField(field);
        PDFApi pdfApi = new PDFImpl();
        ByteArrayOutputStream byteArrayOutputStream = pdfApi.getPDF(userOb.getName(),
        						fieldOB.getNameField(), userOb.getDocumentNumber() );
		 return Response.ok(byteArrayOutputStream.toByteArray(), MediaType.APPLICATION_OCTET_STREAM)
		        .header("content-disposition", "attachment; filename = "+userOb.getDocumentNumber()+".pdf")
		        .build();
	}
	
	public User getUser(String documentNumber) {
		User userOb = new User();
		try {
			String sql = "SELECT * FROM user WHERE numerodoc = ?";
			PreparedStatement statement = objectConn.prepareStatement(sql);
			statement.setString(1, documentNumber);
			ResultSet rs= statement.executeQuery();
			while(rs.next()) {
				userOb.setIdUser(rs.getInt("iduser"));
				userOb.setName(rs.getString("nombre"));
				userOb.setDocumentNumber(rs.getString("numerodoc"));
				userOb.setDocument(new Document(rs.getInt("idtipodoc")));
				userOb.setUserType(new UserType(rs.getInt("idtipousuario")));
			}
		} catch (SQLException e) {
			System.out.println("Error consulting data base: "+e.getCause());
		}		
		return userOb;
	}
	
	public Field getField(int field) {
		Field fieldOB = new Field();
		try {
			String sql = "SELECT * FROM especialidad WHERE idEspecialidad = ?";
			PreparedStatement statement = objectConn.prepareStatement(sql);
			statement.setInt(1, field);
			ResultSet rs= statement.executeQuery();
			while(rs.next()) {
				fieldOB.setIdField(rs.getInt("idEspecialidad"));
				fieldOB.setNameField(rs.getString("nombreEspecialidad"));
			}
		} catch (SQLException e) {
			System.out.println("Error consulting data base: "+e.getCause());
		}		
		return fieldOB;
	}
		

}
