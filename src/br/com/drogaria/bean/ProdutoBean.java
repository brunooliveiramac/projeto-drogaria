package br.com.drograria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drograria.dao.FabricanteDAO;
import br.com.drograria.dao.ProdutoDAO;
import br.com.drograria.domain.Fabricante;
import br.com.drograria.domain.Produto;
import br.com.drograria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class ProdutoBean implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private Produto produtoCadastro;
	private List<Produto> listaProduto;
	private List<Produto> listaProdutosFiltrados;
	private String acao;
	private Long codigo;
	private List<Fabricante> listaFabricante;
	
	public void salvar() {
		try {
			ProdutoDAO dao = new ProdutoDAO();
			dao.salvar(produtoCadastro);
			FacesUtil.addMgsInfo("Produto salvo com sucesso");
			produtoCadastro = new Produto();

		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addMgsError("Erro ao salvar Produto");
		}

	}
	
	public void editar() {
		try {
			ProdutoDAO dao = new ProdutoDAO();
			dao.editar(produtoCadastro);
			FacesUtil.addMgsInfo("Produto editado com sucesso");
			produtoCadastro = new Produto();

		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addMgsError("Erro ao editar o Produto");
		}

	}
	
	public void excluir() {
		try {
			ProdutoDAO dao = new ProdutoDAO();
			dao.excluir(produtoCadastro);
			FacesUtil.addMgsInfo("Produto removido com sucesso");

		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addMgsError("Erro ao tentar remover o Produto");
		}

	}
	
	
	public void carregar(){
		try {
			ProdutoDAO dao = new ProdutoDAO();
			listaProduto = dao.listar();
			FacesUtil.addMgsInfo("Pesquisa efetuada com sucesso");
			produtoCadastro = new Produto();

		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addMgsError("Erro ao pesquisar Produto");
		}
	}
	
	public void carregarcadastro(){
		try {
				if(codigo != null){
				ProdutoDAO fab = new ProdutoDAO();
				produtoCadastro = fab.buscarPorCodigo(codigo);
				
			}else{
				produtoCadastro = new Produto();
			}
				FabricanteDAO dao = new FabricanteDAO();
				listaFabricante = dao.listar();
		} catch (Exception e) {
			FacesUtil.addMgsError("Erro ao carregar Produto");

		}
	}
	

	public Produto getProdutoCadastro() {
		if (produtoCadastro == null) {
			produtoCadastro = new Produto();
		}
		return produtoCadastro;
	}

	
	
	
	public List<Fabricante> getListaFabricante() {
		return listaFabricante;
	}

	public void setListaFabricante(List<Fabricante> listaFabricante) {
		this.listaFabricante = listaFabricante;
	}

	public List<Produto> getListaProduto() {
		return listaProduto;
	}

	public void setListaProduto(List<Produto> listaProduto) {
		this.listaProduto = listaProduto;
	}

	public List<Produto> getListaProdutosFiltrados() {
		return listaProdutosFiltrados;
	}

	public void setListaProdutosFiltrados(List<Produto> listaProdutosFiltrados) {
		this.listaProdutosFiltrados = listaProdutosFiltrados;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public void setProdutoCadastro(Produto produtoCadastro) {
		this.produtoCadastro = produtoCadastro;
	}

	
	
	
	
	
	

}
