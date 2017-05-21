package br.com.taf.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.taf.model.Animal;

public class AnimalModel extends AbstractTableModel {

	private String[] colunas = { "Id", "Nome", "Cliente", "tipo" };
	private List<Animal> animals;

	public AnimalModel(List<Animal> animals) {
		this.animals = animals;
	}

	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public int getRowCount() {
		return animals.size();
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		Animal animal = animals.get(linha);
		switch (coluna) {
		case 0:
			return animal.getId();
		case 1:
			return animal.getNome();
		case 2:
			return animal.getCliente();
		case 3:
			return animal.getTipo();
		}
		return null;
	}

	public Animal getValueAt(int linha) {
		return animals.get(linha);
	}

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
		}
		return null;
	}
}
