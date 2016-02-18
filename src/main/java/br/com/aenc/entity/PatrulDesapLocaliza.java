package br.com.aenc.entity;

/***********************************************************************
 * Module:  Localiza.java
 * Author:  Luiz
 * Purpose: Defines the Class Localiza
 ***********************************************************************/

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "patrul_desap_localiza")
public class PatrulDesapLocaliza implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "latit", length = 30)
	private String latitude;
	@Column(name = "longi", length = 30)
	private String longitude;
	@Column(name = "endereco")
	private String endereco;
	@Lob
	@Column(name = "procedimento")
	private String procedimento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_desap_loca")
	private Desaparecido desaparecido;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_patrul_loca")
	private Usuario patrulheiro;

	public PatrulDesapLocaliza(int id, String latitude, String longitude, String endereco,
			String procedimento) {
		super();
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
		this.endereco = endereco;
		this.procedimento = procedimento;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public PatrulDesapLocaliza() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getProcedimento() {
		return procedimento;
	}

	public void setProcedimento(String procedimento) {
		this.procedimento = procedimento;
	}

	public Desaparecido getDesaparecido() {
		return desaparecido;
	}

	public void setDesaparecido(Desaparecido desaparecido) {
		this.desaparecido = desaparecido;
	}

	public Usuario getPatrulheiro() {
		return patrulheiro;
	}

	public void setPatrulheiro(Usuario patrulheiro) {
		this.patrulheiro = patrulheiro;
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
		PatrulDesapLocaliza other = (PatrulDesapLocaliza) obj;
		if (id != other.id)
			return false;
		return true;
	}

}