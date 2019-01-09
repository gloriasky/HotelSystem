package ru.senla.training.service;

import ru.senla.training.hibernate.HibernateUtil;

public class ExitService {
    private HibernateUtil connector;
    private static ExitService exitService;

    private ExitService(){
        connector= HibernateUtil.getInstance();
    }
    public static ExitService getInstance(){
        if(exitService==null){
            exitService = new ExitService();
        }
        return exitService;
    }
    public boolean closeConnection(){
        if(connector==null){
            return false;
        }
        return connector.shutdown();
    }
}
