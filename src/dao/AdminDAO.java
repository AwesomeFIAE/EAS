package dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.HibernateException;

import adminarea.Admin;


public interface AdminDAO extends GenericDAO<Admin, BigDecimal> {
	
	public void addNewAdmin(Admin admin) throws HibernateException;
	public Admin login(String userName, String password) throws HibernateException;
	public List<String> getAllInformation(String email);
}
