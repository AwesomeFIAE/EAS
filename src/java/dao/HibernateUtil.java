package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {

	private static final SessionFactory sessionFactory;

	private HibernateUtil() {
	}

	static {
		final AnnotationConfiguration cfg = new AnnotationConfiguration();
		cfg.configure("/hibernate.cfg.xml");
		sessionFactory = cfg.buildSessionFactory();
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static Session getSession() {
		Session hibernateSession = sessionFactory.getCurrentSession();
		return hibernateSession;
	}
	
	public static Session beginTransaction() {
		Session hibernateSession = HibernateUtil.getSession();
		hibernateSession.beginTransaction();
		return hibernateSession;
	}
	
	public static void commitTransaction() {
		HibernateUtil.getSession().getTransaction().commit();
	}
	
	public static void rollbackTransaction() {
		HibernateUtil.getSession().getTransaction().rollback();
	}
	
	public static void closeSession() {
		HibernateUtil.getSession().close();
	}
}