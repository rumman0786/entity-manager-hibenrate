package app;

import model.AuthUser;
import model.UserGroup;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.JPAUtil;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @author rumman
 * @since 7/12/18
 */
public class Driver {

    private static final Logger logger = LoggerFactory.getLogger(Driver.class);

    public static void basic() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        AuthUser authUser = new AuthUser();
        authUser.setFirstName("Rumman");
        authUser.setLastName("Ashraf");
        authUser.setDivision("Rajshahi");

        logger.info("[Driver::basic] executing query");

        em.persist(authUser);
        em.flush();

        logger.info("[Driver::basic] postgres version {}", authUser.getId());

        em.getTransaction().commit();
        em.close();

        JPAUtil.shutdown();
    }

    public static void inheritacnce() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        AuthUser authUser = new AuthUser();
        authUser.setFirstName("New4");
        authUser.setLastName("Object4");
        authUser.setDivision("Rajshahi");

        AuthUser authUser2 = em.find(AuthUser.class, 2L);

        logger.info("[Driver::inheritacnce] executing query");

        List<AuthUser> authUsers = Arrays.asList(authUser, authUser2);

        UserGroup userGroup = new UserGroup();
        userGroup.setName("Group 5");


        for (AuthUser user: authUsers) {
            userGroup.addUser(user);
        }

        em.persist(userGroup);
        em.flush();

        logger.info("[Driver::inheritacnce] postgres version {}", authUser.getId());

        em.getTransaction().commit();
        em.close();

        JPAUtil.shutdown();
    }

    public void orderColumnTest() {

        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        List<AuthUser> authUsers = em.createQuery("FROM AuthUser", AuthUser.class).getResultList();

        Hibernate.initialize(authUsers);

        AuthUser user1 = new AuthUser();
        user1.setFirstName("New" + UUID.randomUUID());
        user1.setLastName("Object" + UUID.randomUUID());
        user1.setDivision("Rajshahi");

        authUsers.add(user1);

        AuthUser user2 = new AuthUser();
        user2.setFirstName("New" + UUID.randomUUID());
        user2.setLastName("Object" + UUID.randomUUID());
        user2.setDivision("Rajshahi");

        authUsers.add(user2);

        UserGroup userGroup = em.find(UserGroup.class, 4);

        Hibernate.initialize(userGroup.getUsers());

        for (AuthUser user : authUsers) {
            userGroup.addUser(user);
        }

        logger.debug("##########");
        logger.debug(userGroup.getUsers().toString());
        logger.debug(userGroup.getUsers().get(1).toString());
        logger.debug("##########");

        userGroup.removeUser(userGroup.getUsers().get(1));
        userGroup.removeUser(userGroup.getUsers().get(2));

        em.merge(userGroup);

        em.flush();

        em.getTransaction().commit();

        em.close();

        JPAUtil.shutdown();
    }
}
