package br.com.drograria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drograria.dao.FabricanteDAO;
import br.com.drograria.domain.Fabricante;
import br.com.drograria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class FabricanteBean implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private Fabricante fabricanteCadastro;
	private List<Fabricante> listaFaricante;
	private List<Fabricante> listaFabricantesFiltrados;
	private String acao;
	private Long codigo;
	
	public void salvar() {
		try {
			FabricanteDAO dao = new FabricanteDAO();
			dao.salva(fabricanteCadastro);
			FacesUtil.addMgsInfo("Fabricante salvo com sucesso");
			fabricanteCadastro = new Fabricante();

		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addMgsError("Erro ao salvar Fabricante");
		}

	}
	
	public void editar() {
		try {
			FabricanteDAO dao = new FabricanteDAO();
			dao.editar(fabricanteCadastro);
			FacesUtil.addMgsInfo("Fabricante editado com sucesso");
			fabricanteCadastro = new Fabricante();

		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addMgsError("Erro ao editar o Fabricante");
		}

	}
	
	public void excluir() {
		try {
			FabricanteDAO dao = new FabricanteDAO();
			dao.excluir(fabricanteCadastro);
			FacesUtil.addMgsInfo("Fabricante removido com sucesso");

		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addMgsError("Erro ao tentar remover o Fabricante");
		}

	}
	
	
	public void carregar(){
		try {
			FabricanteDAO dao = new FabricanteDAO();
			listaFaricante = dao.listar();
			FacesUtil.addMgsInfo("Pesquisa efetuada com sucesso");
			fabricanteCadastro = new Fabricante();

		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addMgsError("Erro ao pesquisar Fabricante");
		}
	}
	
	public void carregarcadastro(){
		try {
				if(codigo != null){
				FabricanteDAO fab = new FabricanteDAO();
				fabricanteCadastro = fab.buscarPorCodigo(codigo);
			}else{
				fabricanteCadastro = new Fabricante();
			}
		} catch (Exception e) {
			FacesUtil.addMgsError("Erro ao carregar Fabricante");

		}
	}
	

	public Fabricante getFabricanteCadastro() {
		if (fabricanteCadastro == null) {
			fabricanteCadastro = new Fabricante();
		}
		return fabricanteCadastro;
	}

	
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public void setFabricanteCadastro(Fabricante fabricanteCadastro) {
		this.fabricanteCadastro = fabricanteCadastro;
	}

	public List<Fabricante> getListaFaricante() {
		return listaFaricante;
	}

	public void setListaFaricante(List<Fabricante> listaFaricante) {
		this.listaFaricante = listaFaricante;
	}

	public List<Fabricante> getListaFabricantesFiltrados() {
		return listaFabricantesFiltrados;
	}

	public void setListaFabricantesFiltrados(
			List<Fabricante> listaFabricantesFiltrados) {
		this.listaFabricantesFiltrados = listaFabricantesFiltrados;
	}
	
	

}
