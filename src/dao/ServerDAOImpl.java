package dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.inject.Named;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import models.Server;

@Named
public class ServerDAOImpl extends GenericDAOImpl<Server, BigDecimal> implements ServerDAO, Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public void addNewServer(Server server) throws HibernateException {
		HibernateUtil.beginTransaction();
		save(server);
		HibernateUtil.commitTransaction();
	}

	@Override
	public List<Server> getAllServer() throws HibernateException {
		String sql = "SELECT * FROM eas_server";
		
		HibernateUtil.beginTransaction();
		Query query = HibernateUtil.getSession().createSQLQuery(sql);
		List<Server> server = findMany(query);
		HibernateUtil.commitTransaction();
		
		return server;
	}

}
