package ru.senla.training.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.senla.training.exceptions.SomethingWentWrong;
import ru.senla.training.hibernate.HibernateUtil;
import ru.senla.training.interfaces.IServiceDao;
import ru.senla.training.interfaces.managers.IServiceManager;
import ru.senla.training.interfaces.model.IGuest;
import ru.senla.training.interfaces.model.IService;
import ru.senla.training.dao.Comparators;
import ru.senla.training.dao.ServiceDao;
import java.util.List;

public class ServiceManager implements IServiceManager {
    private static IServiceManager serviceManager;
    private HibernateUtil dbConnector;
    private IServiceDao serviceRepository;

    private ServiceManager(){
        serviceRepository = ServiceDao.getInstance();
        dbConnector = HibernateUtil.getInstance();
    }

    public static IServiceManager getInstance(){
        if(serviceManager == null){
            serviceManager = new ServiceManager();
        }
        return serviceManager;
    }

    public List<IService> readAll() throws SomethingWentWrong {
        SessionFactory sessionFactory = dbConnector.getSessionFactory();
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            List<IService> services = serviceRepository.readAll(session);
            session.getTransaction().commit();
            session.close();
            return services;

        }catch(Exception ex){
            session.getTransaction().rollback();
            session.close();
            throw new SomethingWentWrong();
        }
    }
    public List<IService> sort(Comparators comparator) throws SomethingWentWrong {
        SessionFactory sessionFactory = dbConnector.getSessionFactory();
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            List<IService> services = serviceRepository.sort(session,comparator);
            session.getTransaction().commit();
            session.close();
            return services;

        }catch(Exception ex){
            session.getTransaction().rollback();
            session.close();
            throw new SomethingWentWrong();
        }
    }
    public void update(IService service) throws  SomethingWentWrong {
        SessionFactory sessionFactory = dbConnector.getSessionFactory();
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            serviceRepository.update(session,service);
            session.getTransaction().commit();
            session.close();
            throw new SomethingWentWrong();

        }catch(Exception ex){
            session.getTransaction().rollback();
            session.close();
            throw new SomethingWentWrong();
        }
    }
    public Long getCount() throws SomethingWentWrong {
        SessionFactory sessionFactory = dbConnector.getSessionFactory();
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            Long count = serviceRepository.getCount(session);
            session.getTransaction().commit();
            session.close();
            return count;
        }catch(Exception ex){
            session.getTransaction().rollback();
            session.close();
            throw new SomethingWentWrong();
        }
    }
    public List<IService> getServicesOfTheGuest(IGuest guest) throws SomethingWentWrong {
        SessionFactory sessionFactory = dbConnector.getSessionFactory();
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            List<IService> services = serviceRepository.getServicesOfTheGuests(session,guest);
            session.getTransaction().commit();
            session.close();
            return services;

        }catch(Exception ex){
            session.getTransaction().rollback();
            session.close();
            throw new SomethingWentWrong();
        }
    }
    public void addService(IService service) throws  SomethingWentWrong {
        SessionFactory sessionFactory = dbConnector.getSessionFactory();
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            serviceRepository.addService(session,service);
            session.getTransaction().commit();
            session.close();

        }catch(Exception ex){
            session.getTransaction().rollback();
            session.close();
            throw new SomethingWentWrong();
        }
    }

}