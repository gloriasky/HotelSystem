package ru.senla.training.facade;


import org.apache.log4j.Logger;
import ru.senla.training.dao.Comparators;
import ru.senla.training.exceptions.SomethingWentWrong;
import ru.senla.training.interfaces.managers.IGuestManager;
import ru.senla.training.interfaces.managers.IRoomManager;
import ru.senla.training.interfaces.managers.IServiceManager;
import ru.senla.training.interfaces.model.IGuest;
import ru.senla.training.interfaces.model.IRoom;
import ru.senla.training.interfaces.model.IService;
import ru.senla.training.model.Room;
import ru.senla.training.service.ExitService;
import ru.senla.training.service.GuestManager;
import ru.senla.training.service.RoomManager;
import ru.senla.training.service.ServiceManager;

import java.util.Date;
import java.util.List;

public class Hotel {

    final static Logger logger = Logger.getLogger(Hotel.class);
    private IRoomManager roomManager;
    private IGuestManager guestManager;
    private IServiceManager serviceManager;
    private static Hotel hotel;

    private Hotel(){}

    public static Hotel getInstance(){
        if(hotel==null){
            hotel = new Hotel();
        }
        return hotel;
    }
    public boolean start(){
        boolean isDone = true;
        try {
            roomManager = RoomManager.getInstance();
            guestManager = GuestManager.getInstance();
            serviceManager = ServiceManager.getInstance();
        }catch (Exception ex){
            logger.error(ex);
            isDone = false;
        }
        return isDone;
    }
    public boolean changeServicePrice(IService service){
       boolean isDone = true;
        try {
            serviceManager.update(service);
        }catch (Exception ex){
            logger.error(ex);
            isDone = false;
        }
        return isDone;

    }
    public boolean changeRoomPrice(IRoom room){
        boolean isDone = true;
        try {
            roomManager.update(room);
        }catch (Exception ex){
            logger.error(ex);
            isDone = false;
        }
        return isDone;
    }
    public boolean changeStatus(IRoom room){
        boolean isDone = true;
        try {
            roomManager.update(room);
        }catch (Exception ex){
            logger.error(ex);
            isDone = false;
        }
        return isDone;
     }
    public boolean addRoom(IRoom room){
        boolean isDone = true;
        try {
            roomManager.addRoom(room);
        }catch(Exception ex){
            logger.error(ex);
            isDone = false;
        }
        return isDone;
    }
    public boolean addGuest(IGuest guest){
        boolean isDone = true;
        try {
           guestManager.addGuest(guest);
        }catch(Exception ex){
            logger.error(ex);
            isDone = false;
        }
        return isDone;
    }
    public boolean addService(IService service){
        boolean isDone = true;
        try {
            serviceManager.addService(service);
        }catch(Exception ex){
            logger.error(ex);
            isDone = false;
        }
        return isDone;
    }
    public boolean setGuest(int roomNumber, IGuest guest){
        boolean isDone = true;
        try {
            roomManager.setGuest(new Room(roomNumber), guest, true);
        } catch (Exception ex) {
            logger.error(ex);
            isDone = false;
        }
        return isDone;
    }
    public boolean evictGuest(int roomNumber, IGuest guest){
        boolean isDone = true;
        try {
            roomManager.evictGuest(new Room(roomNumber), guest, true);
        }catch (Exception ex){
            logger.error(ex);
            isDone = false;
        }
        return isDone;
    }
    public List<IService> sortServiceByPrice(){
        try {
           return serviceManager.sort(Comparators.PRICE);
        } catch (SomethingWentWrong ex) {
            logger.error(ex);
            return null;
        }

    }

    public List<IRoom> sortByCapacity(){
        try {
            return roomManager.sort(Comparators.CAPACITY);
        } catch (SomethingWentWrong somethingWentWrong) {
            logger.error(somethingWentWrong);
        }
        return null;
    }
    public List<IService> sortServiceByAlphabet(){
        try {
            return serviceManager.sort(Comparators.SECTION);
        } catch (SomethingWentWrong somethingWentWrong) {
            logger.error(somethingWentWrong);
        }
        return null;
    }
    public List<IGuest> sortByDate(){
        try {
            return guestManager.sort(Comparators.RELEASE_DATE);
        } catch (SomethingWentWrong somethingWentWrong) {
            logger.error(somethingWentWrong);
        }
        return null;
    }
    public List<IRoom> sortByNumberOfStars(){
        try {
            return RoomManager.getInstance().sort(Comparators.NUMBER_OF_STARS);
        } catch (SomethingWentWrong somethingWentWrong) {
            logger.error(somethingWentWrong);
        };
        return null;
    }
    public List<IRoom> sortRoomByPrice(){
        try {
            return RoomManager.getInstance().sort(Comparators.PRICE);
        } catch (SomethingWentWrong somethingWentWrong) {
            logger.error(somethingWentWrong);
        };
        return null;
    }
    public List<IRoom> freeByTheDate(Date date){
        try {
            return roomManager.freeByTheDate(date);
        }catch (Exception ex){
            logger.error(ex);
            return null;
        }
    }
    public Integer getSum(IGuest guest){
        try {
            return roomManager.getSum(guest);
        }catch (Exception ex){
            logger.error(ex);
            return null;
        }
    }
    public Long numberOfGuests(){
        try {
            return guestManager.getCount();
        }catch (Exception ex){
            logger.error(ex);
            return null;
        }
    }
    public List<IGuest> getLastGuests(IRoom room){
        try {
            return guestManager.getLastGuests(room);
        }catch (Exception ex){
            logger.error(ex);
            return null;
        }
    }
    public List<IRoom> getRooms() {
        try {
            return roomManager.readAll();
        } catch (Exception ex) {
            logger.error(ex);
            return null;
        }
    }
    public Long numberOfFreeRooms(){
        try {
            return roomManager.getNumberOfFreeRooms();
        }catch (Exception ex){
            logger.error(ex);
            return null;
        }
    }
    public List<IService> showServices(IGuest guest){
        try {
           return serviceManager.getServicesOfTheGuest(guest);
        }catch (Exception ex){
            logger.error(ex);
            return null;
        }
    }
    public List<IGuest> getGuests(){
        try {
            return GuestManager.getInstance().readAll();
        }catch(Exception ex){
            logger.error(ex);
            return null;

        }
    }


    public List<IService> getServices(){
        try {
            return ServiceManager.getInstance().readAll();
        }catch (Exception ex){
            logger.error(ex);
            return null;
        }
    }
    public boolean end()   {
        return ExitService.getInstance().closeConnection();
    }

}
