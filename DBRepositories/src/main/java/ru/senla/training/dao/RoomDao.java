package ru.senla.training.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import ru.senla.training.interfaces.IRoomDao;
import ru.senla.training.interfaces.model.IGuest;
import ru.senla.training.interfaces.model.IRecord;
import ru.senla.training.interfaces.model.IRoom;
import ru.senla.training.interfaces.model.Status;
import ru.senla.training.model.Record;

import java.util.Date;
import java.util.List;

public class RoomDao extends AbstractDao implements IRoomDao {

    private static IRoomDao roomDao;

    private RoomDao(){

    }

    public static IRoomDao getInstance(){
        if(roomDao==null){
            roomDao=new RoomDao();
        }
        return roomDao;
    }

    public void addRoom(Session session, IRoom  room){
        session.save(room);
    }
    public Long getNumberOfFreeRooms(Session session){
        Criteria criteria = session.createCriteria (IRoom.class);
        criteria.add(Restrictions.eq("status", Status.FREE));
        criteria.setProjection(Projections.count("id"));
        List<Long> freeRooms = criteria.list();
        return freeRooms.get(0);
    }
    public List<IRoom> readAll(Session session){
        Criteria criteria = session.createCriteria (IRoom.class);
        List<IRoom> rooms = criteria.list();
        return rooms;
    }
    public List<IRoom> sort(Session session, Comparators comparator){
        Criteria criteria = session.createCriteria (IRoom.class);
        List<IRoom> rooms = criteria.addOrder(Order.asc(comparator.toString().toLowerCase())).list();
        return rooms;
    }
    public void update(Session session,IRoom room){
        session.saveOrUpdate(room);
    }
    public Long getCount(Session session){
        Criteria criteria = session.createCriteria (IRoom.class);
        List<Long> services = criteria.setProjection(Projections.count("id")).list();
        return services.get(0);
    }
    public int  getRoomPrice(Session session, IGuest guest){
        List<IRecord> records = session.createCriteria(IRecord.class)
                .createAlias("guest", "g", JoinType.LEFT_OUTER_JOIN)
                .createAlias("service", "s", JoinType.LEFT_OUTER_JOIN)
                .createAlias("room","r")
                .add( Restrictions.eq("g.id", guest.getId()) )
                .add(Restrictions.isNotNull("s.id"))
                .setProjection(Projections.sum("s.price"))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
        IRoom room = records.get(0).getRoom();
        return room.getPrice();
    }
    public void setGuest(Session session, IGuest guest,IRoom room){
        Record record = new Record(guest,room);
        session.save(record);
        room.setStatus(Status.BUSY);
        session.update(room);
    }
    public void evictGuest(Session session, IGuest guest,IRoom room){
        Record record = new Record(guest,null);
        session.save(record);
        room.setStatus(Status.FREE);
        session.update(room);
    }
    public List<IRoom> freeByDate(Session session, Date date){
        String hql = "select distinct r.id, r.capacity,r.stars_number,r.price,r.status,guest.release_date " +
                "    from Room as r" +
                "    inner join r.Guest as guest" +
                "    right outer join Record.room as rec" +
                "    where r.status = 'FREE' or guest.release_date <"+date;
        List<IRoom> rooms = session.createQuery(hql).list();
        return rooms;
    }
}
