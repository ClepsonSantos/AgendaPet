package br.com.taf.controller;

import java.util.List;

import br.com.taf.model.Cliente;
import br.com.taf.model.Pessoa;
import br.com.taf.model.TipoPessoa;
import br.com.taf.repository.ClienteDAO;
import br.com.taf.repository.DAO;

public class ClienteController {
	
	private TipoPessoa pessoa = new Pessoa();
	private Cliente cliente;
	
	private DAO<Cliente> clienteDAO;
	
	public ClienteController() {
		this.cliente = pessoa.criarCLiente();
		this.clienteDAO = ClienteDAO.getInstancia();
	}
	
	public boolean salvar(Cliente cliente){
		return this.clienteDAO.salvar(cliente);
	}
	
	public boolean excluir(Cliente cliente){
		return this.clienteDAO.excluir(cliente);
	}
	
	public List<Cliente> lista(){
		return this.clienteDAO.listar();
	}

	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}	

	
}
