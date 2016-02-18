package br.com.aenc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/***********************************************************************
 * Module:  DescricaoDesaparecido.java
 * Author:  Luiz
 * Purpose: Defines the Class DescricaoDesaparecido
 ***********************************************************************/

/**
 * Essa classe armazena descrições do desaparecido, como: cor dos olhos, cabelo,
 * etc.
 */
@Entity
@Table(name = "desc_desaparecido")
public class DescricaoDesaparecido implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "cor_olhos", length=50)
	private String corOlhos;
	@Column(name = "cor_cabelo", length=50)
	private String corCabelo;
	@Column(name = "raca", length=50)
	private String raca;
	@Column(name = "altura")
	private Double altura;
	@Column(name = "peso")
	private Double peso;
	@Lob
	@Column(name = "descricao")
	private String descricao;
	@Column(name = "deficiencia")
	private boolean possuiDefic;
	@Column(name = "tipo_defic", length=100)
	private String tipoDefic;


	public DescricaoDesaparecido(int id, String corOlhos, String corCabelo,
			String raca, Double altura, Double peso, String descricao,
			boolean possuiDefic, String tipoDefic) {
		super();
		this.id = id;
		this.corOlhos = corOlhos;
		this.corCabelo = corCabelo;
		this.raca = raca;
		this.altura = altura;
		this.peso = peso;
		this.descricao = descricao;
		this.possuiDefic = possuiDefic;
		this.tipoDefic = tipoDefic;
	}

	public DescricaoDesaparecido() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCorOlhos() {
		return corOlhos;
	}

	public void setCorOlhos(String corOlhos) {
		this.corOlhos = corOlhos;
	}

	public String getCorCabelo() {
		return corCabelo;
	}

	public void setCorCabelo(String corCabelo) {
		this.corCabelo = corCabelo;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	public Double getAltura() {
		return altura;
	}

	public void setAltura(Double altura) {
		this.altura = altura;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isPossuiDefic() {
		return possuiDefic;
	}

	public void setPossuiDefic(boolean possuiDefic) {
		this.possuiDefic = possuiDefic;
	}

	public String getTipoDefic() {
		return tipoDefic;
	}

	public void setTipoDefic(String tipoDefic) {
		this.tipoDefic = tipoDefic;
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
		DescricaoDesaparecido other = (DescricaoDesaparecido) obj;
		if (id != other.id)
			return false;
		return true;
	}

}