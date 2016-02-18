package br.com.aenc.persistence;

import java.io.Serializable;

import br.com.aenc.entity.Patrulheiro;

public class PatrulheiroJPA extends GenericJPA<Patrulheiro, Integer> implements Serializable{

	private static final long serialVersionUID = 1L;

	public PatrulheiroJPA() {
		super(new Patrulheiro());
	}

	
}
