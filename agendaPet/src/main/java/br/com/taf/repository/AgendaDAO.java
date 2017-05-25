package br.com.taf.repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Session;

import br.com.taf.model.Agenda;



public class AgendaDAO extends GenericDAO<Agenda>{
	
	public AgendaDAO() {
		super(Agenda.class);
	}

	public boolean salvar(Agenda agenda){
		if(agenda.getId() == null){
			// Se salvar mostra essa mensagem
			JOptionPane.showMessageDialog(null, "Salvou!");
			return super.salvar(agenda);
		} else {
			// Se editar mostra essa mensagem
			JOptionPane.showMessageDialog(null, "Editou!");
			return super.editar(agenda);
		}
	}
	
	// Serve para buscar no banco os objetos com a data de hoje
		public List<Agenda> listarDataHoje() {
			Date hj = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			sdf.format(hj);
			Session sessao = getSession().openSession();
			sessao.beginTransaction();
			List lista = sessao.createQuery("From agenda where data ='"+sdf.format(hj)+"'").list();
			// desfaz todas as transações da sessão
			sessao.getTransaction().rollback();
			return lista;
		}
	
}