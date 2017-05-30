package br.com.taf.controller;

import java.util.List;

import br.com.taf.model.Cliente;
import br.com.taf.repository.ClienteDAO;
import br.com.taf.repository.DAO;

public class ClienteController {
	
	private Cliente cliente;
	
	private DAO<Cliente> clienteDAO;
	
	public ClienteController() {
		this.cliente = new Cliente();
		this.clienteDAO = ClienteDAO.getInstancia();
	}
	
	public boolean salvar(Cliente cliente){
		return this.clienteDAO.salvar(cliente);
	}
	
	public boolean excluir(Cliente cliente){
		return this.clienteDAO.salvar(cliente);
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
