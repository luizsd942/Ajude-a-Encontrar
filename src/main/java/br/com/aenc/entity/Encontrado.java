package br.com.aenc.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/***********************************************************************
 * Module: Encontrado.java Author: Luiz Purpose: Defines the Class Encontrado
 ***********************************************************************/
@Entity
@Table(name = "encontrado")
public class Encontrado implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Temporal(TemporalType.DATE)
	@Column(name = "data_encont")
	private Date data;
	@Lob
	@Column(name = "descricao")
	private String descricao;
	
	@OneToOne
	@JoinColumn(name = "id_desap")
	private Desaparecido desaparecido;

	public Encontrado(int id, Date data, String descricao) {
		super();
		this.id = id;
		this.data = data;
		this.descricao = descricao;
	}

	public Encontrado() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Desaparecido getDesaparecido() {
		return desaparecido;
	}

	public void setDesaparecido(Desaparecido desaparecido) {
		this.desaparecido = desaparecido;
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
		Encontrado other = (Encontrado) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	

}