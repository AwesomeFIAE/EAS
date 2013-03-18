
import dao.EmployeeDAO;
import dao.EmployeeDAOImpl;
import dao.HibernateUtil;
import dao.ServerDAO;
import dao.ServerDAOImpl;
import bean.Employee;
import bean.Server;

public class Test {

    public static void main(String[] args) {

        Server[] ser = new Server[1000];

        for (int i = 0; i < ser.length; i++) {
            ser[i] = new Server();
            ser[i].setAdminIP("192.168.178.1");
            ser[i].setCumstomerIP("10.203.33.21");
            ser[i].setServerName("Mikes Server");
        }

        ServerDAO dao = new ServerDAOImpl();
        for (Server s : ser) {
            HibernateUtil.beginTransaction();
            dao.save(s);
            HibernateUtil.commitTransaction();
        }

        Employee[] empList = new Employee[1000];

        for (int i = 0; i < empList.length; i++) {
            empList[i] = new Employee();
            empList[i].setFirstname("Maoam" + i);
            empList[i].setOfficialHandyNumber("342442323");
            empList[i].setPrivateHandyNumber("4234234");
            empList[i].setSubsidiary("sdsds");
            empList[i].setSurname("dfsdas");
            empList[i].setTelephoneNumberAtHome("dasdsa");
            empList[i].setType("Mitarbeiter");
            empList[i].setWorkgroup("dasdasdas");
        }

        EmployeeDAO dao2 = new EmployeeDAOImpl();
        for (Employee emp : empList) {
            HibernateUtil.beginTransaction();
            dao2.save(emp);
            HibernateUtil.commitTransaction();
        }
    }
}
