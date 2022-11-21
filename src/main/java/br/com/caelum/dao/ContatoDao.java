package br.com.caelum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;

import br.com.caelum.bean.Contato;

public class ContatoDao {
	private Connection connection;
	private  ArrayList<Contato> lista;
	private Calendar dataNascimento;

	public ContatoDao() {
		this.lista = new ArrayList<>();
		//this.connection = new ConnectionFactory().getConnection();
	}

	public void remove(Contato contato) {
		lista.remove(contato);

	}

	public List<Contato> getLista() {
		if (lista.isEmpty()) {
			iniciar();
		}
		return lista;
	}
	
		public List<Contato> getLista1() {
		try {
			this.connection = new ConnectionFactory().getConnection();
			List<Contato> contatos = new ArrayList<Contato>();
			PreparedStatement stmt = this.connection.prepareStatement("select	*	from	contatos");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				// criando o objeto Contato
				Contato contato = new Contato();
				contato.setId(rs.getLong("id"));
				contato.setNome(rs.getString("nome"));
				contato.setEmail(rs.getString("email"));
				contato.setEndereco(rs.getString("endereco"));
				// montando a data através do Calendar
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataNascimento"));
				contato.setDataNascimento(data);
				// adicionando o objeto à lista
				contatos.add(contato);
			}
			rs.close();
			stmt.close();
			return contatos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void altera1(Contato contato) {
		String sql = "update	contatos	set	nome=?,	email=?,	endereco=?," + "dataNascimento=?	where	id=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getEmail());
			stmt.setString(3, contato.getEndereco());
			//stmt.setDate(4, new Date(contato.getDataNascimento().getTimeInMillis()));
			stmt.setLong(5, contato.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void remove1(Contato contato) {
		try {
			PreparedStatement stmt = connection.prepareStatement("delete	" + "from	contatos	where	id=?");
			stmt.setLong(1, contato.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void adiciona(Contato contato) {
		lista.add(contato);
	}
	public void iniciar() {
		for (int i = 0; i < 10; i++) {
			try {
				Date date = new SimpleDateFormat("dd/MM/yyyy").parse("10/10/2000");
				dataNascimento = Calendar.getInstance();
				dataNascimento.setTime(date);
			} catch (ParseException e) {
				// out.println("Erro de conversão da data");
				return; // para a execução do método
			}
			
			// monta um objeto contato
			Contato contato = new Contato();
			contato.setNome("alvaro "+i);
			contato.setEndereco("kkkk");
			contato.setEmail("email");
			contato.setDataNascimento(dataNascimento);
			// salva o contato
			lista.add(contato);
		}
		
	}
		public void adiciona1(Contato contato) {
		String sql = "insert	into	contatos	" + "(nome,email,endereco,dataNascimento)" + "	values	(?,?,?,?)";
		try {
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);
			// seta os valores
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getEmail());
			stmt.setString(3, contato.getEndereco());
			//stmt.setDate(4, new Date(contato.getDataNascimento().getTimeInMillis()));
			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
