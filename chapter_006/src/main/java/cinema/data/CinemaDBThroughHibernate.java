package cinema.data;

import cinema.models.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * Class CinemaDBThroughHibernate
 *
 * @author Alexandar Vysotskiy
 * @version 1.0
 * @since
 */

public class CinemaDBThroughHibernate implements Db {

    private static CinemaDBThroughHibernate cinemaDBThroughHibernate;

    public static CinemaDBThroughHibernate getInstance() {
        if (cinemaDBThroughHibernate == null) {
            synchronized (SessionFactory.class) {
                if (cinemaDBThroughHibernate == null) {
                    cinemaDBThroughHibernate = new CinemaDBThroughHibernate();
                }
            }
        }
        return cinemaDBThroughHibernate;
    }

    private SessionFactory sessionFactory;

    public CinemaDBThroughHibernate() {
        sessionFactory = HibernateUtil.getInstance();
    }

    /**
     * Add accounts in data bases;
     */
    @Override
    public Account addNewAccount(Account account) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(account);
            session.getTransaction().commit();
            session.close();
        }
        return account;
    }


    /**
     * Get accounts from data bases;
     */
    @Override
    public List<Account> getAccounts() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery(Account.class);
            cq.from(Account.class);
            Query query = session.createQuery(cq);
            List<Account> result = query.getResultList();
            return result;
        }
    }
}