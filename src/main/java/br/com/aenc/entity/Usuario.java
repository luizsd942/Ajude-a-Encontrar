package br.com.aenc.entity;

/***********************************************************************
 * Module:  Usuario.java
 * Author:  Luiz
 * Purpose: Defines the Class Usuario
 ***********************************************************************/

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * Essa tabela armazena as informações dos usuários que ajudam a encontrar as
 * pessoas desaparecidas.
 */
@Entity
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Integer id;
	@Column(name = "nome", nullable = false, length = 250)
	private String name;
	@Column(name = "email", nullable = false, length = 60)
	private String emailAddress;
	@Column(nullable = false, length = 20)
	private String senha;
	@Column()
	private boolean newsSemanal;
	@Column()
	private boolean termos;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_usuario", length = 15)
	private TipoUsuario tipo;

	public Usuario() {
		super();
	}

	public Usuario(Integer id, String name, String emailAddress, String senha,
			boolean newsSemanal, boolean termos) {
		super();
		this.id = id;
		this.name = name;
		this.emailAddress = emailAddress;
		this.senha = senha;
		this.newsSemanal = newsSemanal;
		this.termos = termos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isNewsSemanal() {
		return newsSemanal;
	}

	public void setNewsSemanal(boolean newsSemanal) {
		this.newsSemanal = newsSemanal;
	}

	public TipoUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}
	
	public boolean isTermos() {
		return termos;
	}

	public void setTermos(boolean termos) {
		this.termos = termos;
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
		Usuario other = (Usuario) obj;
		if (id != other.id)
			return false;
		return true;
	}

}