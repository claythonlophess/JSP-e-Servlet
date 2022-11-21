package br.com.caelum.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Calendar;

public class ConnectionFactory {
	//private static Calendar dataNascimento;

	public Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost/fg21", "root", "");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

//	public static void main(String[] args) throws SQLException {
//		try {
//			Date date = new SimpleDateFormat("dd/MM/yyyy").parse("10/10/2000");
//			dataNascimento = Calendar.getInstance();
//			dataNascimento.setTime(date);
//		} catch (ParseException e) {
//			// out.println("Erro de conversão da data");
//			return; // para a execução do método
//		}
//		Connection c = getConnection();
//		// monta um objeto contato
//		Contato contato = new Contato();
//		contato.setNome("alvaro");
//		contato.setEndereco("kkkk");
//		contato.setEmail("email");
//		contato.setDataNascimento(dataNascimento);
//		// salva o contato
//		ContatoDao dao = new ContatoDao();
//		dao.adiciona(contato);
//	}
}
