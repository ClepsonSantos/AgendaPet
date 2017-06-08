package br.com.taf.controller;

/* 
 * Nessa classe foi atribuido o padrão Creator
 * Ne Classe foi criada uma váriavel de instancia agendaDAO 
 * onde a Classe AgendaDAO será respónsavél para criar uma nova instancia.
 * 
 */

import java.util.List;

import br.com.taf.model.Agenda;
import br.com.taf.repository.AgendaDAO;
import br.com.taf.repository.DAO;
import br.com.taf.repository.GenericDAO;

public class AgendaController {

	private Agenda agenda;

	private GenericDAO<Agenda> agendaDAO;

	public AgendaController() {
		this.agenda = new Agenda();
		this.agendaDAO = AgendaDAO.getInstancia();
	}

	public boolean salvar(Agenda agenda) {
		return this.agendaDAO.salvar(agenda);
	}

	public boolean excluir(Agenda agenda) {
		return this.agendaDAO.excluir(agenda);
	}

	public List<Agenda> lista() {
		return this.agendaDAO.listar();
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

}
