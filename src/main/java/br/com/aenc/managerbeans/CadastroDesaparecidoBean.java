package br.com.aenc.managerbeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;

import br.com.aenc.entity.Desaparecido;
import br.com.aenc.entity.DescricaoDesaparecido;
import br.com.aenc.entity.Encontrado;
import br.com.aenc.entity.Usuario;
import br.com.aenc.persistence.DesaparecidoJPA;
import br.com.aenc.persistence.DescricaoJPA;
import br.com.aenc.persistence.EncontradoJPA;
import br.com.aenc.util.jsf.FacesUtil;

@ManagedBean(name = "desapBean")
@SessionScoped
public class CadastroDesaparecidoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Desaparecido desaparecido;
	private DescricaoDesaparecido descricao;
	private Encontrado encontrado;
	private List<Desaparecido> listaDesaparecido;

	// Pega o usuário logado
	@ManagedProperty(value = "#{uBean.usuario}")
	private Usuario responsavel;

	private UploadArquivo arquivo = new UploadArquivo();

	private String procedimento = "";
	private boolean skip;
	private boolean isdeficiencia = false;
	private boolean booEditar = false;

	/*
	 * Ao carregar o painel do responsavel, a lista de Desaparecidos cadastrados
	 * pelo usuário será carregada uma vez, como Session.
	 */
	@PostConstruct
	public void inicializar() {
		System.out.println("Fui chamado por... " + responsavel.getName()
				+ " id- " + responsavel.getId());

		try {
			limpar();
			listaDesaparecido = new DesaparecidoJPA()
					.buscarDesaparecidosPorIdResponsavel2(responsavel);

			System.out.println("\n\nDesc - Altura: "
					+ listaDesaparecido.get(1).getDescricaoDesaparecido()
							.getAltura());
			System.out
					.println("Desap:" + listaDesaparecido.get(1).getApelido());

		} catch (Exception e) {
			System.out.println("Não foi possível resgatar os dados");
			e.printStackTrace();
		}
	}

	public void limpar() {
		System.out.println("-------\nFui limpado");
		desaparecido = new Desaparecido();
		descricao = new DescricaoDesaparecido();
		encontrado = new Encontrado();
		arquivo = new UploadArquivo();
	}

	public String getProcedimento() {
		return procedimento;
	}

	public void setProcedimento(String procedimento) {
		this.procedimento = procedimento;
	}

	public Encontrado getEncontrado() {
		return encontrado;
	}

	public void setEncontrado(Encontrado encontrado) {
		this.encontrado = encontrado;
	}

	public DescricaoDesaparecido getDescricao() {
		return descricao;
	}

	public void setDescricao(DescricaoDesaparecido descricao) {
		this.descricao = descricao;
	}

	public Desaparecido getDesaparecido() {
		return desaparecido;
	}

	public void setDesaparecido(Desaparecido desaparecido) {
		this.desaparecido = desaparecido;
	}

	public Usuario getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Usuario responsavel) {
		this.responsavel = responsavel;
	}

	public boolean isIsdeficiencia() {
		return isdeficiencia;
	}

	public void setIsdeficiencia(boolean isdeficiencia) {
		this.isdeficiencia = isdeficiencia;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public boolean isBooEditar() {
		return booEditar;
	}

	public void setBooEditar(boolean booEditar) {
		System.out.println("Cadastrar...");
		this.booEditar = booEditar;
	}

	public List<Desaparecido> getListaDesaparecido() {
		System.out.println("devo ser chamado depois do @post...");
		return listaDesaparecido;
	}

	public void setListaDesaparecido(List<Desaparecido> listaDesaparecido) {
		this.listaDesaparecido = listaDesaparecido;
	}

	// Renderiza o campo deficiencia no cadastro, caso o checkbox seja marcado
	public void renderizarDeficiencia() {
		if (descricao.isPossuiDefic()) {
			setIsdeficiencia(true);
		} else {
			setIsdeficiencia(false);
		}
	}

	/*
	 * Metodo para realizar o upload de uma foto Os tipos de dados sao validados
	 * no Primefaces
	 */
	public void uploadAction(FileUploadEvent event) {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			this.arquivo.fileUpload(event, ".jpg", "/upload/");
			this.desaparecido.setFoto(this.arquivo.getNome());

			fc.addMessage("formcaddesap", new FacesMessage("", event.getFile()
					.getFileName() + " foi carregada."));

		} catch (Exception ex) {
			fc.addMessage("formcaddesap", new FacesMessage("", event.getFile()
					.getFileName() + " não foi carregada."));
		}
	}

	public String cadastrar() {

		FacesContext fc = FacesContext.getCurrentInstance();
		try {

			if (desaparecido.getLatitude() == null) {
				System.out.println("nao cadastrou local desap...");
				fc.addMessage(
						"formCadEnderDesap",
						new FacesMessage("Atenção!",
								"Clique em Cadastrar Endereço e\ndepois em Finalizar Cadastro."));
				// Permanece na página até o endereco ser preenchido
				return null;
			}

			System.out.println("Dados do responsavel - Tipo e Email");
			System.out.println(responsavel.getTipo() + " "
					+ responsavel.getEmailAddress());

			new DescricaoJPA().gravarAtualizar(descricao);
			this.desaparecido.setDescricaoDesaparecido(descricao);
			this.desaparecido.setResponsavel(responsavel);

			if (desaparecido.getFoto() != null && desaparecido.getFoto() != "") {
				this.arquivo.gravar();
				System.out.println("foto salva no servidor");
			}

			if (procedimento.equals("editar")) {
				System.out.println("editano");
				editarDesaparecido(desaparecido);
			} else {
				new DesaparecidoJPA().gravarAtualizar(desaparecido);

				fc.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"SUCESSO!", desaparecido.getNome()
										+ " cadastrado com sucesso."));
				ExternalContext ec = fc.getExternalContext();
				ec.getFlash().setKeepMessages(true);

				NavigationHandler nh = fc.getApplication()
						.getNavigationHandler();

				System.out.println("arquivo gravado: " + arquivo.getNome());
				System.out.println("id gravado::::::::::::::: "
						+ desaparecido.getId());

				listaDesaparecido.add(desaparecido);
				limpar();
				nh.handleNavigation(fc, null,
						"/admin/painelresp?faces-redirect=true");
			}

		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addSuccessMessage("Dados não foram armazenados!");
		}
		return null;
	}

	private void editarDesaparecido(Desaparecido obj) {
		listaDesaparecido.remove(obj);
		procedimento = "";
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			new DesaparecidoJPA().gravarAtualizar(obj);
			fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"SUCESSO!", obj.getNome() + " atualizado com sucesso."));
			ExternalContext ec = fc.getExternalContext();
			ec.getFlash().setKeepMessages(true);

			NavigationHandler nh = fc.getApplication().getNavigationHandler();

			System.out.println("id gravado::::::::::::::: "
					+ desaparecido.getId());

			listaDesaparecido.add(obj);
			limpar();
			nh.handleNavigation(fc, null,
					"/admin/painelresp?faces-redirect=true");
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addSuccessMessage("Dados não foram armazenados!");
		}
	}

	public void setActionSituacao(String action) {
		if (action.equals("true")) {
			desaparecido.setEncontrado(true);
			System.out.println("----Situacao foi Alterada----");
		} else {
			desaparecido.setEncontrado(false);
			System.out.println("nao setou");
		}
	}

	public void setActionProcedimento(String action) {
		procedimento = action;
	}

	public void altararSituacao() {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {

			System.out.println("Dados do responsavel - Tipo e Email");
			System.out.println(responsavel.getTipo() + " "
					+ responsavel.getEmailAddress());
			System.out.println("desap: " + desaparecido.getApelido());

			new DesaparecidoJPA().editar(desaparecido);
			encontrado.setDesaparecido(desaparecido);
			new EncontradoJPA().gravar(encontrado);

			fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"SUCESSO!", desaparecido.getNome()
							+ " alterado com sucesso."));
			ExternalContext ec = fc.getExternalContext();
			// mantem a mensagem para ser usada em outra pagina com flashScoped
			ec.getFlash().setKeepMessages(true);

			NavigationHandler nh = fc.getApplication().getNavigationHandler();
			// navega para outra pagina com a mensagem
			nh.handleNavigation(fc, null,
					"/admin/painelresp?faces-redirect=true");

			System.out.println("encontrado gravado: " + encontrado.getData());

			inicializar();

		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addSuccessMessage("Alteração não foi realizada!");
		}
	}

	// Exclui um cadastro de desaparecido.
	public void excluir() {
		try {
			new Encontrado().setDesaparecido(desaparecido);
			new DesaparecidoJPA().excluir(desaparecido);
			this.listaDesaparecido.remove(desaparecido);
			FacesUtil.addSuccessMessageWithDetail(null, "SUCESSO","A ocorrência de "
					+ desaparecido.getNome() + " foi excluída com sucesso.");
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil
					.addErrorMessage("Desculpe, houve algum erro no servidor, tente novamente mais tarde.");
		}
	}

	// Cancela um cadastro de desaparecido ou encontrado
	public String cancelar() {
		limpar();
		if (desaparecido.isEncontrado()) {
			setActionSituacao("");
		}
		return "/admin/painelresp?faces-redirect=true";
	}

	// Para trabalhar com a navegação entre as TABs
	public String onFlowProcess(FlowEvent event) {
		if (skip) {
			skip = false; // reseta, caso clique em voltar
			return "confirm";
		} else {
			return event.getNewStep();
		}
	}

}
