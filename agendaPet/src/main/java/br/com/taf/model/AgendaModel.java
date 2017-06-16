package br.com.taf.model;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class AgendaModel extends AbstractTableModel {

	private String[] colunas = { "Cliente", "Data", "Hora" };
	private List<Agenda> agendas;

	public AgendaModel(List<Agenda> agendas) {
		this.agendas = agendas;
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public int getRowCount() {
		return agendas.size();
	}

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public Object getValueAt(int linha, int coluna) {
		Agenda agenda = agendas.get(linha);
		switch (coluna) {
		case 0:
			return agenda.getCliente();
		case 1:
			return sdf.format(agenda.getData());
		case 2:
			return agenda.getHora();
		}
		return null;
	}

	public Agenda getValueAt(int linha) {
		return agendas.get(linha);
	}

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
