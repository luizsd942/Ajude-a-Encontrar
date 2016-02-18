package br.com.aenc.managerbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;

import br.com.aenc.entity.Desaparecido;
import br.com.aenc.entity.DescricaoDesaparecido;
import br.com.aenc.entity.Usuario;
import br.com.aenc.persistence.DesaparecidoJPA;
import br.com.aenc.util.jsf.FacesUtil;

//Sem uso - nao funcionou em painel de responsavel.
@ManagedBean(name = "responDesapBean")
@ViewScoped
public class ResponsavelDesaparecidoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Desaparecido> listaDesaparecido = new ArrayList<>();
	private Desaparecido desapSelecionado;
	private DescricaoDesaparecido descricao;

	// Pega o usuário logado
	@ManagedProperty(value = "#{uBean.usuario}")
	private Usuario responsavel;
	
	public ResponsavelDesaparecidoBean() {
		desapSelecionado = new Desaparecido();
		descricao = new DescricaoDesaparecido();
	}

	@PostConstruct
	public void inicializar() {
		System.out.println("Fui iniciado por... " + responsavel.getName());

		try {
			listaDesaparecido = new DesaparecidoJPA()
					.buscarDesaparecidosPorIdResponsavel2(responsavel);
		} catch (Exception e) {
			System.out.println("Não foi possível resgatar os dados");
			e.printStackTrace();
		}
	}

	public void excluir(ActionEvent event) {
		try {
			new DesaparecidoJPA().excluir(desapSelecionado);
			this.listaDesaparecido.remove(desapSelecionado);
			FacesUtil
					.addSuccessMessage("A ocorrência de "
							+ desapSelecionado.getNome()
							+ " foi excluída com sucesso.");
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil
					.addErrorMessage("Desculpe, houve algum erro no servidor, tente novamente mais tarde.");
		}
	}

	public List<Desaparecido> getListaDesaparecido() {
		return listaDesaparecido;
	}

	public void setListaDesaparecido(List<Desaparecido> listaDesaparecido) {
		this.listaDesaparecido = listaDesaparecido;
	}

	public Desaparecido getDesapSelecionado() {
		return desapSelecionado;
	}

	public void setDesapSelecionado(Desaparecido desapSelecionado) {
		this.desapSelecionado = desapSelecionado;
	}

	public Usuario getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Usuario responsavel) {
		this.responsavel = responsavel;
	}

	public DescricaoDesaparecido getDescricao() {
		return descricao;
	}

	public void setDescricao(DescricaoDesaparecido descricao) {
		this.descricao = descricao;
	}

}
