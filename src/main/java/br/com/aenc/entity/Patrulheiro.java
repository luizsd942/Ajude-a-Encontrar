package br.com.aenc.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/***********************************************************************
 * Module: Patrulheiro.java Author: Luiz Purpose: Defines the Class Patrulheiro
 ***********************************************************************/
@Entity
@Table(name = "patrulheiro")
@PrimaryKeyJoinColumn(name = "id")
public class Patrulheiro extends Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "endereco", length = 100)
	private String cep;
	@Column(name = "latit", length = 30)
	private String latitude;
	@Column(name = "longi", length = 30)
	private String longitude;

	// Campo para Alertar sobre um desaparecido
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "patrulheiro", cascade = CascadeType.ALL)
	private List<PatrulDesapAlerta> patrulAlertaDesap;

	// Campo na tabela Localiza um desaparecido
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "patrulheiro", cascade=CascadeType.ALL)
	private List<PatrulDesapLocaliza> patrulLocalizaDesap;

	public Patrulheiro(String endereco, String latitude, String longitude) {
		super();
		this.cep = endereco;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Patrulheiro() {
		super();
	}

	public String getEndereco() {
		return cep;
	}

	public void setEndereco(String endereco) {
		this.cep = endereco;
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

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public List<PatrulDesapAlerta> getPatrulAlertaDesap() {
		return patrulAlertaDesap;
	}

	public void setPatrulAlertaDesap(List<PatrulDesapAlerta> patrulAlertaDesap) {
		this.patrulAlertaDesap = patrulAlertaDesap;
	}

	public List<PatrulDesapLocaliza> getPatrulLocalizaDesap() {
		return patrulLocalizaDesap;
	}

	public void setPatrulLocalizaDesap(
			List<PatrulDesapLocaliza> patrulLocalizaDesap) {
		this.patrulLocalizaDesap = patrulLocalizaDesap;
	}

}