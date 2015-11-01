package br.com.drograria.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.drograria.domain.Produto;
import br.com.drograria.util.HibernateUtil;

public class ProdutoDAO {

	public void salvar(Produto produto) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(produto);
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
	public List<Produto> listar() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Produto> produtos = null;
		try {
			Query consulta = session.getNamedQuery("Produto.listar");
			produtos = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
		return produtos;
	}

	public Produto buscarPorCodigo(Long codigo) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Produto produto = null;
		try {
			Query consulta = session.getNamedQuery("Produto.buscarPorCodigo");
			consulta.setLong("codigo", codigo);
			produto = (Produto) consulta.uniqueResult();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
		return produto;
	}

	public void excluir(Produto produto) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.delete(produto);
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
	
	
	public void editar(Produto produto) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(produto);
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
