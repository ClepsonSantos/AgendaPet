package br.com.taf.repository;

import javax.swing.JOptionPane;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.taf.model.Usuario;

public class UsuarioDAO extends GenericDAO<Usuario> {

	// Padrão Singleton
	private static UsuarioDAO instancia = null;

	public static UsuarioDAO getInstancia() {
		if (instancia == null) {
			instancia = new UsuarioDAO();
		}
		return instancia;
	}

	private UsuarioDAO() {
		super(Usuario.class);
	}

	public boolean salvar(Usuario u) {
		if (u.getId() == null) {
			// Quando salvar exibe essa mensagem
			JOptionPane.showMessageDialog(null, "Salvou!");
			return super.salvar(u);
		} else {
			// Quando editar exibe essa mensagem
			JOptionPane.showMessageDialog(null, "Editou!");
			return super.editar(u);
		}
	}

	public Usuario buscaLogin(String usuario, String senha) throws Exception {
		Session session = null;
		try {// captura o erro
			session = getSession().openSession();
			Criteria criteria = session.createCriteria(Usuario.class);
			criteria.add(org.hibernate.criterion.Restrictions.eq("login", usuario));
			criteria.add(org.hibernate.criterion.Restrictions.eq("senha", senha));
			return (Usuario) criteria.uniqueResult();
		} catch (Exception e) { // trata a excessão
			System.out.println(e);
			throw e;
		} finally {// finamliza a sessão
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	private Usuario verificaSeDadosIguais(Usuario usuario) {// verifica se se os
															// dados são iguais
		Session session = getSession().openSession();
		return (Usuario) session.createCriteria(Usuario.class).add(Restrictions.eq("login", usuario.getLogin()))
				.add(Restrictions.eq("senha", usuario.getSenha())).uniqueResult();
	}

	public boolean existeUsuarioSemelhante(Usuario usuario) { // verifica se
																// exite usuario
																// iguais
		Usuario usuarioSemelhante = this.verificaSeDadosIguais(usuario);
		return usuarioSemelhante != null && !usuarioSemelhante.equals(usuario);
	}

}