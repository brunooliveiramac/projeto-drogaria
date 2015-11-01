package br.com.drograria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.codec.digest.DigestUtils;

import br.com.drograria.dao.FuncionarioDAO;
import br.com.drograria.domain.Funcionario;
import br.com.drograria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class FuncionarioBean implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private Funcionario funcionarioCadastro;
	private List<Funcionario> listaFuncionario;
	private List<Funcionario> listaFuncionariosFiltrados;
	private String acao;
	private Long codigo;
	
	public void salvar() {
		try {
			FuncionarioDAO dao = new FuncionarioDAO();
			//Gerar Hash
			funcionarioCadastro.setSenha(DigestUtils.md5Hex(funcionarioCadastro.getSenha()));
			
			dao.salva(funcionarioCadastro);
			FacesUtil.addMgsInfo("Funcionario salvo com sucesso");
			funcionarioCadastro = new Funcionario();

		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addMgsError("Erro ao salvar Funcionario");
		}

	}
	
	public void editar() {
		try {
			FuncionarioDAO dao = new FuncionarioDAO();
			//Gerar Hash
			funcionarioCadastro.setSenha(DigestUtils.md5Hex(funcionarioCadastro.getSenha()));
			
			dao.editar(funcionarioCadastro);
			FacesUtil.addMgsInfo("Funcionario editado com sucesso");
			funcionarioCadastro = new Funcionario();

		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addMgsError("Erro ao editar o Funcionario");
		}

	}
	
	public void excluir() {
		try {
			FuncionarioDAO dao = new FuncionarioDAO();
			dao.excluir(funcionarioCadastro);
			FacesUtil.addMgsInfo("Funcionario removido com sucesso");

		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addMgsError("Erro ao tentar remover o Funcionario");
		}

	}
	
	
	public void carregar(){
		try {
			FuncionarioDAO dao = new FuncionarioDAO();
			listaFuncionario = dao.listar();
			FacesUtil.addMgsInfo("Pesquisa efetuada com sucesso");
			funcionarioCadastro = new Funcionario();

		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addMgsError("Erro ao pesquisar Funcionario");
		}
	}
	
	public void carregarcadastro(){
		try {
				if(codigo != null){
				FuncionarioDAO fab = new FuncionarioDAO();
				funcionarioCadastro = fab.buscarPorCodigo(codigo);
			}else{
				funcionarioCadastro = new Funcionario();
			}
		} catch (Exception e) {
			FacesUtil.addMgsError("Erro ao carregar Funcionario");

		}
	}
	

	public Funcionario getFuncionarioCadastro() {
		if (funcionarioCadastro == null) {
			funcionarioCadastro = new Funcionario();
		}
		return funcionarioCadastro;
	}

	public List<Funcionario> getListaFuncionario() {
		return listaFuncionario;
	}

	public void setListaFuncionario(List<Funcionario> listaFuncionario) {
		this.listaFuncionario = listaFuncionario;
	}

	public List<Funcionario> getListaFuncionariosFiltrados() {
		return listaFuncionariosFiltrados;
	}

	public void setListaFuncionariosFiltrados(
			List<Funcionario> listaFuncionariosFiltrados) {
		this.listaFuncionariosFiltrados = listaFuncionariosFiltrados;
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

	public void setFuncionarioCadastro(Funcionario funcionarioCadastro) {
		this.funcionarioCadastro = funcionarioCadastro;
	}

	
	
	
	

}
