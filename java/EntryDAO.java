import java.util.List;
import database.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import database.models.Entry;


public class EntryDAO {
//    public List<Entry> getEntries(String session_id) throws Exception {
//        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//        try {
//            List<Entry> entries = entityManager.createQuery(
//                    "select entry from Entry entry where session_id = :session_id order by id", Entry.class)
//                    .setParameter("session_id", session_id).getResultList();
//            transaction.commit();
//            session.close();
//            return entries;
//        } catch (Exception e) {
//            if (transaction != null)
//                transaction.rollback();
//            throw e;
//        }
//    }

    public void add(Entry entry) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(entry);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
        }
    }
}