import java.util.List;
import database.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import database.models.Entry;

import javax.transaction.SystemException;


public class EntryDAO {
    public List<Entry> getEntries(String session_id) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            List<Entry> entries = session.createQuery(
                    "select entry from Entry entry where session_id = :session_id order by id", Entry.class)
                    .setParameter("session_id", session_id).getResultList();
            transaction.commit();
            session.close();
            return entries;
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }

    public void add(Entry entry) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(entry);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
        } finally {
            session.close();
        }
    }
}