package dao;

import bean.AssignmentGroup;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.HibernateException;

public interface AssignmentGroupDAO extends GenericDAO<AssignmentGroup, BigDecimal>{
    public void saveOrUpdateAssignment(AssignmentGroup assignGroup) throws HibernateException;
    public List<AssignmentGroup> getAllAssignmentGroups() throws HibernateException;
}
