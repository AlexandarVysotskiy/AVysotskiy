package cinema.data;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Class HibernateUtil
 *
 * @author Alexandar Vysotskiy
 * @version 1.0
 */

public class HibernateUtil {
    private static SessionFactory factory;

    public static SessionFactory getInstance() {
        if (factory == null) {
            synchronized (SessionFactory.class) {
                if (factory == null) {
                    try {
                        factory = new Configuration()
                                .configure("hibernate.cfg.xml")
                                .buildSessionFactory();
                    } catch (HibernateException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return factory;
    }
}
