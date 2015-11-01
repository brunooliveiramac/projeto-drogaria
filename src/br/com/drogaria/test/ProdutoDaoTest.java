package br.com.drograria.test;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drograria.dao.FabricanteDAO;
import br.com.drograria.dao.ProdutoDAO;
import br.com.drograria.domain.Fabricante;
import br.com.drograria.domain.Produto;

public class ProdutoDaoTest {

	
	@Test	
	@Ignore
	public void inserir(){
		FabricanteDAO FDao = new FabricanteDAO();
		Fabricante fabricante = FDao.buscarPorCodigo(6L);
		
		
		Produto produto = new Produto();
		produto.setFabricante(fabricante);
		produto.setDescricao("Teste");
		produto.setPreco(new BigDecimal(22.99D));
		produto.setQuantidade(1);
		
		ProdutoDAO PDao = new ProdutoDAO();
		
		PDao.salvar(produto);
		
	}
	
	@Test
	@Ignore
	public void buscar(){
		ProdutoDAO dao = new ProdutoDAO();
		Produto prod = dao.buscarPorCodigo(1L);
		System.out.println(prod);
	}
	
	@Test
	@Ignore
	public void listar(){
		ProdutoDAO dao = new ProdutoDAO();
		List<Produto> produtos = dao.listar();
		System.out.println(produtos);
	}
	
	@Test
	@Ignore
	public void editar(){
		ProdutoDAO dao = new ProdutoDAO();
		FabricanteDAO daoF = new FabricanteDAO();
		Produto prod = new Produto();
		prod = dao.buscarPorCodigo(1L);
		
		Fabricante fab = new Fabricante();
		fab = daoF.buscarPorCodigo(6L);
		
		prod.setDescricao("Desc");
		prod.setPreco(new BigDecimal(32.56D));
		prod.setFabricante(fab);
		prod.setQuantidade(3);
		
		dao.editar(prod);
		
		
	}
}
