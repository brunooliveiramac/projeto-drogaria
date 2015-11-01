package br.com.drograria.test;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;

import br.com.drograria.dao.FuncionarioDAO;
import br.com.drograria.dao.VendaDAO;
import br.com.drograria.domain.Funcionario;
import br.com.drograria.domain.Venda;

public class VendaDaoTest {
	
	
	@Test
	public void salvar(){
		
		VendaDAO vDao = new VendaDAO();
		Venda ven = new Venda();
		
		FuncionarioDAO fDao = new FuncionarioDAO();
		Funcionario fun = new Funcionario();
		fun = fDao.buscarPorCodigo(1L);
		
		ven.setValor(new BigDecimal(12.56D));
		ven.setHorario(new Date());
		ven.setFuncionario(fun);
		
		vDao.salvar(ven);
		
		
	}

}
