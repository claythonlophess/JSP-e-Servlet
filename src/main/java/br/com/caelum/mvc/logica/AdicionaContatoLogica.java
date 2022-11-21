package br.com.caelum.mvc.logica;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.bean.Contato;
import br.com.caelum.dao.ContatoDao;

@WebServlet("/adicionaContato")
public class AdicionaContatoLogica extends HttpServlet {

	/**
	 * O	método		service		aceita	todos	os	métodos	HTTP,	portanto,	tanto	o	método	GET	quanto	o	POST.
	 * @return 
	 * @throws IOException 
	 * @throws ServletException 

	 */
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		PrintWriter out = null;;
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// pegando os parâmetros do request
		String nome = request.getParameter("nome");
		String endereco = request.getParameter("endereco");
		String email = request.getParameter("email");
		String dataEmTexto = request.getParameter("dataNascimento");
		Calendar dataNascimento = null;
		// fazendo a conversão da data
		try {
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dataEmTexto);
			dataNascimento = Calendar.getInstance();
			dataNascimento.setTime(date);
		} catch (ParseException e) {
			out.println("Erro	de	conversão	da	data");
			
		}
		// monta um objeto contato
		Contato contato = new Contato();
		contato.setNome(nome);
		contato.setEndereco(endereco);
		contato.setEmail(email);
		contato.setDataNascimento(dataNascimento);
		// salva o contato
		ContatoDao dao   = new ContatoDao();
		dao.adiciona(contato);
		RequestDispatcher	rd	=	request	.getRequestDispatcher("/lista-contatos.jsp");
       rd.forward(request,response);

	}
	

}