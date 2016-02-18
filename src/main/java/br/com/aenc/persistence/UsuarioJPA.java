package br.com.aenc.persistence;

import java.io.Serializable;

import org.hibernate.criterion.Restrictions;

import br.com.aenc.entity.Usuario;
import br.com.aenc.util.persistence.HibernateUtil;

public class UsuarioJPA extends GenericJPA<Usuario, Integer> implements
		Serializable {

	private static final long serialVersionUID = 1L;

	public UsuarioJPA() {
		super(new Usuario());
	}

	public Usuario logar(String pemail, String senha) throws Exception {
		session = HibernateUtil.getSessionFactory().openSession();
		criteria = session.createCriteria(Usuario.class);
		criteria.add(Restrictions.and(Restrictions.eq("emailAddress", pemail),
				Restrictions.eq("senha", senha)));
		Usuario u = (Usuario) criteria.uniqueResult();
		session.close();
		return u;
	}

	public Usuario buscarPorLogin(String pemail) throws Exception {
		session = HibernateUtil.getSessionFactory().openSession();
		criteria = session.createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("emailAddress", pemail));
		Usuario user = (Usuario) criteria.uniqueResult();
		session.close();
		return user;
	}

}
