package br.com.aenc.managerbeans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.aenc.entity.Patrulheiro;
import br.com.aenc.entity.Responsavel;
import br.com.aenc.entity.TipoUsuario;
import br.com.aenc.entity.Usuario;
import br.com.aenc.persistence.PatrulheiroJPA;
import br.com.aenc.persistence.ResponsavelJPA;
import br.com.aenc.persistence.UsuarioJPA;

@ManagedBean(name = "uBean")
@SessionScoped
public class UsuarioBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
	private Responsavel responsavel;
	private Patrulheiro patrulheiro;

	// Para renderizar campos no formulario login
	private Boolean isresp = false;
	private Boolean ispatr = true;

	// Para controlar sessao nas views
	private Boolean isLogado = false;

	public UsuarioBean() {
		usuario = new Usuario();
		patrulheiro = new Patrulheiro();
		responsavel = new Responsavel();
	}

	// Enum com tipo de usuários para exibir na página com radio button
	public TipoUsuario[] getTipoUsuarios() {
		return TipoUsuario.values();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

	public Boolean getIsLogado() {
		return isLogado;
	}

	public void setIsLogado(Boolean isLogado) {
		this.isLogado = isLogado;
	}

	public Patrulheiro getPatrulheiro() {
		return patrulheiro;
	}

	public void setPatrulheiro(Patrulheiro patrulheiro) {
		this.patrulheiro = patrulheiro;
	}

	public Boolean getIsresp() {
		return isresp;
	}

	public void setIsresp(Boolean isresp) {
		this.isresp = isresp;
	}

	public Boolean getIspatr() {
		return ispatr;
	}

	public void setIspatr(Boolean ispatr) {
		this.ispatr = ispatr;
	}

	public void renderizarTipo() {
		if (usuario.getTipo().getDescricao().equalsIgnoreCase("Responsavel")) {
			isresp = true;
			ispatr = false;
		} else {
			isresp = false;
			ispatr = true;
		}
	}

	public String cadastrar() {

		FacesContext fc = FacesContext.getCurrentInstance();
		try {

			if (usuario.getTipo().getDescricao()
					.equalsIgnoreCase("Responsavel")) {
				
				responsavel.setName(usuario.getName());
				responsavel.setEmailAddress(usuario.getEmailAddress());
				responsavel.setSenha(usuario.getSenha());
				responsavel.setNewsSemanal(usuario.isNewsSemanal());
				responsavel.setTipo(usuario.getTipo());

				new ResponsavelJPA().gravarAtualizar(responsavel);

				fc.addMessage("form-login-cadastro", new FacesMessage(
						"Cadastrado com sucesso. "
								+ "\nFaça o login no sistema."));

			} else {
				patrulheiro.setName(usuario.getName());
				patrulheiro.setEmailAddress(usuario.getEmailAddress());
				patrulheiro.setSenha(usuario.getSenha());
				patrulheiro.setNewsSemanal(usuario.isNewsSemanal());
				patrulheiro.setTipo(usuario.getTipo());

				new PatrulheiroJPA().gravarAtualizar(patrulheiro);

				fc.addMessage("form-login-cadastro", new FacesMessage(
						"Seja bem vindo Patrulheiro "
								+ "\nFaça o login no sistema."));
			}

			patrulheiro = new Patrulheiro();
			responsavel = new Responsavel();
			usuario = new Usuario();

		} catch (Exception e) {
			e.printStackTrace();

			fc.addMessage("form-login-cadastro", new FacesMessage(
					"Seja bem vindo Patrulheiro "
							+ "\nFaça o login no sistema."));
		}

		return null;
	}

	public String logar() {
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			UsuarioJPA ud = new UsuarioJPA();
			Usuario user = ud.logar(usuario.getEmailAddress(),
					usuario.getSenha());
			if (user != null) {
				System.out.println("Cheguei até o retorno de usuario");
				usuario = new Usuario();
				usuario = user;
				usuario.setSenha(null);
				setIsLogado(true);

				if (user.getTipo().getDescricao()
						.equalsIgnoreCase("responsavel")) {
					setIsresp(true);
					setIspatr(false);
					return "/admin/painelresp?faces-redirect=true";
				} else {
					setIspatr(true);
					return "/admin/painelpatrul?faces-redirect=true";
				}

			} else {
				fc.addMessage("form-login", new FacesMessage(
						"Usuario ou senha invalido"));
			}

		} catch (Exception e) {
			fc.addMessage
					("form-login",
					 new FacesMessage(
								"Desculpe, houve algum problema no servidor...\nTente novamente mais tarde ou entre em contato"));
			e.printStackTrace();
		}

		return null;
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		usuario = new Usuario();
		setIsLogado(false);
		return "/login";
	}
}