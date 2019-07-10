package org.resource.create.componentes;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;

@ManagedBean
@ViewScoped
public class MenuView {

	private MenuModel model;

	@PostConstruct
	public void init() {
		model = new DefaultMenuModel();

		DefaultMenuItem item = new DefaultMenuItem("Save");
		item.setIcon("ui-icon-disk");
		item.setCommand("#{menuView.save}");
		item.setUpdate("messages");

		model.addElement(item);
	}

	public MenuModel getModel() {
		return model;
	}

	public void save() {
		//RequestContext.getCurrentInstance().execute("PF('wdlgCargando').show()");
		addMessage("Succsssess", "Data saved");
	}

	public void addMessage(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}