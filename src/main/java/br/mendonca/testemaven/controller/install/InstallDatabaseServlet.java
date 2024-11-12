package br.mendonca.testemaven.controller.install;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import br.mendonca.testemaven.services.InstallService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/install")
public class InstallDatabaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter page = response.getWriter();
		
		try {
			InstallService service = new InstallService();
			String msg = "<h1>INSTALL DATABASE</h1>";
			
			service.testConnection();
			msg += "<h2>Connection DB sucessful!</h2>\n";
			
			service.deleteUserTable();
			msg += "<h2>Delete table user sucessful!</h2>\n";
			
			service.createUserTable();
			msg += "<h2>Create table user sucessful!</h2>\n";

//			service.createDisciplinaTable();
//			msg += "<h2>Create table disciplina sucessful!</h2>\n";


			service.deleteProfessoresTable();
			msg += "<h2>Delete table professores sucessful!</h2>\n";

			service.createProfessoresTable();
			msg += "<h2>Create table professores sucessful!</h2>\n";

			service.deleteAlunoTable();
			msg += "<h2>Delete table alunos sucessful!</h2>\n";

			service.createAlunoTable();
			msg += "<h2>Create table alunos sucessful!</h2>\n";

            service.deleteCursoTable();
            msg += "<h2>Delete table cursos sucessful!</h2>\n";

            service.createCursoTable();
            msg += "<h2>Create table cursos sucessful!</h2>\n";

			service.populateCursosTable();
			msg += "<h2>Populate table cursos sucessful!</h2>\n";

            page.println("<html lang='pt-br'><head><title>Teste</title></head><body>");
			page.println(msg);
			/*/
			page.println("<code>");
			for (Map.Entry<String,String> pair : env.entrySet()) {
			    page.println(pair.getKey());
			    page.println(pair.getValue());
			}
			//*/
			page.println("</code>");
			page.println("</body></html>");
			page.close();
			
		} catch (Exception e) {
			// Escreve as mensagens de Exception em uma p�gina de resposta.
			// N�o apagar este bloco.
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			
			page.println("<html lang='pt-br'><head><title>Error</title></head><body>");
			page.println("<h1>Error</h1>");
			page.println("<code>");
			page.println(sw.toString());
			page.println("</code>");
			page.println("</body></html>");
			page.close();
		} finally {
			
		}
	}
}
