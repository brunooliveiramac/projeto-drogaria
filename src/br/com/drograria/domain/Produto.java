package br.com.drograria.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tbl_produtos")
@NamedQueries( {
	@NamedQuery(name = "Produto.listar", query = "SELECT produto FROM Produto produto"), //Quando pesquisa o produto traz o FAB (EAGER)
	@NamedQuery(name = "Produto.buscarPorCodigo", query = "SELECT produto FROM Produto produto WHERE "
			+ "produto.codigo = :codigo")
								   //Valor do parametro
} )
public class Produto implements Serializable {
		
	


	private static final long serialVersionUID = 1L;

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name = "pro_codigo")
		private Long codigo;
		
		@NotEmpty(message = "Campo Descrição obrigatorio")
		@Column(name = "pro_descricao", length = 50, nullable = false)
		private String descricao;
		
		@NotNull(message = "Campo Preço obrigatorio")
		@DecimalMin(value = "0.00", message = "Campo Preço maior ou igual a 0")
		@DecimalMax(value = "99999.99", message = "Campo Preço menor que 100000.00")
		@Column(name = "pro_preco", precision = 7, scale = 2, nullable = false)
		private BigDecimal preco;
		
		
		@NotNull(message = "Campo Quantidade obrigatorio")
		@Min(value = 0, message = "Campo Quantidade maior ou igual a 0")
		@Max(value = 9999, message = "Campo Quantidade menor que 9999")
		@Column(name = "pro_quantidade", nullable = false)
		private Integer quantidade;
		
		//Chave estrageira: unidirecional ou bidirecional
		//No hibernate a chave estrangeira é um objeto
		//Muitos produtos tem um Fabricante
		@NotNull(message = "Campo Fabricante obrigatorio")
		@ManyToOne(fetch = FetchType.EAGER) //Eager: Carregou os produtos, carrega tambem os fabricantes
		@JoinColumn(name = "tbl_fabricantes_fab_codigo", referencedColumnName = "fab_codigo", nullable = false)
		private Fabricante fabricante;

		public Long getCodigo() {
			return codigo;
		}

		public void setCodigo(Long codigo) {
			this.codigo = codigo;
		}

		public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}

		public BigDecimal getPreco() {
			return preco;
		}

		public void setPreco(BigDecimal preco) {
			this.preco = preco;
		}

		public Integer getQuantidade() {
			return quantidade;
		}

		public void setQuantidade(Integer quantidade) {
			this.quantidade = quantidade;
		}

		public Fabricante getFabricante() {
			return fabricante;
		}

		public void setFabricante(Fabricante fabricante) {
			this.fabricante = fabricante;
		}
		
		@Override
		public String toString() {
			return "Produto [codigo=" + codigo + ", descricao=" + descricao
					+ ", preco=" + preco + ", quantidade=" + quantidade
					+ ", fabricante=" + fabricante + "]";
		}

		//Necessario para conversão de obj para string e vice-cersa
		
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
			Produto other = (Produto) obj;
			if (codigo == null) {
				if (other.codigo != null)
					return false;
			} else if (!codigo.equals(other.codigo))
				return false;
			return true;
		}
		
		
}
