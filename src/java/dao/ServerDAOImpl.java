package dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.inject.Named;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import bean.Server;

@Named
public class ServerDAOImpl extends GenericDAOImpl<Server, BigDecimal> implements ServerDAO, Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public void saveOrUpdateServer(Server server) throws HibernateException {
        HibernateUtil.beginTransaction();
        save(server);
        HibernateUtil.commitTransaction();
    }

    @Override
    public List<Server> getAllServer() throws HibernateException {
        String sql = "From Server S";

        HibernateUtil.beginTransaction();
        Query query = HibernateUtil.getSession().createQuery(sql);
        List<Server> server = findMany(query);
        HibernateUtil.commitTransaction();

        return server;
    }

    @Override
    public List<Server> getAllServerForTable() throws HibernateException {
        String sql = "SELECT server_name, admin_ip, customer_ip FROM eas_server";

        HibernateUtil.beginTransaction();
        Query query = HibernateUtil.getSession().createSQLQuery(sql);
        List<Server> server = findMany(query);
        HibernateUtil.commitTransaction();

        return server;
    }

    @Override
    public void deleteServer(Server server) throws HibernateException {
        HibernateUtil.beginTransaction();
        delete(server);
        HibernateUtil.commitTransaction();
    }

    @Override
    public Server findServerByName(String name) throws HibernateException {
        String sql = "FROM Server WHERE serverName = :servername";
        
        HibernateUtil.beginTransaction();
        Query query = HibernateUtil.getSession().createQuery(sql).setParameter("servername", name);
        Server server = findOne(query);
        HibernateUtil.commitTransaction();
        
        return server;
    }
}
