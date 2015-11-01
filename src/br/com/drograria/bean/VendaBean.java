package br.com.drograria.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.drograria.dao.FuncionarioDAO;
import br.com.drograria.dao.ItemDAO;
import br.com.drograria.dao.ProdutoDAO;
import br.com.drograria.dao.VendaDAO;
import br.com.drograria.domain.Funcionario;
import br.com.drograria.domain.Item;
import br.com.drograria.domain.Produto;
import br.com.drograria.domain.Venda;
import br.com.drograria.util.FacesUtil;


@ManagedBean
@ViewScoped
public class VendaBean implements Serializable {
	

	private static final long serialVersionUID = 1L;
	private List<Produto> listaProduto;
	private List<Produto> listaProdutosFiltrados;
	private List<Item> listaItens;
	private Venda vendaCadastro;
	
	@ManagedProperty(value = "#{autenticacaoBean}") //Injeção. Funcionario Logado. Pega o Objeto instanciado p/ usar em outro Bean
	private AutenticacaoBean atuAutenticacaoBean;
	
	public void carregarProdutos(){
		try {
			ProdutoDAO dao = new ProdutoDAO();
			listaProduto = dao.listar();

		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addMgsError("Erro ao pesquisar Produto");
		}
	}
	
	public void adicionar(Produto produtoSelecionado){
		int posicaoEncontrada = -1;
		
		//interação varrendo a lista de itens verificando se o mesmo ja foi adicionado
		for(int pos = 0; pos < listaItens.size() && posicaoEncontrada < 0; pos++){
			Item itemTemp = listaItens.get(pos);  //Se a posição encontrada tornar-se positiva: stop
			if(itemTemp.getProduto().equals(produtoSelecionado)){ //HashCode and Equals
				posicaoEncontrada = pos;
			}
		}
		
		Item item = new Item();
		item.setProduto(produtoSelecionado);
		
		if(posicaoEncontrada < 0 ){
			item.setQuantidade(1);
			item.setValor(produtoSelecionado.getPreco());
			listaItens.add(item);
		}else{
			Item itemTemp = listaItens.get(posicaoEncontrada);
			item.setQuantidade(itemTemp.getQuantidade() + 1);
			item.setValor(produtoSelecionado.getPreco().multiply(new BigDecimal(item.getQuantidade())));
			listaItens.set(posicaoEncontrada, item);
		}
		
		vendaCadastro.setValor(vendaCadastro.getValor().add(produtoSelecionado.getPreco())); //add: Somar BigDecimal
	}
	
	
	public void remover(Item item){
		int posicaoEncontrada = -1;
		
		//interação varrendo a lista de itens verificando se o mesmo ja foi adicionado
		for(int pos = 0; pos < listaItens.size() && posicaoEncontrada < 0; pos++){
			Item itemTemp = listaItens.get(pos);  //Se a posição encontrada tornar-se positiva: stop
			if(itemTemp.getProduto().equals(item.getProduto())){ //HashCode and Equals
				posicaoEncontrada = pos;
			}
		}
		
		if(posicaoEncontrada > -1){
			listaItens.remove(posicaoEncontrada); //Pelo index
			vendaCadastro.setValor(vendaCadastro.getValor().subtract(item.getValor()));
		}
	}
	
	
	public void carregarDadosVenda(){
		vendaCadastro.setHorario(new Date());
		
		FuncionarioDAO dao = new FuncionarioDAO();
		Funcionario fun = dao.buscarPorCodigo(atuAutenticacaoBean.getFuncionarioLogado().getCodigo());
		
		vendaCadastro.setFuncionario(fun);
		
	}
	
	public void salvar(){
		try {
			//Salvar Venda
			VendaDAO vDao = new VendaDAO();
			Long codigoVenda =	vDao.salvar(vendaCadastro); //Recupero a chave da venda salva
			Venda vendaFk = vDao.buscarPorCodigo(codigoVenda); 
			
			for(Item item : listaItens){ 
				item.setVenda(vendaFk);
				ItemDAO itemDao = new ItemDAO();
				itemDao.salvar(item); //Salvo a chave da venda (Recuperada acima) no item
			}
			
			//Limpa a venda
			vendaCadastro = new Venda();
			vendaCadastro.setValor(new BigDecimal("0.00"));
			//Limpa a lista de itens
			listaItens = new  ArrayList<>();
				
			
			
			FacesUtil.addMgsInfo("Venda salva com sucesso ");
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addMgsError("Erro ao salvar Venda");
		}
	}
	
	
	
	public AutenticacaoBean getAtuAutenticacaoBean() {
		return atuAutenticacaoBean;
	}

	public void setAtuAutenticacaoBean(AutenticacaoBean atuAutenticacaoBean) {
		this.atuAutenticacaoBean = atuAutenticacaoBean;
	}

	public Venda getVendaCadastro() {
		if(vendaCadastro == null){
			vendaCadastro = new Venda();
			vendaCadastro.setValor(new BigDecimal("0.00"));
		}
		return vendaCadastro;
	}

	public void setVendaCadastro(Venda vendaCadastro) {
		this.vendaCadastro = vendaCadastro;
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



	public List<Item> getListaItens() {
		if(listaItens == null){
			listaItens = new ArrayList<>();
		}
		return listaItens;
	}



	public void setListaItens(List<Item> listaItens) {
		this.listaItens = listaItens;
	}
	
	

}
