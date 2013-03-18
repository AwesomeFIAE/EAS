package controller;

import dao.EmployeeDAO;
import dao.HibernateUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import bean.Employee;
import bean.EmployeeType;
import org.hibernate.HibernateException;
import org.primefaces.context.RequestContext;

@Named
@SessionScoped
public class EmployeeService implements Serializable {

    private Employee employee = new Employee();
    @Inject
    private EmployeeDAO employeeDAO;
    private List<Employee> employees;
    private List<Employee> dataTableList;
    private List<String> columns;
    private List<SelectItem> options;
    private List<SelectItem> employeeOption;
    private String chosenEmployee;

    public EmployeeService() {
        columns = new ArrayList<String>();
        columns.add("Bezeichnung");
        columns.add("Vorname");
        columns.add("Nachname");
        columns.add("Arbeitsgruppe");
        columns.add("Dienstl. Handynummer");
        columns.add("Private Handynummer");
        columns.add("Telefonnummer");
        columns.add("Nst");

        employees = new ArrayList<Employee>();
    }

    public void startupList() {
        dataTableList = employeeDAO.getAllEmployeesForTable();
        employees = employeeDAO.getAllEmployees();
    }

    public void updateList() {
        dataTableList.clear();
        dataTableList = employeeDAO.getAllEmployeesForTable();
        employees.clear();
        employees = employeeDAO.getAllEmployees();
    }

    public void updateEmployee() {
        employeeDAO.saveOrUpdateEmployee(employee);
        updateList();
        clearEmployeeBean();
    }

    public void deleteEmployee() {
        // 0 = Nachname
        // 1 = Vorname
        String[] splittedName = chosenEmployee.split(",");
        String surname = splittedName[0].trim();
        String firstname = splittedName[1].trim();
        
        if (useDatabaseQuery()) {
            employee = employeeDAO.findEmployeeByName(firstname, surname);
        } else {
            setEmployeeBeanByName(surname, firstname);
        }

        employeeDAO.deleteEmployee(employee);
        updateList();
        clearEmployeeBean();
    }

    public void editEmployeeInformation() {
        // 0 = Nachname
        // 1 = Vorname
        String[] splittedName = chosenEmployee.split(",");
        String surname = splittedName[0].trim();
        String firstname = splittedName[1].trim();

        if (useDatabaseQuery()) {
            employee = employeeDAO.findEmployeeByName(firstname, surname);
        } else {
            setEmployeeBeanByName(surname, firstname);
        }

        RequestContext.getCurrentInstance().execute("editEmployee.show()");
    }

    public void addToDb() {

        try {
            employee.setId(null);

            FacesContext context;

            context = FacesContext.getCurrentInstance();

            String info = employee.getType() + ": " + employee.getSurname() + ", "
                    + employee.getFirstname() + "\n wurde erfolgreich hinzugefügt.";

            employeeDAO.saveOrUpdateEmployee(employee);
            updateList();

            context.addMessage("addInfo", new FacesMessage(FacesMessage.SEVERITY_INFO, info, ""));

        } catch (HibernateException ex) {
            System.err.println(ex);
            HibernateUtil.rollbackTransaction();

            String info = "Es konnte keine neue Person hinzugefügt werden.\nBitte zeigen sie dies dem Admin: " + ex.toString();

            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("addInfo", new FacesMessage(FacesMessage.SEVERITY_FATAL, info, ""));
        }
    }

    public List<SelectItem> getOptions() {
        this.options = new ArrayList<SelectItem>();

        for (int i = 0; i < EmployeeType.values().length; i++) {
            switch (EmployeeType.values()[i]) {
                case EMPLOYEE:
                    this.options.add(new SelectItem("Mitarbeiter"));
                    break;
                case CHIEF:
                    this.options.add(new SelectItem("Chef"));
                    break;
                case EXTERNAL_EMPLOYEE:
                    this.options.add(new SelectItem("Externer Mitarbeiter"));
                    break;
                case OTHER:
                    this.options.add(new SelectItem("Sonstige"));
                    break;
            }

        }

        return options;
    }

    private void setEmployeeBeanByName(String surname, String firstname) {
        for (Employee e : employees) {
            if (e.getSurname().equals(surname) && e.getFirstname().equals(firstname)) {
                employee.setFirstname(e.getFirstname());
                employee.setId(e.getId());
                employee.setOfficialHandyNumber(e.getOfficialHandyNumber());
                employee.setPrivateHandyNumber(e.getPrivateHandyNumber());
                employee.setSubsidiary(e.getSubsidiary());
                employee.setSurname(e.getSurname());
                employee.setTelephoneNumberAtHome(e.getTelephoneNumberAtHome());
                employee.setType(e.getType());
                employee.setWorkgroup(e.getWorkgroup());
                break;
            }
        }
    }

    private boolean useDatabaseQuery() {
        if (employees.size() > 1000) {
            return true;
        }

        return false;
    }

    private void clearEmployeeBean() {
        employee.setFirstname(null);
        employee.setId(null);
        employee.setOfficialHandyNumber(null);
        employee.setPrivateHandyNumber(null);
        employee.setSubsidiary(null);
        employee.setSurname(null);
        employee.setTelephoneNumberAtHome(null);
        employee.setType(null);
        employee.setWorkgroup(null);
    }

    public List<SelectItem> getEmployeeOption() {
        employeeOption = new ArrayList<SelectItem>();

        for (Employee emp : employees) {
            employeeOption.add(new SelectItem(emp.getSurname() + ", " + emp.getFirstname()));
        }

        return employeeOption;
    }

    public void setEmployeeOption(List<SelectItem> employeeOption) {
        this.employeeOption = employeeOption;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public EmployeeDAO getEmployeeDAO() {
        return employeeDAO;
    }

    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public void setOptions(List<SelectItem> options) {
        this.options = options;
    }

    public List<Employee> getDataTableList() {
        return dataTableList;
    }

    public void setDataTableList(List<Employee> dataTableList) {
        this.dataTableList = dataTableList;
    }

    public String getChosenEmployee() {
        return chosenEmployee;
    }

    public void setChosenEmployee(String chosenEmployee) {
        this.chosenEmployee = chosenEmployee;
    }
}
