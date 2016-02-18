package br.com.aenc.entity;

/***********************************************************************
 * Module:  Desaparecido.java
 * Author:  Luiz
 * Purpose: Defines the Class Desaparecido
 ***********************************************************************/

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * Essa classe guarda as informações do desaparecido
 */
@Entity
@Table(name = "desaparecido")
public class Desaparecido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "nome", length = 150)
	private String nome;
	@Column(name = "apelido", length = 30)
	private String apelido;
	@Temporal(TemporalType.DATE)
	@Column(name = "data_nasc")
	private Date dtNascimento;
	@Column(name = "sexo")
	private String sexo;
	@Temporal(TemporalType.DATE)
	@Column(name = "data_desap")
	private Date dtDesap;
	@Column(name = "ult_local")
	private String ultimoLocal;
	@Column(name = "roupa", length = 200)
	private String roupaUsada;
	@Lob
	@Column(name = "motivo")
	private String motivoDesap;
	@Column(name = "loc_freq")
	private String locaisFrequenta;
	@Column(name = "boletim", length = 50)
	private String numBO;
	@Column(name = "foto")
	private String foto;
	@Column(length = 100)
	private String endereco;
	@Column(name = "latit", length = 30)
	private String latitude;
	@Column(name = "longi", length = 30)
	private String longitude;
	@Column
	private boolean isEncontrado;

	// Campo para o sistema Alertar sobre um Desaparecido proximo ao Patrulheiro
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "desaparecido", cascade = CascadeType.ALL)
	private List<PatrulDesapAlerta> patrulAlertaDesap;

	/*
	 * Campo para o Patrulheiro acompanhar um Desaparecido ou informar a sua
	 * localizacao na tabela assiciativa
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "desaparecido", cascade = CascadeType.ALL)
	private List<PatrulDesapLocaliza> patrulLocalizaDesap;

	/*
	 * Atualiza ou deleta os dados da descricao de um desaparecido em cascata
	 * (CascadeType.ALL). Remove os dados na tabela que nao possuem associacao
	 * com outra tabela, sao chamados de dados orfaos (orphanRemoval).
	 */
	@OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_descricao")
	private DescricaoDesaparecido descricaoDesaparecido;

	// campo bidirecional - delete cascade
	@OneToOne(mappedBy = "desaparecido", cascade = CascadeType.ALL)
	Encontrado encontrado;

	@ManyToOne
	@JoinColumn(name = "id_responsavel")
	private Usuario responsavel;

	@Transient
	private Double distancia;

	public Desaparecido() {
		super();
	}

	public Desaparecido(int id, String nome, String apelido, Date dtNascimento,
			String sexo, Date dtDesap, String ultimoLocal, String roupaUsada,
			String motivoDesap, String locaisFrequenta, String numBO,
			String foto, String latitude, String longitude) {
		super();
		this.id = id;
		this.nome = nome;
		this.apelido = apelido;
		this.dtNascimento = dtNascimento;
		this.sexo = sexo;
		this.dtDesap = dtDesap;
		this.ultimoLocal = ultimoLocal;
		this.roupaUsada = roupaUsada;
		this.motivoDesap = motivoDesap;
		this.locaisFrequenta = locaisFrequenta;
		this.numBO = numBO;
		this.foto = foto;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Desaparecido(String nome, String apelido, String ultimoLocal,
			String latitude, String longitude, String foto) {
		super();
		this.nome = nome;
		this.apelido = apelido;
		this.ultimoLocal = ultimoLocal;
		this.latitude = latitude;
		this.longitude = longitude;
		this.foto = foto;
	}

	public Double getDistancia() {
		// setDistancia(Double.valueOf(String.format(Locale.US, "%.2f",
		// Math.floor(distancia))));
		return distancia;
	}

	public void setDistancia(Double distancia) {
		this.distancia = distancia;
	}
	
	public String getExibeDistancia(){
		if(getDistancia()!=null){
			DecimalFormat df = new DecimalFormat("0.###");
			String format = df.format(getDistancia());
			return format + " km";
		}
		return null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isEncontrado() {
		return isEncontrado;
	}

	public void setEncontrado(boolean isEncontrado) {
		this.isEncontrado = isEncontrado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public Date getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Date getDtDesap() {
		return dtDesap;
	}

	public void setDtDesap(Date dtDesap) {
		this.dtDesap = dtDesap;
	}

	public String getUltimoLocal() {
		return ultimoLocal;
	}

	public void setUltimoLocal(String ultimoLocal) {
		this.ultimoLocal = ultimoLocal;
	}

	public String getRoupaUsada() {
		return roupaUsada;
	}

	public void setRoupaUsada(String roupaUsada) {
		this.roupaUsada = roupaUsada;
	}

	public String getMotivoDesap() {
		return motivoDesap;
	}

	public void setMotivoDesap(String motivoDesap) {
		this.motivoDesap = motivoDesap;
	}

	public String getLocaisFrequenta() {
		return locaisFrequenta;
	}

	public void setLocaisFrequenta(String locaisFrequenta) {
		this.locaisFrequenta = locaisFrequenta;
	}

	public String getNumBO() {
		return numBO;
	}

	public void setNumBO(String numBO) {
		this.numBO = numBO;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public DescricaoDesaparecido getDescricaoDesaparecido() {
		return descricaoDesaparecido;
	}

	public void setDescricaoDesaparecido(
			DescricaoDesaparecido descricaoDesaparecido) {
		this.descricaoDesaparecido = descricaoDesaparecido;
	}

	public List<PatrulDesapAlerta> getPatrulAlertaDesap() {
		return patrulAlertaDesap;
	}

	public void setPatrulAlertaDesap(List<PatrulDesapAlerta> patrulAlerta) {
		this.patrulAlertaDesap = patrulAlerta;
	}

	public List<PatrulDesapLocaliza> getPatrulLocalizaDesap() {
		return patrulLocalizaDesap;
	}

	public void setPatrulLocalizaDesap(List<PatrulDesapLocaliza> patrulLocaliza) {
		this.patrulLocalizaDesap = patrulLocaliza;
	}

	public Usuario getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Usuario responsavel) {
		this.responsavel = responsavel;
	}

	public Encontrado getEncontrado() {
		return encontrado;
	}

	public void setEncontrado(Encontrado encontrado) {
		this.encontrado = encontrado;
	}

	public String getSituacaoDesap() {
		if (isEncontrado()) {
			return "ENCONTRADO(A)";
		}
		return "DESAPARECIDO(A)";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Desaparecido other = (Desaparecido) obj;
		if (id != other.id)
			return false;
		return true;
	}

}