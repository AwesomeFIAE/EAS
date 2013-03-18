package dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.HibernateException;

import models.Server;

public interface ServerDAO extends GenericDAO<Server, BigDecimal>{
	public void addNewServer(Server server) throws HibernateException;
	public List<Server> getAllServer() throws HibernateException;
}
