package br.com.aenc.persistence;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import br.com.aenc.entity.Desaparecido;
import br.com.aenc.entity.Usuario;
import br.com.aenc.util.persistence.HibernateUtil;

public class DesaparecidoJPA extends GenericJPA<Desaparecido, Integer> {

	public DesaparecidoJPA() {
		super(new Desaparecido());
	}

	@SuppressWarnings("unchecked")
	public List<Desaparecido> buscarDesaparecidosPorIdResponsavel(
			int idResponsavel) throws Exception {
		session = HibernateUtil.getSessionFactory().openSession();
		criteria = session.createCriteria(Desaparecido.class);
		criteria.add(Restrictions.eq("responsavel", idResponsavel));
		List<Desaparecido> lista = criteria.list();
		session.close();
		return lista;
	}

	@SuppressWarnings("unchecked")
	public List<Desaparecido> buscarDesaparecidosPorIdResponsavel2(
			Usuario responsavel) throws Exception {
		session = HibernateUtil.getSessionFactory().openSession();
		query = session
				.createQuery("from Desaparecido d where d.responsavel = :idResponsavel");
		query.setParameter("idResponsavel", responsavel);
		List<Desaparecido> lista = query.list();
		session.close();
		return lista;
	}

	@SuppressWarnings("unchecked")
	public List<Desaparecido> buscarDesaparecidosPorIdPatrulheiro(
			Usuario patrulheiro) {

		session = HibernateUtil.getSessionFactory().openSession();
		query = session
				.createQuery("select p.desaparecido from PatrulDesapLocaliza p "
						+ " left join p.desaparecido "
						+ " left join p.patrulheiro "
						+ " where p.patrulheiro = :idPatrulheiro");
		query.setParameter("idPatrulheiro", patrulheiro);
		List<Desaparecido> lista = query.list();
		session.close();
		return lista;
	}

}
