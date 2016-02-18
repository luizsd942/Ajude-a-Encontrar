package br.com.aenc.persistence;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.aenc.util.persistence.HibernateUtil;

public abstract class GenericJPA<E, ID extends Serializable> implements InterfaceJPA<E, ID> {

	Session session;
	Transaction tranction;
	Query query;
	Criteria criteria;
	
	E objeto;
	
	public GenericJPA(E entity) {
		objeto = entity;
	}
	
	@Override
	public void gravarAtualizar(E obj) throws Exception {
		session = HibernateUtil.getSessionFactory().openSession();
		tranction = session.beginTransaction();
		session.saveOrUpdate(obj);
		tranction.commit();
		session.close();
	}
	@Override
	public void gravar(E obj) throws Exception {
		session = HibernateUtil.getSessionFactory().openSession();
		tranction = session.beginTransaction();
		session.save(obj);
		tranction.commit();
		session.close();
	}
	@Override
	public void editar(E obj) throws Exception {
		session = HibernateUtil.getSessionFactory().openSession();
		tranction = session.beginTransaction();
		session.update(obj);
		tranction.commit();
		session.close();
	}
	@Override
	public void excluir(E obj) throws Exception {
		session = HibernateUtil.getSessionFactory().openSession();
		tranction = session.beginTransaction();
		session.delete(obj);
		tranction.commit();
		session.close();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<E> listar() throws Exception {
		session = HibernateUtil.getSessionFactory().openSession();
		criteria = session.createCriteria(objeto.getClass());
		List<E> lista = criteria.list();
		session.close();
		return lista;
	}
	@SuppressWarnings("unchecked")
	@Override
	public E buscarPorCodigo(ID cod) throws Exception {
		session = HibernateUtil.getSessionFactory().openSession();
		E obj = (E) session.get(objeto.getClass(), cod);
		session.close();
		return obj;
	}
	
	
	
}
