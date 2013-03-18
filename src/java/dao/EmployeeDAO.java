package dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.HibernateException;

import bean.Employee;

public interface EmployeeDAO extends GenericDAO<Employee, BigDecimal> {	
	public void saveOrUpdateEmployee(Employee employee) throws HibernateException;
	public List<Employee> getAllEmployeesForTable() throws HibernateException;
        public List<Employee> getAllEmployees() throws HibernateException;
        public Employee findEmployeeByName(String firstname, String surname) throws HibernateException;
        public void deleteEmployee(Employee employee) throws HibernateException;
}
