package br.com.aenc.persistence;

import java.io.Serializable;

import br.com.aenc.entity.Responsavel;

public class ResponsavelJPA extends GenericJPA<Responsavel, Integer> implements Serializable{

	private static final long serialVersionUID = 1L;

	public ResponsavelJPA() {
		super(new Responsavel());
	}

}