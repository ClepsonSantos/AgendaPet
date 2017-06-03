package br.com.taf.controller;

import java.util.List;

import br.com.taf.model.Agenda;
import br.com.taf.repository.AgendaDAO;
import br.com.taf.repository.DAO;

public class AgandaController {
	
private Agenda agenda;
	
	private DAO<Agenda> agendaDAO;
	
	public AgandaController() {
		this.agenda = new Agenda();
		this.agendaDAO = AgendaDAO.getInstancia();
	}
	
	public boolean salvar(Agenda agenda){
		return this.agendaDAO.salvar(agenda);
	}
	
	public boolean excluir(Agenda agenda){
		return this.agendaDAO.excluir(agenda);
	}
	
	public List<Agenda> lista(){
		return this.agendaDAO.listar();
	}

	public Agenda getAgenda() {
		return agenda;
	}
	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}	

}
