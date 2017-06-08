package br.com.taf.controller;

/* 
 * Nessa classe foi atribuido o padrão Creator
 * Ne Classe foi criada uma váriavel de instancia animalDAO 
 * onde a Classe AnimalDAO será respónsavél para criar uma nova instancia.
 * 
 */

import java.util.List;

import br.com.taf.model.Animal;
import br.com.taf.repository.AnimalDAO;
import br.com.taf.repository.GenericDAO;

public class AnimalController {

	private Animal animal;

	private GenericDAO<Animal> animalDAO;

	public AnimalController() {
		this.animal = new Animal();
		this.animalDAO = AnimalDAO.getInstancia();
	}

	public boolean salvar(Animal animal) {
		return this.animalDAO.salvar(animal);
	}

	public boolean excluir(Animal animal) {
		return this.animalDAO.excluir(animal);
	}

	public List<Animal> lista() {
		return this.animalDAO.listar();
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

}
