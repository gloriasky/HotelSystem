package ru.senla.training.interfaces.managers;

import ru.senla.training.exceptions.SomethingWentWrong;
import ru.senla.training.interfaces.model.IGuest;
import ru.senla.training.interfaces.model.IService;
import ru.senla.training.dao.Comparators;
import java.sql.SQLException;
import java.util.List;


public interface IServiceManager extends IManager{
    List<IService> readAll() throws SomethingWentWrong;
    List<IService> sort(Comparators comparators) throws SomethingWentWrong;
    void update(IService service) throws SQLException, SomethingWentWrong;
    void addService(IService service) throws SQLException, SomethingWentWrong;
    List<IService> getServicesOfTheGuest(IGuest guest) throws SomethingWentWrong;
}
