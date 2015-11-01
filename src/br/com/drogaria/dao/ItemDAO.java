package br.com.drograria.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.drograria.domain.Item;
import br.com.drograria.util.HibernateUtil;

public class ItemDAO {

	
	public void salvar(Item item) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(item);
			transaction.commit();
		} catch (RuntimeException e) {
			if (transaction != null) {
				transaction.rollback();

			}
			throw e;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Item> listar() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Item> items = null;
		try {
			Query consulta = session.getNamedQuery("Item.listar");
			items = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
		return items;
	}

	public Item buscarPorCodigo(Long codigo) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Item item = null;
		try {
			Query consulta = session.getNamedQuery("Item.buscarPorCodigo");
			consulta.setLong("codigo", codigo);
			item = (Item) consulta.uniqueResult();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
		return item;
	}

	public void excluir(Item item) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.delete(item);
			transaction.commit();
		} catch (RuntimeException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		} finally {
			session.close();
		}
	}
	
	
	public void editar(Item item) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(item);
			transaction.commit();
		} catch (RuntimeException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		} finally {
			session.close();
		}
	}
	
	
}
