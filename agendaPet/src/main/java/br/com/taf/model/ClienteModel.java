package br.com.taf.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ClienteModel extends AbstractTableModel {

	private String[] colunas = { "Id", "Nome", "Endereco", "Celular", "Telefone" };
	private List<Cliente> clientes;

	public ClienteModel(List<Cliente> clientes) {
		this.clientes = clientes;
		super.fireTableDataChanged();
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public int getRowCount() {
		return clientes.size();
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		Cliente cliente = clientes.get(linha);
		switch (coluna) {
		case 0:
			return cliente.getId();
		case 1:
			return cliente.getNome();
		case 2:
			return cliente.getEndereco();
		case 3:
			return cliente.getCelular();
		case 4:
			return cliente.getTelefone();
		}
		return null;
	}

	public Cliente getValueAt(int linha) {
		return clientes.get(linha);
	}

	@Override
	public String getColumnName(int column) {
		switch (column) {
		case 0:
			return colunas[0];
		case 1:
			return colunas[1];
		case 2:
			return colunas[2];
		case 3:
			return colunas[3];
		case 4:
			return colunas[4];
		}
		return null;
	}

}
