package app;

import model.AuthUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.JPAUtil;

import javax.persistence.EntityManager;

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

        AuthUser authUser = new AuthUser();
        authUser.setFirstName("Rumman");
        authUser.setLastName("Ashraf");

        logger.info("[MainApp::main] executing query");

        em.persist(authUser);
        em.flush();

        logger.info("[MainApp::main] postgres version {}", authUser.getId());

        em.getTransaction().commit();
        em.close();

        JPAUtil.shutdown();

        logger.info("[MainApp::main] finished");
    }
}