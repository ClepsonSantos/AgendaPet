package br.com.taf.model;

/*
 * Classe base para criar todos os tipos de pessoa; 
 * 
 */

public class Pessoa implements TipoPessoa {

	@Override
	public Usuario criarUsuario() {
		return new Usuario();
	}

	@Override
	public Cliente criarCLiente() {
		return new Cliente();
	}

}
