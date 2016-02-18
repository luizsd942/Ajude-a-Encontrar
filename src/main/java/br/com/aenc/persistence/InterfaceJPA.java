package br.com.aenc.persistence;

import java.util.List;

public interface InterfaceJPA<E, ID> {

	void gravarAtualizar(E obj)throws Exception;
	void editar(E obj)throws Exception;
	void excluir(E obj)throws Exception;
	
	List<E> listar()throws Exception;
	E buscarPorCodigo(ID cod)throws Exception;
	void gravar(E obj) throws Exception;
	
}
