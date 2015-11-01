package br.com.drograria.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;


@Entity
@Table(name = "tbl_funcionarios")
@NamedQueries( {
	@NamedQuery(name = "Funcionario.listar", query = "SELECT funcionario FROM Funcionario funcionario"),
	@NamedQuery(name = "Funcionario.buscarPorCodigo", query = "SELECT funcionario FROM Funcionario funcionario WHERE "
			+ "funcionario.codigo = :codigo"),
	@NamedQuery(name = "Funcionario.autenticar", query = "SELECT funcionario FROM Funcionario funcionario WHERE "
			+ "funcionario.cpf =:cpf AND funcionario.senha =:senha")
})
public class Funcionario implements Serializable {
		

	private static final long serialVersionUID = 1L;

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name = "fun_codigo")
		private Long codigo;
		
		@NotEmpty(message = "Campo Nome obrigatorio")
		@Column(name = "fun_nome", length = 45, nullable = false)
		private String nome;
		
		@CPF(message = "CPF inválido")
		@Column(name = "fun_cpf", length = 14, nullable = false, unique = true)
		private String cpf;
		
		@NotEmpty(message = "Campo Senha obrigatorio")
		@Column(name = "fun_senha", length = 32, nullable = false)
		private String senha;
		
		@NotEmpty(message = "Campo Função obrigatorio")
		@Column(name = "fun_funcao", length = 50, nullable = false)
		private String funcao;

		@Override
		public String toString() {
			return "Funcionario [codigo=" + codigo + ", nome=" + nome
					+ ", cpf=" + cpf + ", senha=" + senha + ", funcao="
					+ funcao + "]";
		}

		public Long getCodigo() {
			return codigo;
		}

		public void setCodigo(Long codigo) {
			this.codigo = codigo;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getCpf() {
			return cpf;
		}

		public void setCpf(String cpf) {
			this.cpf = cpf;
		}

		public String getSenha() {
			return senha;
		}

		public void setSenha(String senha) {
			this.senha = senha;
		}

		public String getFuncao() {
			return funcao;
		}

		public void setFuncao(String funcao) {
			this.funcao = funcao;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((codigo == null) ? 0 : codigo.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Funcionario other = (Funcionario) obj;
			if (codigo == null) {
				if (other.codigo != null)
					return false;
			} else if (!codigo.equals(other.codigo))
				return false;
			return true;
		}
		
		
}
