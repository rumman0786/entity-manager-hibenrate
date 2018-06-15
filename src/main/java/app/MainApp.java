package app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author therap
 * @since 6/2/18.
 */
public class MainApp {

    private static final Logger logger = LoggerFactory.getLogger(MainApp.class);

    public static void main(String[] args) {

        logger.info("[MainApp::main] started");

        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        // Check database version
        String sql = "SELECT message FROM log_data";

        logger.info("[MainApp::main] executing query");

        List<String> result = (List<String>) em.createNativeQuery(sql, String.class).getResultList();

        logger.info("[MainApp::main] postgres version {}", result.get(0));

        em.getTransaction().commit();
        em.close();

        JPAUtil.shutdown();

        logger.info("[MainApp::main] finished");
    }
}