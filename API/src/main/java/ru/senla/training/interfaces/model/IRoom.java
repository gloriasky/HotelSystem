package ru.senla.training.interfaces.model;

import java.util.List;

public interface IRoom {
     Integer getPrice();
     void setPrice(int price);
     Integer getCapacity();
     void setCapacity(int capacity);
     Integer getNumberOfStars();
     void setNumberOfStars(int numberOfStars);
     Integer getId();
     void setId(Integer id);
     Status getStatus();
     void setStatus(String  status);
     void setStatus(Status status);
     boolean equals(IRoom room);
     IRoom clone() throws CloneNotSupportedException ;
     String toString();


}
