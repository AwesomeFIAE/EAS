package dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.inject.Named;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import models.Employee;

@Named
public class EmployeeDAOImpl extends GenericDAOImpl<Employee, BigDecimal> implements EmployeeDAO, Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public void addNewEmployee(Employee employee) throws HibernateException {
		HibernateUtil.beginTransaction();
		save(employee);
		HibernateUtil.commitTransaction();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getAllEmployees() throws HibernateException {
		String sql = "SELECT label, firstname, surname, workgroup, official_handy_nr, private_handy_nr, nr_at_home, subsidiary FROM eas_employee";
		
		HibernateUtil.beginTransaction();
		Query query = HibernateUtil.getSession().createSQLQuery(sql);
		List<Employee> employees = findMany(query);
		HibernateUtil.commitTransaction();
		
		return employees;
	}
}
