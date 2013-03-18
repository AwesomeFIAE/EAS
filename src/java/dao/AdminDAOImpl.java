package dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.inject.Named;

import bean.Admin;


import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;


@Named
public class AdminDAOImpl extends GenericDAOImpl<Admin, BigDecimal> implements AdminDAO, Serializable{

	private static final long serialVersionUID = 1L;


	public AdminDAOImpl(){}
	
	@Override
	public void addNewAdmin(Admin admin) throws HibernateException {
			HibernateUtil.beginTransaction();
			save(admin);
			HibernateUtil.commitTransaction();
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllInformation(String email) {
		List<String> adminInformation = null;
		Session hibernateSession = getSession();
		
		HibernateUtil.beginTransaction();
		adminInformation = hibernateSession.createCriteria(Admin.class).add(Restrictions.like("email", email)).list();
		HibernateUtil.commitTransaction();
		
		return adminInformation;
	}
	
	@Override
	public Admin login(String username, String password) throws HibernateException {
		String sql = "FROM Admin WHERE username = :username AND userPassword = :password";		   
		Admin admin = null;
		
		
		HibernateUtil.beginTransaction();
		Query query = HibernateUtil.getSession().createQuery(sql).setParameter("username", username).setParameter("password", password);
		admin = findOne(query);
		HibernateUtil.commitTransaction();
		
		return admin;
	}
	
}
