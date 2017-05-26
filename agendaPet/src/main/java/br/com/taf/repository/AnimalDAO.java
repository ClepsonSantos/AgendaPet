package br.com.taf.repository;

import javax.swing.JOptionPane;

import br.com.taf.model.Animal;



public class AnimalDAO extends GenericDAO<Animal> {
	
	// Padrão Singleton
	private static AnimalDAO instancia = null;

	public static AnimalDAO getInstancia() {
		if (instancia == null) {
			instancia = new AnimalDAO();
		}
		return instancia;
	}


	public AnimalDAO() {
		super(Animal.class);
	}
	public boolean salvar(Animal a){
		if(a.getId() == null){
			// Se salvar mostra essa mensagem
			JOptionPane.showMessageDialog(null, "Salvou!");
			return super.salvar(a); 
		} else {
			// Se editar mostra essa mensagem
			JOptionPane.showMessageDialog(null, "Editou!");
			return super.editar(a);
		}
	}
	
}
