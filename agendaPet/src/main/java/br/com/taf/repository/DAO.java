package br.com.taf.repository;

import java.util.List;

public interface DAO<T> {
	
	public boolean salvar(T t);
	public List<T> listar();
	public boolean editar(T t);
	public boolean excluir(T t);

}
