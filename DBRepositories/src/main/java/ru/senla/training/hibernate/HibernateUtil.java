package ru.senla.training.hibernate;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil{

    private static SessionFactory sessionFactory;
    private static HibernateUtil hibernateUtil;

    private HibernateUtil(){
        buildSessionFactory();
    }

    public static HibernateUtil getInstance(){
        if(hibernateUtil==null){
            hibernateUtil= new HibernateUtil();
        }
        return hibernateUtil;
    }

    private static void buildSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        ServiceRegistry serviceRegistry = new
                ServiceRegistryBuilder().applySettings(configuration.
                getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    public  SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    public  boolean shutdown() {
        try {
            if (sessionFactory != null) {
                getSessionFactory().close();
            }
            return true;
        }catch (Exception ex){
            return false;
        }
    }


}