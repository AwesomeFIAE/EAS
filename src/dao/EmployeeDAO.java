package dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.HibernateException;

import models.Employee;

public interface EmployeeDAO extends GenericDAO<Employee, BigDecimal> {	
	public void addNewEmployee(Employee employee) throws HibernateException;
	public List<Employee> getAllEmployees() throws HibernateException;
}
