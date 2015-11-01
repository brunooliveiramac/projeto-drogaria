package br.com.drograria.util;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class FacesUtil {

	public static void addMgsInfo(String mesagem) {

		FacesMessage facesMessage = new FacesMessage(
				FacesMessage.SEVERITY_INFO, mesagem, mesagem);
		// Captura contexto do JSF
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, facesMessage);
	}

	public static void addMgsError(String mesagem) {
		FacesMessage facesMessage = new FacesMessage(
				FacesMessage.SEVERITY_ERROR, mesagem, mesagem);
		// Captura contexto do JSF
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, facesMessage);
	}
	
	//Metodo estatico
	public static String getParam(String nome){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		Map<String, String> parametros = externalContext.getRequestParameterMap();
		String valor = parametros.get(nome);
		return valor;
		
	}
	
}
