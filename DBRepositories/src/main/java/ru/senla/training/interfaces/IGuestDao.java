package ru.senla.training.interfaces;

import org.hibernate.Session;
import ru.senla.training.dao.Comparators;
import ru.senla.training.interfaces.model.IGuest;
import ru.senla.training.interfaces.model.IRoom;

import java.util.List;

public interface IGuestDao extends GenericDao{
    List<IGuest> readAll(Session session);
    List<IGuest> sort(Session session, Comparators comparator);
    List<IGuest> findGuest(Session session,IGuest guest);
    void update(Session session, IGuest guest);
    List<IGuest> getLastGuests(Session session, IRoom room);
    void addGuest(Session session,IGuest guest);
}
