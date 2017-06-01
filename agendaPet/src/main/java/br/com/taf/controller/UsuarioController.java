package br.com.taf.controller;

import java.util.List;

import br.com.taf.model.Usuario;
import br.com.taf.repository.DAO;
import br.com.taf.repository.UsuarioDAO;

public class UsuarioController {

private Usuario usuario;
	
	private DAO<Usuario> usuarioDAO;
	
	public UsuarioController() {
		this.usuario = new Usuario();
		this.usuarioDAO = UsuarioDAO.getInstancia();
	}
	
	public boolean salvar(Usuario usuario){
		return this.usuarioDAO.salvar(usuario);
	}
	
	public boolean excluir(Usuario usuario){
		return this.usuarioDAO.excluir(usuario);
	}
	
	public List<Usuario> lista(){
		return this.usuarioDAO.listar();
	}

	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}	

}
