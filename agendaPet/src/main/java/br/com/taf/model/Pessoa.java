package br.com.taf.model;

public class Pessoa implements TipoPessoa{

	@Override
	public Usuario criarUsuario() {
		return new Usuario();
	}

	@Override
	public Cliente criarCLiente() {
		return new Cliente();
	}

}
