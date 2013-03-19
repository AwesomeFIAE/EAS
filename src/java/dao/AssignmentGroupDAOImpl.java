package dao;

import bean.AssignmentGroup;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;

public class AssignmentGroupDAOImpl extends GenericDAOImpl<AssignmentGroup, BigDecimal> implements AssignmentGroupDAO, Serializable {

    @Override
    public void saveOrUpdateAssignment(AssignmentGroup assignGroup) throws HibernateException {
        HibernateUtil.beginTransaction();
        save(assignGroup);
        HibernateUtil.commitTransaction();
    }

    @Override
    public List<AssignmentGroup> getAllAssignmentGroups() throws HibernateException {
        String sql = "FROM AssignmentGroup";
        
        HibernateUtil.beginTransaction();
        Query query = HibernateUtil.getSession().createQuery(sql);
        List<AssignmentGroup> assignGroup = findMany(query);
        HibernateUtil.commitTransaction();
        
        return assignGroup;
    }
    
}
