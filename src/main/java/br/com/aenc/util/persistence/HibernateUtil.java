package br.com.aenc.util.persistence;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

@SuppressWarnings("deprecation")
public class HibernateUtil {

	private static SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			if (sessionFactory == null) {
				Configuration configuration = new Configuration()
						.configure(HibernateUtil.class
								.getResource("/config/hibernate.cfg.xml"));
				ServiceRegistryBuilder serviceRegistryBuilder = new ServiceRegistryBuilder();
				serviceRegistryBuilder.applySettings(configuration
						.getProperties());
				ServiceRegistry serviceRegistry = serviceRegistryBuilder.buildServiceRegistry();
				sessionFactory = configuration
						.buildSessionFactory(serviceRegistry);
			}
			return sessionFactory;
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		getSessionFactory().close();
	}
}
