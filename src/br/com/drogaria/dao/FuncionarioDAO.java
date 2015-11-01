package br.com.drograria.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.drograria.domain.Funcionario;
import br.com.drograria.util.HibernateUtil;

public class FuncionarioDAO {

	public void salva(Funcionario funcionario) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction trasacao = null;
		try {
			trasacao = sessao.beginTransaction();
			sessao.save(funcionario);
			trasacao.commit();
		} catch (RuntimeException e) {
			if (trasacao != null) {
				trasacao.rollback();
			}
			throw e;
		} finally {
			sessao.close();
		}

	}

	@SuppressWarnings("unchecked")
	public List<Funcionario> listar() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Funcionario> funcionarios = null;

		try {
			Query consulta = session.getNamedQuery("Funcionario.listar");
			funcionarios = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
		return funcionarios;
	}

	public Funcionario buscarPorCodigo(Long codigo) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Funcionario funcionario = null;
		try {
			Query consulta = session
					.getNamedQuery("Funcionario.buscarPorCodigo");
			consulta.setLong("codigo", codigo);
			funcionario = (Funcionario) consulta.uniqueResult(); // CAST:
																	// converte
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
		return funcionario;
	}

	public void excluir(Funcionario funcionario) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction trasacao = null;
		try {
			trasacao = sessao.beginTransaction();
			sessao.delete(funcionario);
			trasacao.commit();
		} catch (RuntimeException e) {
			if (trasacao != null) {
				trasacao.rollback();
			}
			throw e;
		} finally {
			sessao.close();
		}

	}

	public void excluir(Long codigo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction trasacao = null;
		try {
			trasacao = sessao.beginTransaction();
			Funcionario funcionario = buscarPorCodigo(codigo);
			sessao.delete(funcionario);
			trasacao.commit();
		} catch (RuntimeException e) {
			if (trasacao != null) {
				trasacao.rollback();
			}
			throw e;
		} finally {
			sessao.close();
		}

	}

	public void editar(Funcionario funcionario) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction trasacao = null;
		try {
			trasacao = sessao.beginTransaction();
			sessao.update(funcionario);
			trasacao.commit();
		} catch (RuntimeException e) {
			if (trasacao != null) {
				trasacao.rollback();
			}
			throw e;
		} finally {
			sessao.close();
		}

	}
	
	public Funcionario autenticar(String cpf, String senha){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Funcionario funcionario = null;
		try {
			Query consulta = session
					.getNamedQuery("Funcionario.autenticar");
			consulta.setString("cpf", cpf);
			consulta.setString("senha", senha);

			funcionario = (Funcionario) consulta.uniqueResult(); // CAST:
																	// converte
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
		return funcionario;
	}
}
