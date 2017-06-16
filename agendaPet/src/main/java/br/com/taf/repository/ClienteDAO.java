package br.com.taf.repository;

import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import br.com.taf.repository.ClienteDAO;
import br.com.taf.model.Cliente;

public class ClienteDAO extends GenericDAO<Cliente> {

	// Padrão Singleton
	private static ClienteDAO instancia = null;

	public static ClienteDAO getInstancia() {
		if (instancia == null) {
			instancia = new ClienteDAO();
		}
		return instancia;
	}

	SessionFactory getSession() {
		return new Configuration().configure().buildSessionFactory();
	}

	private ClienteDAO() {
		super(Cliente.class);
	}

	public boolean salvar(Cliente c) {
		if (c.getId() == null) {
			// Se salvar mostra essa mensagem
			JOptionPane.showMessageDialog(null, "Salvou!");
			return super.salvar(c);
		} else {
			// Se editar mostra essa mensagem
			JOptionPane.showMessageDialog(null, "Editou!");
			return super.editar(c);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> listarPorNome(String nome) {
		Session session = getSession().openSession();
		return session
				.createQuery("from Cliente as c where c.nome like '%" + nome + "%' or c.endereco like '%" + nome + "%'")
				.list();
	}

}
