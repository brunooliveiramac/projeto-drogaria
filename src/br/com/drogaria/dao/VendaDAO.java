package br.com.drograria.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.drograria.domain.Venda;
import br.com.drograria.util.HibernateUtil;

public class VendaDAO {

	public Long salvar(Venda venda) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Long cod = null;
		try {
			transaction = session.beginTransaction();
			cod = (Long) session.save(venda); //Retorna codigo salvo
			transaction.commit();
		} catch (RuntimeException e) {
			if (transaction != null) {
				transaction.rollback();

			}
			throw e;
		} finally {
			session.close();
		}
		return cod;
	}

	@SuppressWarnings("unchecked")
	public List<Venda> listar() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Venda> vendas = null;
		try {
			Query consulta = session.getNamedQuery("Venda.listar");
			vendas = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
		return vendas;
	}
	
	public Venda buscarPorCodigo(Long codigo) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Venda venda = null;
		try {
			Query consulta = session.getNamedQuery("Venda.buscarPorCodigo");
			consulta.setLong("codigo", codigo);
			venda = (Venda) consulta.uniqueResult();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
		return venda;
	}
	
	public void excluir(Venda venda) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.delete(venda);
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
	
	
	public void editar(Venda venda) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(venda);
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
