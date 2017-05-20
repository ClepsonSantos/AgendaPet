package br.com.taf.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GenericDAO<T> implements DAO<T>{
	
	private Class classe;

	public GenericDAO(Class classe) {
		this.classe = classe;
	}

	SessionFactory getSession(){
		return new Configuration().configure().buildSessionFactory();
	}
	
	@Override
	public boolean salvar(T t) {
		Session session = null;
		try {
			session = getSession().openSession();
			session.beginTransaction();
			session.save(t);
			session.beginTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			session.close();
		}
		return true;
	}

	@Override
	public boolean editar(T t) {
		Session session = null;
		try {
			session = getSession().openSession();
			session.beginTransaction();
			session.update(t);
			session.beginTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			session.close();
		}
		return true;
	}

	@Override
	public boolean excluir(T t) {
		Session session = null;
		try {
			session = getSession().openSession();
			session.beginTransaction();
			session.delete(t);
			session.beginTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			session.close();
		}
		return true;
	}

	@Override
	public List<T> listar() {
		List<T> lista = null;
		Session sessao = null;
		try {
			sessao = getSession().openSession();
			lista = sessao.createCriteria(classe).list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return lista;
	}
	
	

}