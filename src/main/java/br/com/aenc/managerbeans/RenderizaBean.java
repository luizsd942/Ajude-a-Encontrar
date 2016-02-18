package br.com.aenc.managerbeans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.com.aenc.entity.Usuario;

@ManagedBean(name="renderiza")
@SessionScoped
public class RenderizaBean {
	// Pega o usuário logado
	@ManagedProperty(value = "#{uBean.usuario}")
	private Usuario usuario;

	private boolean isResp = false;
	private boolean isPatrul = false;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isResp() {
		return isResp;
	}

	public void setResp(boolean isResp) {
		this.isResp = isResp;
	}

	public boolean isPatrul() {
		return isPatrul;
	}

	public void setPatrul(boolean isPatrul) {
		this.isPatrul = isPatrul;
	}

	//Será executado automaticamente antes da renderizacao do painel
	//do usuário, assim poderá ser definido o tipo de menu a ser exibido.
	@PostConstruct
	public void renderizarTipo() {
		//System.out.println("Fui chamado por... " + usuario.getName());
		if (usuario.getTipo().getDescricao().equalsIgnoreCase("Responsavel")) {
			setResp(true);
			setPatrul(false);
		} else {
			setResp(false);
			setPatrul(true);
		}
	}

}
