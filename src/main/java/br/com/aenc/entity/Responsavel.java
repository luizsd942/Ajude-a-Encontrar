package br.com.aenc.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/***********************************************************************
 * Module:  Responsavel.java
 * Author:  Luiz
 * Purpose: Defines the Class Responsavel
 ***********************************************************************/

/**
 * Essa classe armazena informacoes do responsavel pelo desaparecido.
 */
@Entity
@Table(name = "responsavel")
@PrimaryKeyJoinColumn(name = "id")
public class Responsavel extends Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "ddd", length = 2)
	private Integer ddd;
	@Column(name = "telefone", length = 12)
	private String telefone;
	@Column(name = "endereco", length = 100)
	private String cep;
	@Column(name = "latit", length = 30)
	private String latitude;
	@Column(name = "longi", length = 30)
	private String longitude;
	@Column(name = "foto")
	private String foto;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "responsavel")
	private List<Desaparecido> desaparecido;

	public Responsavel(Integer ddd, String telefone, String cep,
			String latitude, String longitude, String foto) {
		super();
		this.ddd = ddd;
		this.telefone = telefone;
		this.cep = cep;
		this.latitude = latitude;
		this.longitude = longitude;
		this.foto = foto;
	}

	public Responsavel() {
		super();
	}

	public Integer getDdd() {
		return ddd;
	}

	public void setDdd(Integer ddd) {
		this.ddd = ddd;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
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

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public List<Desaparecido> getDesaparecido() {
		return desaparecido;
	}

	public void setDesaparecido(List<Desaparecido> desaparecido) {
		this.desaparecido = desaparecido;
	}

}