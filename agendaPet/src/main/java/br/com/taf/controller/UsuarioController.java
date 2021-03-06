package br.com.taf.controller;

/* 
 * Nessa classe foi atribuido o padrão Creator
 * Nessa Classe foi criada uma váriavel de instancia usuarioDAO 
 * onde a Classe UsuarioDAO será respónsavél para criar uma nova instancia.
 * 
 */

import java.util.List;

import br.com.taf.model.Pessoa;
import br.com.taf.model.TipoPessoa;
import br.com.taf.model.Usuario;
import br.com.taf.repository.GenericDAO;
import br.com.taf.repository.UsuarioDAO;

public class UsuarioController {

	private TipoPessoa pessoa = new Pessoa();
	private Usuario usuario;

	private GenericDAO<Usuario> usuarioDAO;

	public UsuarioController() {
		this.usuario = pessoa.criarUsuario();
		this.usuarioDAO = UsuarioDAO.getInstancia();
	}

	public boolean salvar(Usuario usuario) {
		return this.usuarioDAO.salvar(usuario);
	}

	public boolean excluir(Usuario usuario) {
		return this.usuarioDAO.excluir(usuario);
	}

	public List<Usuario> lista() {
		return this.usuarioDAO.listar();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
