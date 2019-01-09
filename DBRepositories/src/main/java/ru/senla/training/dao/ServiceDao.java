package ru.senla.training.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import ru.senla.training.interfaces.IServiceDao;
import ru.senla.training.interfaces.model.IGuest;
import ru.senla.training.interfaces.model.IRecord;
import ru.senla.training.interfaces.model.IService;

import java.util.ArrayList;
import java.util.List;

public class ServiceDao extends AbstractDao implements IServiceDao {

    private static ServiceDao serviceDao;

    private ServiceDao(){

    }

    public static ServiceDao getInstance(){
        if(serviceDao ==null){
            serviceDao =new ServiceDao();
        }
        return serviceDao;
    }

    public List<IService> readAll(Session session){
        Criteria criteria = session.createCriteria (IService.class);
        List<IService> services = criteria.list();
        return services;
    }
    public List<IService> sort(Session session, Comparators comparator){
        Criteria criteria = session.createCriteria (IService.class);
        List<IService> services = criteria.addOrder(Order.asc(comparator.toString().toLowerCase())).list();
        return services;
    }
    public void update(Session session, IService service){
        session.saveOrUpdate(service);
    }
    public void addService(Session session, IService service){
        session.save(service);
    }
    public Long getCount(Session session){
        Criteria criteria = session.createCriteria (IService.class);
        List<Long> services = criteria.setProjection(Projections.count("id")).list();
        return services.get(0);
    }
    public List<IService> getServicesOfTheGuests(Session session, IGuest guest){
        List<IRecord> records = session.createCriteria(IRecord.class)
                .createAlias("guest", "g", JoinType.LEFT_OUTER_JOIN)
                .createAlias("service", "s", JoinType.LEFT_OUTER_JOIN)
                .createAlias("room","r")
                .add( Restrictions.eq("g.id", guest.getId()) )
                .add(Restrictions.isNotNull("s.id"))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
        List<IService> servicesOfTjeGuest = new ArrayList<>();
        for (int t = 0;t<records.size();t++){
            if(records.get(t).getService()!=null) {
                servicesOfTjeGuest.add(records.get(t).getService());
            }
        }
        return servicesOfTjeGuest;
    }
    public int getSumOfTheServices(Session session, IGuest guest){
        List<IRecord> records = session.createCriteria(IRecord.class)
                .createAlias("guest", "g", JoinType.LEFT_OUTER_JOIN)
                .createAlias("service", "s", JoinType.LEFT_OUTER_JOIN)
                .createAlias("room","r")
                .add( Restrictions.eq("g.id", guest.getId()) )
                .add(Restrictions.isNotNull("s.id"))
                .setProjection(Projections.sum("s.price"))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
        List<Integer> sum = new ArrayList<>();
        return sum.get(0);
    }
}
