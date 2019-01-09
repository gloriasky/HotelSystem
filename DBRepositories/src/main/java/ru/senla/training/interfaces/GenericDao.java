package ru.senla.training.interfaces;

import org.hibernate.Session;

public interface GenericDao {

    Long getCount(Session session);
}
