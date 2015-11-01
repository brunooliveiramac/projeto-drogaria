package br.com.drograria.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.codec.digest.DigestUtils;

import br.com.drograria.dao.FuncionarioDAO;
import br.com.drograria.domain.Funcionario;
import br.com.drograria.util.FacesUtil;

@ManagedBean
@SessionScoped  //Existe durante todo o tempo de sessão do usuario
public class AutenticacaoBean {
		
		private Funcionario funcionarioLogado;

		
		public void autenticar(){
			try {
					FuncionarioDAO dao = new FuncionarioDAO();
					//Gerar Hash
					//funcionarioLogado.setSenha(DigestUtils.md5Hex(funcionarioLogado.getSenha()));
					funcionarioLogado = dao.autenticar(funcionarioLogado.getCpf(), DigestUtils.md5Hex(funcionarioLogado.getSenha()));
					if (funcionarioLogado == null){
						FacesUtil.addMgsError("Dados inválidos!");
					}else{
						FacesUtil.addMgsInfo("Sucesso!");
					}
				
			} catch (RuntimeException e) {
					FacesUtil.addMgsError("Erro");
			}
		}
		
		public String sair(){
			funcionarioLogado = null;
			return "/pages/autenticacao.xhtml?faces-redirect=true";
		}
		
		
		public Funcionario getFuncionarioLogado() {
			if(funcionarioLogado == null){
				funcionarioLogado = new Funcionario();
			}
			return funcionarioLogado;
		}

		public void setFuncionarioLogado(Funcionario funcionarioLogado) {
			this.funcionarioLogado = funcionarioLogado;
		}
		
		
}
