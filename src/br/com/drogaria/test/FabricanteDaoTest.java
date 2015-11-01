package br.com.drograria.test;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drograria.dao.FabricanteDAO;
import br.com.drograria.dao.FuncionarioDAO;
import br.com.drograria.domain.Fabricante;
import br.com.drograria.domain.Funcionario;

public class FabricanteDaoTest {	
	
	@Test
	@Ignore
	public void salvar(){
		Fabricante f1 = new Fabricante();
		f1.setDescricao("A");
		Fabricante f2 = new Fabricante();
		f2.setDescricao("B");
		
		FabricanteDAO dao = new FabricanteDAO();
		dao.salva(f1);
		dao.salva(f2);
	}
	
	@Test
	@Ignore
	public void listar(){
		FabricanteDAO dao = new FabricanteDAO();
		List<Fabricante> fabricantes = dao.listar();
		
		for(Fabricante fabricante : fabricantes){//Pega a coleção de Fabricantes e joga no Obj fabricante
			System.out.println(fabricante);
			
		}
		
	}
	

	@Test
	@Ignore
	public void buscarPorCodigo(){
		FabricanteDAO dao = new FabricanteDAO();
		Fabricante fabricante = dao.buscarPorCodigo(4L);
		System.out.println(fabricante);
		
	}
	
	@Test
	@Ignore
	public void deletar(){
		FabricanteDAO dao = new FabricanteDAO();
		Fabricante f1 = dao.buscarPorCodigo(4L);
		dao.excluir(f1);
	}
	
	
	@Test
	@Ignore
	public void editar(){
		FabricanteDAO dao = new FabricanteDAO();
		Fabricante f1 = dao.buscarPorCodigo(4L);
		f1.setDescricao("TESTE");
		
		dao.editar(f1);
	}
	
	@Test
	@Ignore
	public void autenticar(){
		FuncionarioDAO fdao = new FuncionarioDAO();
		Funcionario fun = fdao.autenticar("442.045.518-74", "1234");
		System.out.println("Fun "+ fun);
		
	}
	
	
	

}
