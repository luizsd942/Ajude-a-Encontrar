package br.com.aenc.managerbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.com.aenc.entity.Desaparecido;
import br.com.aenc.entity.DescricaoDesaparecido;
import br.com.aenc.entity.PatrulDesapLocaliza;
import br.com.aenc.entity.Patrulheiro;
import br.com.aenc.entity.Usuario;
import br.com.aenc.persistence.AcompanharDesaparecidoJPA;
import br.com.aenc.persistence.DesaparecidoJPA;
import br.com.aenc.util.jsf.FacesUtil;

@ManagedBean(name = "patrulBean")
@SessionScoped
public class PatrulheiroBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Desaparecido desaparecido;
	private DescricaoDesaparecido descricao;
	private List<Desaparecido> listaDesaparecido;
	private Patrulheiro patrulheiro;
	private PatrulDesapLocaliza assocLocaliza;

	private String situacao = null;

	// Pega o usuário logado
	@ManagedProperty(value = "#{uBean.usuario}")
	private Usuario usuario;

	@PostConstruct
	public void inicializar() {
		System.out.println("Fui chamado por... " + usuario.getName() + "id: "
				+ usuario.getId());
		try {
			limpar();
			if (usuario.getEmailAddress() == null
					&& usuario.getEmailAddress() == "") {
				System.out.println("Usuario ainda não logado");
			} else {
				System.out.println("usuáro já logado em PatrulBean");
				listaDesaparecido = new DesaparecidoJPA()
						.buscarDesaparecidosPorIdPatrulheiro(usuario);
			}
		} catch (Exception e) {
			System.out
					.println("Não foi possível resgatar os dados em PatrulheiroBean");
			e.printStackTrace();
		}
	}

	public void limpar() {
		desaparecido = new Desaparecido();
		descricao = new DescricaoDesaparecido();
		patrulheiro = new Patrulheiro();
		assocLocaliza = new PatrulDesapLocaliza();
	}

	public void iniciarLista() {
		System.out.println("Fui chamado por.lista. " + usuario.getName() + "id: "
				+ usuario.getId());
		try {
			System.out.println("chamou inicia lista com preRender");
			listaDesaparecido = new DesaparecidoJPA()
					.buscarDesaparecidosPorIdPatrulheiro(usuario);

		} catch (Exception e) {
			System.out
					.println("Não foi possível resgatar os dados em PatrulheiroBean");
			e.printStackTrace();
		}
	}

	public PatrulDesapLocaliza getAssocLocaliza() {
		return assocLocaliza;
	}

	public void setAssocLocaliza(PatrulDesapLocaliza assocLocaliza) {
		this.assocLocaliza = assocLocaliza;
	}

	public Patrulheiro getPatrulheiro() {
		return patrulheiro;
	}

	public void setPatrulheiro(Patrulheiro patrulheiro) {
		this.patrulheiro = patrulheiro;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Desaparecido getDesaparecido() {
		return desaparecido;
	}

	public void setDesaparecido(Desaparecido desaparecido) {
		this.desaparecido = desaparecido;
	}

	public DescricaoDesaparecido getDescricao() {
		return descricao;
	}

	public void setDescricao(DescricaoDesaparecido descricao) {
		this.descricao = descricao;
	}

	public List<Desaparecido> getListaDesaparecido() {
		return listaDesaparecido;
	}

	public void setListaDesaparecido(List<Desaparecido> listaDesaparecido) {
		this.listaDesaparecido = listaDesaparecido;
	}

	public String getSituacao() {
		if (desaparecido.isEncontrado()) {
			situacao = "Encontrado";
		} else {
			situacao = "Desaparecido";
		}
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	// Metodo para teste, somente faz a chamada da pagina para cadastrar
	// localizacao do desap
	public String localizarDesaparecido() {
		System.out.println("----------sss--------");
		System.out.println("param desap:" + desaparecido.getApelido());
		System.out.println("assoc id: " + assocLocaliza.getId());
		return "informarlocalizacao?faces-redirect=true";
	}

	/*
	 * Resgata a associacao entre o desaparecido selecionado e o patrulheiro
	 * logado para usar no cadastroLocaliza
	 */
	public void action() {
		try {
			List<PatrulDesapLocaliza> listaAssoc = new ArrayList<PatrulDesapLocaliza>();
			listaAssoc = new AcompanharDesaparecidoJPA().selecionarAssociacao(
					desaparecido, usuario);
			this.assocLocaliza = listaAssoc.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Exclui uma associacao PatrulheiroDesaparecidLocaliza.
	public void excluir() {
		try {
			System.out.println("Associação\nDesap: "
					+ desaparecido.getApelido() + " " + desaparecido.getId());
			System.out.println("Patrul: " + usuario.getName() + " "
					+ usuario.getId());
			new AcompanharDesaparecidoJPA().excluirAssociacao(desaparecido,
					usuario);
			this.listaDesaparecido.remove(desaparecido);

			FacesUtil.addSuccessMessageWithDetail("msgTabela", "Exclúido",
					"A ocorrência de " + desaparecido.getNome()
							+ " foi excluída com sucesso.");
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil
					.addErrorMessageWithDetail("msgTabela", "Erro",
							"Desculpe, houve algum erro no servidor, tente novamente mais tarde.");
		}
	}

	public void cadastroLocalizar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			System.out.println("id assoc e proced antes de grav "
					+ assocLocaliza.getId() + " "
					+ assocLocaliza.getProcedimento());
			new AcompanharDesaparecidoJPA().gravarAtualizar(assocLocaliza);

			fc.addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO!",
							" Dados sobre a ocorrência de "
									+ desaparecido.getNome()
									+ " foram enviados."));
			ExternalContext ec = fc.getExternalContext();
			ec.getFlash().setKeepMessages(true);
			NavigationHandler nh = fc.getApplication().getNavigationHandler();
			nh.handleNavigation(fc, null,
					"/admin/painelpatrul?faces-redirect=true");
			limpar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
