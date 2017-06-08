package br.com.taf.controller;

/* 
 * Nessa classe foi atribuido o padrão Creator
 * Ne Classe foi criada uma váriavel de instancia clienteDAO 
 * onde a Classe ClienteDAO será respónsavél para criar uma nova instancia.
 * 
 */

import java.util.List;

import br.com.taf.model.Cliente;
import br.com.taf.model.Pessoa;
import br.com.taf.model.TipoPessoa;
import br.com.taf.repository.ClienteDAO;
import br.com.taf.repository.GenericDAO;

public class ClienteController {

	private TipoPessoa pessoa = new Pessoa();
	private Cliente cliente;

	private GenericDAO<Cliente> clienteDAO;

	public ClienteController() {
		this.cliente = pessoa.criarCLiente();
		this.clienteDAO = ClienteDAO.getInstancia();
	}

	public boolean salvar(Cliente cliente) {
		return this.clienteDAO.salvar(cliente);
	}

	public boolean excluir(Cliente cliente) {
		return this.clienteDAO.excluir(cliente);
	}

	public List<Cliente> lista() {
		return this.clienteDAO.listar();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
