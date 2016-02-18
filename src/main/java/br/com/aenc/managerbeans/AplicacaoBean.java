package br.com.aenc.managerbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.com.aenc.entity.Desaparecido;
import br.com.aenc.entity.DescricaoDesaparecido;
import br.com.aenc.entity.PatrulDesapLocaliza;
import br.com.aenc.entity.Patrulheiro;
import br.com.aenc.entity.Usuario;
import br.com.aenc.geolocaliza.GeoCoordinate;
import br.com.aenc.persistence.DesaparecidoJPA;
import br.com.aenc.persistence.PatrulheiroJPA;
import br.com.aenc.persistence.UsuarioJPA;
import br.com.aenc.util.jsf.FacesUtil;

@ManagedBean(name = "api")
@SessionScoped
public class AplicacaoBean implements Serializable{
	private static final long serialVersionUID = 1L;

	// Pega o usuário logado
	@ManagedProperty(value = "#{uBean.usuario}")
	private Usuario usuario;
	
//	@ManagedProperty(value = "#{patrulBean}")
//	private PatrulheiroBean patrulheiroBean; 

	private String lat;
	private String lng;
	private String local;
	private GeoCoordinate coordenadas;
	private Double radius = 50.000;
	private Desaparecido desaparecido;
	private DescricaoDesaparecido descricao;
	private List<Desaparecido> lista;
	private List<Desaparecido> resultados;
	private PatrulDesapLocaliza assocLocaliza;

	@PostConstruct
	public void inicializar() {
		try {
			limpar();
			lista = new DesaparecidoJPA().listar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void limpar() {
		desaparecido = new Desaparecido();
		descricao = new DescricaoDesaparecido();
		lista = new ArrayList<Desaparecido>();
		resultados = new ArrayList<Desaparecido>();
		assocLocaliza = new PatrulDesapLocaliza();
	}

	public Usuario buscarPatrulheiroPorEmail() {
		Usuario usu = null;
		try {
			usu = new UsuarioJPA().buscarPorLogin(usuario.getEmailAddress());
		} catch (Exception e) {
			System.out.println("Problemas ao buscar o usuario por email: "
					+ e.getMessage());
			e.printStackTrace();
		}
		return usu;
	}

	public Integer getIdadeAtual() {
		Date nasc = null;
		try{
			nasc = desaparecido.getDtNascimento();
			return FacesUtil.calculaIdade(nasc);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Integer getIdadeDesap() {
		Date nasc = null;
		Date desap = null;
		try{
			nasc = desaparecido.getDtNascimento();
			desap = desaparecido.getDtDesap();
			return FacesUtil.calculaIdadeDesapareceu(nasc, desap);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public PatrulDesapLocaliza getAssocLocaliza() {
		return assocLocaliza;
	}

	public void setAssocLocaliza(PatrulDesapLocaliza assocLocaliza) {
		this.assocLocaliza = assocLocaliza;
	}

	public Double getRadius() {
		return radius;
	}

	public void setRadius(Double radius) {
		this.radius = radius * 1.000;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public List<Desaparecido> getLista() {
		return lista;
	}

	public void setLista(List<Desaparecido> lista) {
		this.lista = lista;
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

	public List<Desaparecido> getResultados() {
		return resultados;
	}

	public void setResultados(List<Desaparecido> resultados) {
		this.resultados = resultados;
	}

	public GeoCoordinate getCoordenadas() {
		return coordenadas;
	}

	public void setCoordenadas(GeoCoordinate coordenadas) {
		this.coordenadas = coordenadas;
	}

	/*
	 * Faz a busca por uma ocorrência de desaparecido proximo ao local informado
	 * pelo usuario. o usuario podera' informar a distancia de uma ocorrencia.
	 */
	public String pesquisar() {
		// Seta os valores informado pelo usuario
		setCoordenadas(new GeoCoordinate(new Double(lat), new Double(lng)));
		GeoCoordinate coordenadas2;
		int total = 0;

		for (Desaparecido desap : lista) {
			coordenadas2 = new GeoCoordinate(new Double(desap.getLatitude()),
					new Double(desap.getLongitude()));
			desap.setDistancia(coordenadas.distanceInKm(coordenadas2));

			if (desap.getDistancia() <= (getRadius())) {
				resultados.add(desap);
				total++;
			}
		}

		if (total == 0) {
			FacesUtil
					.addErrorMessage("Não há nenhuma ocorrência de desaparecidos \npróximo ao local informado!");

			return null;
		}
		setLista(resultados);
		return null;
	}

	public String acompanharCaso() {
		Patrulheiro patrul = new Patrulheiro();
		if (usuario.getEmailAddress() != null
				&& usuario.getEmailAddress() != "") {

			try {
				patrul = (Patrulheiro) buscarPatrulheiroPorEmail();
				assocLocaliza = new PatrulDesapLocaliza();
				
				assocLocaliza.setDesaparecido(desaparecido);
				assocLocaliza.setPatrulheiro(patrul);
				List<PatrulDesapLocaliza> acompanhamentos = Arrays
						.asList(assocLocaliza);
				patrul.setPatrulLocalizaDesap(acompanhamentos);

				new PatrulheiroJPA().gravarAtualizar(patrul);
//				patrulheiroBean.inicializar();
				FacesUtil
						.addSuccessMessageWithDetail("frmDialogo",
								"Dados enviados",
								"Agora você está acompanhando esse caso! Verifique no painel de usuário");
			} catch (Exception e) {
				e.printStackTrace();
				// Não é um Patrulheiro
				FacesUtil.addErrorMessageWithDetail("frmDialogo", "Atenção",
						"Você deve ser um Patrulheiro para acompanhar o caso!");
			}

		} else {
			// Nao está logado
			FacesUtil.addErrorMessageWithDetail("frmDialogo", "Atenção",
					"Logue-se como Patrulheiro para acompanhar o caso!");
		}
		return null;
	}
}
