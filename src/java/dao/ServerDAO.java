package dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.HibernateException;

import bean.Server;

public interface ServerDAO extends GenericDAO<Server, BigDecimal>{
	public void saveOrUpdateServer(Server server) throws HibernateException;
        public void deleteServer(Server server) throws HibernateException;
        public Server findServerByName(String name) throws HibernateException;
	public List<Server> getAllServer() throws HibernateException;
        public List<Server> getAllServerForTable() throws HibernateException;
}
