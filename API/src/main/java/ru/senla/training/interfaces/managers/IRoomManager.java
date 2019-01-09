package ru.senla.training.interfaces.managers;

import ru.senla.training.exceptions.SomethingWentWrong;
import ru.senla.training.interfaces.model.IGuest;
import ru.senla.training.interfaces.model.IRoom;
import ru.senla.training.dao.Comparators;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface IRoomManager extends IManager {


    List<IRoom> readAll() throws SomethingWentWrong;
    List<IRoom> sort(Comparators comparators) throws SomethingWentWrong;
    void update(IRoom room) throws SQLException, SomethingWentWrong;
    int getSum(IGuest guest) throws SomethingWentWrong;
    Long getNumberOfFreeRooms() throws SomethingWentWrong;
    void addRoom(IRoom room) throws SomethingWentWrong, SQLException;
    void setGuest(IRoom room, IGuest guest, Boolean canChangeStatus) throws SomethingWentWrong, SQLException;
    void evictGuest(IRoom room, IGuest guest,Boolean canChangeStatus) throws SomethingWentWrong, SQLException;
    List<IRoom> freeByTheDate(Date date) throws SomethingWentWrong;
}
