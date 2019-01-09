package ru.senla.training.service;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.senla.training.dao.GuestDao;
import ru.senla.training.exceptions.SomethingWentWrong;
import ru.senla.training.hibernate.HibernateUtil;
import ru.senla.training.interfaces.IGuestDao;
import ru.senla.training.interfaces.managers.IGuestManager;
import ru.senla.training.interfaces.model.IGuest;
import ru.senla.training.interfaces.model.IRoom;
import ru.senla.training.dao.Comparators;

import java.sql.SQLException;
import java.util.List;

public class GuestManager implements IGuestManager {
    private static IGuestManager guestManager;
    private IGuestDao guestRepository;
    private HibernateUtil dbConnector;
    
    private GuestManager() {
        dbConnector = HibernateUtil.getInstance();
        guestRepository = GuestDao.getInstance();
    }
    
    public static IGuestManager getInstance() {
    	if(guestManager == null) {
    		guestManager = new GuestManager();
    	}
    	return guestManager;
    }
    public List<IGuest> readAll() throws SomethingWentWrong {
        SessionFactory sessionFactory = dbConnector.getSessionFactory();
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            List<IGuest> guests = guestRepository.readAll(session);
            session.getTransaction().commit();
            session.close();
            return guests;
        }catch(Exception ex){
            session.getTransaction().rollback();
            session.close();
            throw new SomethingWentWrong();
        }
    }

    public List<IGuest> sort(Comparators comparator) throws SomethingWentWrong {
        SessionFactory sessionFactory = dbConnector.getSessionFactory();
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            List<IGuest> guest = guestRepository.sort(session,comparator);
            session.getTransaction().commit();
            session.close();
            return guest;
        }catch(Exception ex){
            session.getTransaction().rollback();
            session.close();
            throw new SomethingWentWrong();
        }
    }

    public List<IGuest> findGuest(IGuest guest) throws SomethingWentWrong {
        SessionFactory sessionFactory = dbConnector.getSessionFactory();
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            List<IGuest> guests = guestRepository.findGuest(session,guest);
            session.getTransaction().commit();
            session.close();
            return guests;
        }catch(Exception ex){
            session.getTransaction().rollback();
            session.close();
            throw new SomethingWentWrong();
        }
    }
    public void update(IGuest guest) throws SQLException, SomethingWentWrong {
        SessionFactory sessionFactory = dbConnector.getSessionFactory();
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            guestRepository.update(session,guest);
            session.getTransaction().commit();
            session.close();
        }catch(Exception ex){
            session.getTransaction().rollback();
            session.close();
            throw new SomethingWentWrong();
        }
    }
    public List<IGuest> getLastGuests(IRoom room) throws SomethingWentWrong {
        SessionFactory sessionFactory = dbConnector.getSessionFactory();
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            List<IGuest> guests = guestRepository.getLastGuests(session,room);
            session.getTransaction().commit();
            session.close();
            return guests;
        }catch(Exception ex){
            session.getTransaction().rollback();
            session.close();
            throw new SomethingWentWrong();
        }
    }

    public void addGuest(IGuest guest) throws  SomethingWentWrong {
        SessionFactory sessionFactory = dbConnector.getSessionFactory();
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            guestRepository.addGuest(session,guest);
            session.getTransaction().commit();
            session.close();
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
            Long count  = guestRepository.getCount(session);
            session.getTransaction().commit();
            session.close();
            return count;
        }catch(Exception ex){
            session.getTransaction().rollback();
            session.close();
            throw new SomethingWentWrong();
        }
    }

}
