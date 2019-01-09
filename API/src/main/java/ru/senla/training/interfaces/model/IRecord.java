package ru.senla.training.interfaces.model;

public interface IRecord {
     Integer getRecord_id();

     void setRecord_id(Integer record_id);

     IGuest getGuest();

     void setGuest(IGuest guest);

     IRoom getRoom();

     void setRoom(IRoom room);

     IService getService();

     void setService(IService service_id);
}
