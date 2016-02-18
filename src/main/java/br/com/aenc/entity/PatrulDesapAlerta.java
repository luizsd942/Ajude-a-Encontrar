/***********************************************************************
 * Module:  AlertaDesaparecido.java
 * Author:  Luiz
 * Purpose: Defines the Class AlertaDesaparecido
 * Classe para armazenar os alertas que já foram feitos para um
 * Patrulheiro sobro um desaparecido.
 ***********************************************************************/
package br.com.aenc.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="patrul_desap_alerta")
public class PatrulDesapAlerta implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_sistema", nullable=false)
	private Date dataSistema;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_desap_aler")
	private Desaparecido desaparecido;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_patrul_aler")
	private Usuario patrulheiro;
	
	public PatrulDesapAlerta() {
		super();
	}

	public PatrulDesapAlerta(int id, Date dataSistema) {
		super();
		this.id = id;
		this.dataSistema = dataSistema;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDataSistema() {
		return dataSistema;
	}

	public void setDataSistema(Date dataSistema) {
		this.dataSistema = dataSistema;
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

	public Double calcularRaio() {
		// TODO: implement
		return null;
	}

}