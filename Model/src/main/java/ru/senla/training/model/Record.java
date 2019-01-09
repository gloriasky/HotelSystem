package ru.senla.training.model;

import ru.senla.training.interfaces.model.IGuest;
import ru.senla.training.interfaces.model.IRecord;
import ru.senla.training.interfaces.model.IRoom;
import ru.senla.training.interfaces.model.IService;

import javax.persistence.*;

@Entity
@Table(name = "records")
public class Record implements IRecord {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne(cascade = CascadeType.PERSIST,targetEntity = Guest.class,fetch = FetchType.LAZY)
    @JoinColumn()
    private IGuest guest;
    @ManyToOne(cascade = CascadeType.PERSIST,targetEntity = Room.class,fetch = FetchType.LAZY)
    private IRoom room;
    @ManyToOne(cascade = CascadeType.PERSIST,targetEntity = Service.class,fetch = FetchType.LAZY)
    private IService service;

    public Record(){

    }
    public Record(IGuest guest, IRoom room){
        this.guest = guest;
        id = null;;
        this.room = room;
        this.service = null;

    }
    public Record(IGuest guest, IRoom room, IService service){
        this.guest = guest;
        id = null;
        this.room = room;
        this.service = service;

    }

    public Integer getRecord_id() {
        return id;
    }

    public void setRecord_id(Integer record_id) {
        this.id = record_id;
    }

    public IGuest getGuest() {
        return guest;
    }

    public void setGuest(IGuest guest) {
        this.guest = guest;
    }

    public IRoom getRoom() {
        return room;
    }

    public void setRoom(IRoom room) {
        this.room = room;
    }

    public IService getService() {
        return service;
    }

    public void setService(IService service) {
        this.service = service;
    }

    @Override
    public String toString() {
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append("Record ");
        sBuilder.append(id);
        sBuilder.append(": guest = ");
        sBuilder.append(guest.getId());
        if(room!=null){
            sBuilder.append(", room = ");
            sBuilder.append(room.getId());
        }
        if(service!=null){
            sBuilder.append(", service = ");
            sBuilder.append(service.getId());
        }
        return sBuilder.toString();
    }
}
