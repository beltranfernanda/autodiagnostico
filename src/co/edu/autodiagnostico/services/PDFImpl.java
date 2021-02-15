package co.edu.autodiagnostico.services;

import java.io.ByteArrayOutputStream;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFImpl implements PDFApi {

	@Override
	public ByteArrayOutputStream getPDF(String name, String especialidad, String numeroDoc) {
		Document document = new Document();
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			PdfWriter.getInstance(document, byteArrayOutputStream);
			document.open();
			Font fontRegular = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, BaseColor.BLACK);
			Font fontBold = FontFactory.getFont(FontFactory.TIMES_BOLD, 16, BaseColor.BLACK);
			Font fontBoldp = FontFactory.getFont(FontFactory.TIMES_BOLD, 12, BaseColor.BLACK);
			
			PdfPTable table = new PdfPTable(2);
			table.setSpacingBefore(30f); //Space before table
			table.setSpacingAfter(30f); //Space after table
			//Set Column widths
			float[] columnWidths = {1f, 1f};
			table.setWidths(columnWidths);
			//Set cells
			table.addCell(new PdfPCell(new Phrase("Número de documento",fontBoldp)));
			table.addCell(new PdfPCell(new Phrase(numeroDoc,fontRegular)));
			table.addCell(new PdfPCell(new Phrase("Nombre usuario",fontBoldp)));
			table.addCell(new PdfPCell(new Phrase(name,fontRegular)));
			table.addCell(new PdfPCell(new Phrase("Especialidad",fontBoldp)));
			table.addCell(new PdfPCell(new Phrase(especialidad,fontRegular)));
			document.addTitle("Certificado de salud ocupacional");
			Paragraph title = new Paragraph("Certificado de salud ocupacional",fontBold);
			title.setAlignment(Paragraph.ALIGN_CENTER);
			title.setSpacingAfter(30f);
			document.add(title);
			document.add(new Paragraph("Se certifica que "+ name + " identificado(a) con documento "
					+ numeroDoc +" se encuentra apto para desarrollar la actividad para la que ha sido "
							+ "contratado. El certificado es expedido con la siguiente información:",fontRegular));
			document.add(table);
			document.close(); 
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return byteArrayOutputStream;
	}

}
