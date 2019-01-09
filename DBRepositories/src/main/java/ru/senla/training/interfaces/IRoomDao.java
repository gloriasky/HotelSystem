package ru.senla.training.interfaces;

import org.hibernate.Session;
import ru.senla.training.dao.Comparators;
import ru.senla.training.interfaces.model.IGuest;
import ru.senla.training.interfaces.model.IRoom;

import java.util.Date;
import java.util.List;

public interface IRoomDao extends GenericDao {
    List<IRoom> readAll(Session session);

    List<IRoom> sort(Session session, Comparators comparator);

     void addRoom(Session session, IRoom room);

     Long getNumberOfFreeRooms(Session session);

    void update(Session session, IRoom room);


    int getRoomPrice(Session session, IGuest guest);

    void setGuest(Session session, IGuest guest, IRoom room);

    void evictGuest(Session session, IGuest guest, IRoom room);

    List<IRoom> freeByDate(Session session, Date date);
}
