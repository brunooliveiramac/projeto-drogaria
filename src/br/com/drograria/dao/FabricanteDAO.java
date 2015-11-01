package br.com.drograria.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.drograria.domain.Fabricante;
import br.com.drograria.util.HibernateUtil;

public class FabricanteDAO {
			
		
				public void salva(Fabricante fabricante){
					//Pegar uma sessão aberta e armazena na variavel sessão
					Session sessao = HibernateUtil.getSessionFactory().openSession();
					
					//Abre uma transação, que se der erro retorna
					Transaction trasacao = null;
					try{
						//Sessão aberta para criar a transacao
						trasacao = sessao.beginTransaction();
						sessao.save(fabricante);
						//Confirma a transacao
						trasacao.commit();
					}catch(RuntimeException e){
						//ver se a transacao foi aberta
						if(trasacao != null){
							//desfaz
							trasacao.rollback();
						}
						throw e;
					}finally{
						//fecha sessão
						sessao.close();
					}
					
				}
				
				//Hibernate retorna uma Collection(Um Set ou um List)
				//Para Listar nao precisa de transacao, somente INCLUIR, EXCLUIR, ALTERAR
				@SuppressWarnings("unchecked")
				public List<Fabricante> listar(){
					Session session = HibernateUtil.getSessionFactory().openSession();
					//Recebe o resultado da conculta
					List<Fabricante> fabricantes = null;
					
					try {
						Query consulta = session.getNamedQuery("Fabricante.listar");
						fabricantes = consulta.list();
					} catch (RuntimeException e) {
						throw e;
					}finally{
						session.close();
					}
					return fabricantes;
				}
				
				
				public Fabricante buscarPorCodigo(Long codigo){
					Session session = HibernateUtil.getSessionFactory().openSession();
					//Recebe o resultado da conculta
					Fabricante fabricante = null;
					
					try {
						Query consulta = session.getNamedQuery("Fabricante.buscarPorCodigo");
						consulta.setLong("codigo", codigo);
						
						fabricante = (Fabricante) consulta.uniqueResult(); //CAST: converte
					} catch (RuntimeException e) {
						throw e;
					}finally{
						session.close();
					}
					return fabricante;
				}
				
				
				public void excluir(Fabricante fabricante){
					Session sessao = HibernateUtil.getSessionFactory().openSession();
					Transaction trasacao = null;
					try{
						trasacao = sessao.beginTransaction();
						sessao.delete(fabricante);
						trasacao.commit();
					}catch(RuntimeException e){
						if(trasacao != null){
							trasacao.rollback();
						}
						throw e;
					}finally{
						sessao.close();
					}
					
				}
				
				
				public void excluir(Long codigo){
					Session sessao = HibernateUtil.getSessionFactory().openSession();
					Transaction trasacao = null;
					try{
						trasacao = sessao.beginTransaction();
						Fabricante	fabricante = buscarPorCodigo(codigo);
						sessao.delete(fabricante);
						trasacao.commit();
					}catch(RuntimeException e){
						if(trasacao != null){
							trasacao.rollback();
						}
						throw e;
					}finally{
						sessao.close();
					}
					
				}
				
				public void editar(Fabricante fabricante){
					Session sessao = HibernateUtil.getSessionFactory().openSession();
					Transaction trasacao = null;
					try{
						trasacao = sessao.beginTransaction();
						//Persistence (Desc antiga)   //Transiente(Desc nova)
						
//						Fabricante fabricanteEditar = buscarPorCodigo(fabricante.getCodigo());
//						fabricanteEditar.setDescricao(fabricante.getDescricao());
						
						sessao.update(fabricante);
						trasacao.commit();
					}catch(RuntimeException e){
						if(trasacao != null){
							trasacao.rollback();
						}
						throw e;
					}finally{
						sessao.close();
					}
					
				}
				
				
}
