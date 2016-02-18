package br.com.aenc.persistence;

import java.io.Serializable;
import br.com.aenc.entity.Encontrado;

public class EncontradoJPA extends GenericJPA<Encontrado, Integer> implements Serializable{
	private static final long serialVersionUID = 1L;

	public EncontradoJPA() {
		super(new Encontrado());
	}
}
