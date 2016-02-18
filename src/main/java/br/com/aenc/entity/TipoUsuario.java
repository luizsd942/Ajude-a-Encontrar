package br.com.aenc.entity;

/***********************************************************************
 * Module: TipoUsuario.java Author: Luiz Purpose: Defines the Class TipoUsuario
 ***********************************************************************/

public enum TipoUsuario {
	RESPONSAVEL("Responsavel"), PATRULHEIRO("Patrulheiro");

	private String descricao;

	TipoUsuario(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}