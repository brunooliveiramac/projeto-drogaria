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

@Entity
@Table(name = "tbl_fabricantes")                        //HQL: Query com Entidade
@NamedQueries( {
	@NamedQuery(name = "Fabricante.listar", query = "SELECT fabricante FROM Fabricante fabricante"),
	@NamedQuery(name = "Fabricante.buscarPorCodigo", query = "SELECT fabricante FROM Fabricante fabricante WHERE "
			+ "fabricante.codigo = :codigo")
								   //Valor do parametro
} )
public class Fabricante implements Serializable{
		
	
	private static final long serialVersionUID = 1L;

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name = "fab_codigo")
		private Long codigo;
		
		@NotEmpty(message = "Campo Descrição obrigatorio")
		@Size(min = 3, max = 50, message = "Tamanho invalido no campo Descrição (3-50)")
		@Column(name = "fab_descricao", length = 50, nullable = false)
		private String descricao;

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

		@Override
		public String toString() {
			return  descricao;
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
			Fabricante other = (Fabricante) obj;
			if (codigo == null) {
				if (other.codigo != null)
					return false;
			} else if (!codigo.equals(other.codigo))
				return false;
			return true;
		}
		
		
}
