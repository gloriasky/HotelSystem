package ru.senla.training.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import ru.senla.training.interfaces.IGuestDao;
import ru.senla.training.interfaces.model.IGuest;
import ru.senla.training.interfaces.model.IRecord;
import ru.senla.training.interfaces.model.IRoom;
import ru.senla.training.model.Guest;

import java.util.ArrayList;
import java.util.List;

public class GuestDao extends AbstractDao implements IGuestDao {
    private static IGuestDao guestDao;

    private GuestDao(){

    }

    public static IGuestDao getInstance(){
        if(guestDao ==null){
            guestDao =new GuestDao();
        }
        return guestDao;
    }
    public void addGuest(Session session, IGuest guest){
        session.save(guest);
    }
    public List<IGuest> readAll(Session session){
        Criteria criteria = session.createCriteria (IGuest.class);
        List<IGuest> guests = criteria.list();
        return guests;
    }
    public List<IGuest> sort(Session session,Comparators comparator){
        Criteria criteria = session.createCriteria (Guest.class);
        List<IGuest> guests = criteria.addOrder(Order.asc(comparator.toString().toLowerCase())).list();
        return guests;
    }
    public void update(Session session,IGuest guest){
        session.saveOrUpdate(guest);
    }
    public List<IGuest> findGuest(Session session, IGuest guest){
        Criteria criteria = session.createCriteria (IGuest.class);
        criteria.add(Restrictions.eq("firstName",guest.getFirstName()));
        criteria.add(Restrictions.eq("lastName",guest.getLastName()));
        List<IGuest> guests = criteria.list();
        return guests;
    }
    public List<IGuest> getLastGuests(Session session,IRoom room){
        List<IRecord> records = session.createCriteria(IRecord.class)
                .createAlias("room","r", JoinType.LEFT_OUTER_JOIN)
                .createAlias("guest", "g", JoinType.RIGHT_OUTER_JOIN)
                .createAlias("service", "s", JoinType.LEFT_OUTER_JOIN)
                .add( Restrictions.eq("r.id", room.getId()) )
                .add(Restrictions.isNotNull("g.id"))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
        List<IGuest> lastGuests = new ArrayList<>();
        for (int t = 0;t<records.size();t++){
            if(records.get(t).getGuest()!=null) {
                lastGuests.add(records.get(t).getGuest());
            }
        }
        return lastGuests;
    }
    public Long getCount(Session session){
        Criteria criteria = session.createCriteria (IGuest.class);
        List<Long> guests = criteria.setProjection(Projections.count("id")).list();
        return guests.get(0);
    }
}
