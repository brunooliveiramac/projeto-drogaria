package br.com.drograria.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.drograria.dao.FabricanteDAO;
import br.com.drograria.domain.Fabricante;

@FacesConverter("fabricanteConverter")
public class FabricanteConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent componete,
			String valor) {
		try {
			Long codigo = Long.parseLong(valor);
			FabricanteDAO fabDao = new FabricanteDAO();
			Fabricante fab = fabDao.buscarPorCodigo(codigo);
			return fab;
		} catch (RuntimeException e) {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent componete,
			Object objeto) {
		try {	//Converte o objeto para um objeto do tipo fabricante
				Fabricante fab = (Fabricante) objeto;
				Long codigo = fab.getCodigo();
				return codigo.toString();
		} catch (RuntimeException e) {
			return null;
		}
	}

}
