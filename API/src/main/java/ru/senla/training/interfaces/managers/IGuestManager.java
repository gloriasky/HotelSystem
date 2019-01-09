package ru.senla.training.interfaces.managers;

import ru.senla.training.dao.Comparators;
import ru.senla.training.exceptions.SomethingWentWrong;
import ru.senla.training.interfaces.model.IGuest;
import ru.senla.training.interfaces.model.IRoom;

import java.sql.SQLException;
import java.util.List;

public interface IGuestManager extends IManager {

    List<IGuest> readAll() throws SomethingWentWrong;
    List<IGuest> sort(Comparators comparators) throws SomethingWentWrong;
     void update( IGuest guest) throws SQLException, SomethingWentWrong;
     void addGuest(IGuest guest) throws SQLException, SomethingWentWrong;
     List<IGuest> getLastGuests(IRoom room) throws SomethingWentWrong;


}
