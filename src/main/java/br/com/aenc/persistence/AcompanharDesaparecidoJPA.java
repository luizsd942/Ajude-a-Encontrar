package br.com.aenc.persistence;

import java.io.Serializable;
import java.util.List;

import br.com.aenc.entity.Desaparecido;
import br.com.aenc.entity.PatrulDesapLocaliza;
import br.com.aenc.entity.Usuario;
import br.com.aenc.util.persistence.HibernateUtil;

public class AcompanharDesaparecidoJPA extends
		GenericJPA<PatrulDesapLocaliza, Integer> implements Serializable {

	private static final long serialVersionUID = 1L;

	public AcompanharDesaparecidoJPA() {
		super(new PatrulDesapLocaliza());
	}

	public void excluirAssociacao(Desaparecido desaparecido, Usuario patrulheiro)
			throws Exception {
		session = HibernateUtil.getSessionFactory().openSession();
		tranction = session.beginTransaction();
		query = session
				.createQuery("DELETE FROM PatrulDesapLocaliza p "
						+ " WHERE p.desaparecido = :pDesap and p.patrulheiro = :pPatrul");
		query.setParameter("pDesap", desaparecido).setParameter("pPatrul",
				patrulheiro).executeUpdate();
		tranction.commit();
		session.close();
	}
	@SuppressWarnings("unchecked")
	public List<PatrulDesapLocaliza> selecionarAssociacao(Desaparecido desaparecido, Usuario patrulheiro)
			throws Exception {
		session = HibernateUtil.getSessionFactory().openSession();
		tranction = session.beginTransaction();
		query = session
				.createQuery("select p FROM PatrulDesapLocaliza p "
						+ " WHERE p.desaparecido = :pDesap and p.patrulheiro = :pPatrul");
		query.setParameter("pDesap", desaparecido).setParameter("pPatrul",
				patrulheiro);
		List<PatrulDesapLocaliza> valor = query.list();
		tranction.commit();
		session.close();
		return valor;
	}

}
