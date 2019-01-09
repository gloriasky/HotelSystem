package ru.senla.training.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.senla.training.dao.ServiceDao;
import ru.senla.training.exceptions.SomethingWentWrong;
import ru.senla.training.hibernate.HibernateUtil;
import ru.senla.training.interfaces.IGuestDao;
import ru.senla.training.interfaces.IRoomDao;
import ru.senla.training.interfaces.managers.IRoomManager;
import ru.senla.training.interfaces.model.IGuest;
import ru.senla.training.interfaces.model.IRoom;

import java.util.Date;
import java.util.List;
import ru.senla.training.dao.Comparators;
import ru.senla.training.dao.GuestDao;
import ru.senla.training.dao.RoomDao;

public class RoomManager implements IRoomManager {
    private static  IRoomManager roomManager;
    private HibernateUtil dbConnector;
    private IRoomDao roomRepository;

    private RoomManager(){
        roomRepository = RoomDao.getInstance();
        dbConnector = HibernateUtil.getInstance();
    }

    public static IRoomManager getInstance(){
        if(roomManager == null){
            roomManager = new RoomManager();
        }
        return roomManager;
    }

    public Long getNumberOfFreeRooms() throws SomethingWentWrong {
        SessionFactory sessionFactory = dbConnector.getSessionFactory();
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            Long numberOfFreeRooms = roomRepository.getNumberOfFreeRooms(session);
            session.getTransaction().commit();
            session.close();;
            return numberOfFreeRooms;

        }catch(Exception ex){
            session.getTransaction().rollback();
            session.close();
            throw new SomethingWentWrong();
        }

    }
    public List<IRoom> readAll() throws SomethingWentWrong {
        SessionFactory sessionFactory = dbConnector.getSessionFactory();
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            List<IRoom> rooms = roomRepository.readAll(session);
            session.getTransaction().commit();
            session.close();
            return rooms;

        }catch(Exception ex){
            session.getTransaction().rollback();
            session.close();
            throw new SomethingWentWrong();
        }
    }
    public List<IRoom> sort(Comparators comparator) throws SomethingWentWrong {
        SessionFactory sessionFactory = dbConnector.getSessionFactory();
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            List<IRoom> rooms = roomRepository.sort(session,comparator);
            session.getTransaction().commit();
            session.close();
            return rooms;


        }catch(Exception ex){
            session.getTransaction().rollback();
            session.close();
            throw new SomethingWentWrong();
        }
    }
    public void update(IRoom room) throws  SomethingWentWrong {
        SessionFactory sessionFactory = dbConnector.getSessionFactory();
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            roomRepository.update(session,room);
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
            Long numberOfRooms = roomRepository.getCount(session);
            session.getTransaction().commit();
            session.close();
            return numberOfRooms;

        }catch(Exception ex){
            session.getTransaction().rollback();
            session.close();
            throw new SomethingWentWrong();
        }
    }
    public int getSum(IGuest guest) throws SomethingWentWrong {
        SessionFactory sessionFactory = dbConnector.getSessionFactory();
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            int serviceSum = ServiceDao.getInstance().getSumOfTheServices(session,guest);
            int roomSum = roomRepository.getRoomPrice(session,guest);
            session.getTransaction().commit();
            long seconds = guest.getDateOfRelease().getTime()-guest.getArrivalDate().getTime();
            int numberOfDays = (int) (seconds / (24 * 60 * 60 * 1000));
            int sum = serviceSum*numberOfDays + roomSum*numberOfDays;

            return sum;

        }catch(Exception ex){
            session.getTransaction().rollback();
            session.close();
            throw new SomethingWentWrong();
        }
    }

    public void addRoom(IRoom room) throws SomethingWentWrong {
        SessionFactory sessionFactory = dbConnector.getSessionFactory();
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            roomRepository.addRoom(session,room);
            session.getTransaction().commit();
            session.close();

        }catch(Exception ex){
            session.getTransaction().rollback();
            session.close();
            throw new SomethingWentWrong();
        }
    }
    public void setGuest(IRoom room, IGuest guest, Boolean canChangeStatus) throws SomethingWentWrong {
        SessionFactory sessionFactory = dbConnector.getSessionFactory();
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            roomRepository.setGuest(session, guest,room );
            session.getTransaction().commit();
            session.close();
        }catch(Exception ex){
            session.getTransaction().rollback();
            session.close();
            throw new SomethingWentWrong();
        }
    }
    public void evictGuest(IRoom room, IGuest guest,Boolean canChangeStatus) throws SomethingWentWrong {
        SessionFactory sessionFactory = dbConnector.getSessionFactory();
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            roomRepository.evictGuest(session,guest,room);
            session.getTransaction().commit();
            session.close();
        }catch(Exception ex){
            session.getTransaction().rollback();
            session.close();
            throw new SomethingWentWrong();
        }
    }

    public List<IRoom> freeByTheDate(Date date) throws SomethingWentWrong {
        SessionFactory sessionFactory = dbConnector.getSessionFactory();
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            List<IRoom> rooms = roomRepository.freeByDate(session,date);
            session.getTransaction().commit();
            session.close();
            return rooms;
        }catch(Exception ex){
            session.getTransaction().rollback();
            session.close();
            throw new SomethingWentWrong();
        }
    }

}