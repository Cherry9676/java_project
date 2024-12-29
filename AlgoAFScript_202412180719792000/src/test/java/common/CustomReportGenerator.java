package common;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.DocumentException;
import com.itextpdf.text.PageSize;

import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.xhtmlrenderer.pdf.ITextRenderer;
import java.io.FileOutputStream;
import java.io.IOException;
import io.cucumber.java.*;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CustomReportGenerator {
	private static String path = System.getProperty("user.dir");
	public static Path htmlfile = Paths.get(path, "CustomReport", "report.html");
	public static Path pdffile = Paths.get(path, "CustomReport", "output.pdf");

	private static List<String[]> scenarioResults = new ArrayList<>();

	public static void addScenarioResult(String[] result) {
		scenarioResults.add(result);
	}
	public static List<String[]> getaddScenarioResult() {
		return scenarioResults;
	}

	public static String getTestCaseNumber(Scenario scenario) {
		return scenario.getSourceTagNames().stream().filter(tag -> tag.startsWith("@test")).findFirst()
				.orElse("Unknown").substring(5); // Extract the test case number after "@test"
	}

	public static void generateReport() {
		// Generate HTML report
		try {
			// Ensure the CustomReport folder exists
			File reportDir = new File("CustomReport");
			if (!reportDir.exists()) {
				reportDir.mkdirs();
			}

			FileWriter writer = new FileWriter(htmlfile.toString());

			writer.write("<html><head><style>");
			writer.write("table {width: 100%; border-collapse: collapse;}");
			writer.write("th, td {border: 1px solid black; padding: 8px; text-align: center;}");
			writer.write("th {background-color: #f2f2f2;}");
			writer.write("</style></head><body>");
			writer.write("<table>");
			writer.write(
					"<tr><th>Scenario Name</th><th>Scheme Name</th><th>Application Number</th><th>Output (Pass/Fail)</th></tr>");

			for (String[] row : scenarioResults) {
				writer.write("<tr>");
				for (String cell : row) {
					writer.write("<td>" + cell + "</td>");
				}
				writer.write("</tr>");
			}

			writer.write("</table>");
			writer.write("</body></html>");

			writer.close();
			System.out.println("HTML report generated successfully.");
			// Append the HTML content to the second page of the existing PDF
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getSchemaName(String name) {
		String normalizedName = name.toLowerCase().replace(" ", "");
		if (normalizedName.contains("ss")) {
			return "SS General Form 60";
		} else if (normalizedName.contains("car")) {
			return "Car Loan";
		} else if (normalizedName.contains("home")) {
			return "Home Loan";
		}
		return null;
	}
	
	 public static void convertHtmlToPdf(String htmlFilePath, String pdfFilePath) {
	        try (FileOutputStream outputStream = new FileOutputStream(pdfFilePath)) {
	            // Create a new PDF document with custom page size
	            Document document = new Document(PageSize.A3.rotate()); // Set page size to A3 landscape
	            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
	            document.open();
	            
	            // Create an ITextRenderer object
	            ITextRenderer renderer = new ITextRenderer();
	            
	            // Set the base URI for relative paths in the HTML
	            renderer.setDocument(new java.io.File(htmlFilePath));
	            renderer.layout();
	            
	            // Write the PDF to the output stream
	            try {
					renderer.createPDF(outputStream);
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            document.close();
	            
	            System.out.println("PDF generated successfully at " + pdfFilePath);
	        } catch (IOException e) {
	            System.err.println("IO error: " + e.getMessage());
	            e.printStackTrace();
	        } catch (com.itextpdf.text.DocumentException e) {
	            System.err.println("Document error: " + e.getMessage());
	            e.printStackTrace();
	        }
	    }


}
