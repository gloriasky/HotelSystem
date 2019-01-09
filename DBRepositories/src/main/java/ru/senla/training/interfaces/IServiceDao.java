package ru.senla.training.interfaces;

import org.hibernate.Session;
import ru.senla.training.dao.Comparators;
import ru.senla.training.interfaces.model.IGuest;
import ru.senla.training.interfaces.model.IService;

import java.util.List;

public interface IServiceDao extends GenericDao {
     List<IService> readAll(Session session);
     List<IService> sort(Session session, Comparators comparator);
     void update(Session session, IService service);
     void addService(Session session,IService service);
     List<IService> getServicesOfTheGuests(Session session, IGuest guest);
     int getSumOfTheServices(Session session, IGuest guest);
}
