package br.com.taf.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;


public class UsuarioModel extends AbstractTableModel {
	private String[] colunas = { "Id", "Login", "Senha" };
	private List<Usuario> usuarios;

	public UsuarioModel(List<Usuario> usuarios) {
		this.usuarios = usuarios;
		super.fireTableDataChanged();
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public int getRowCount() {
		return usuarios.size();
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		Usuario usuario = usuarios.get(linha);
		switch (coluna) {
		case 0:
			return usuario.getId();
		case 1:
			return usuario.getLogin();
		case 2:
			return usuario.getSenha();
		}
		return null;
	}

	public Usuario getValueAt(int linha) {
		return usuarios.get(linha);
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
		}
		return null;
	}
}
