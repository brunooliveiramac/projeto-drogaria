package br.com.drograria.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//Todo Listener deve ser indicado no WEB.XML para o funcionamento

//Classe definida para o carregamento da Fabrica de Sessão do Hibernate
//Carregamento junto com o TomCat
public class ContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		HibernateUtil.getSessionFactory().close();
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		HibernateUtil.getSessionFactory().openSession();
		
	}

}
