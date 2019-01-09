package ru.senla.training.model;

import ru.senla.training.interfaces.model.IGuest;
import ru.senla.training.interfaces.model.IRoom;
import ru.senla.training.interfaces.model.Status;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ROOMS")
public class Room implements Serializable,Cloneable, IRoom {
	@Id
	@GeneratedValue
	private Integer id;
	@Column
	private Integer price;
	@Column
	private Integer capacity;
	@Column(name = "stars_number")
	private Integer numberOfStars;
	@Enumerated(EnumType.STRING)
	private Status status;


	public Room(){
        this.id = null;
	}
	public Room(int id){
		this.id = id;
	}
	public Room(Integer id, Integer price, Integer capacity, Integer numberOfStars, Status status) {
		this.id = id;
		this.price = price;
		this.capacity = capacity;
		this.numberOfStars = numberOfStars;
		this.status = status;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Integer getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public Integer getNumberOfStars() {
		return numberOfStars;
	}
	public void setNumberOfStars(int numberOfStars) {
		this.numberOfStars = numberOfStars;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = Status.valueOf(status);
	}
	public void setStatus(Status status) {
		this.status = status;
	}

    public boolean equals(IRoom room) {
        if (this.getId().equals(room.getId())) return true;
        else{return false;}
    }

	@Override
	public IRoom clone() throws CloneNotSupportedException {
		Room cloneRoom = new Room();
		cloneRoom.setStatus(this.status);
		cloneRoom.setPrice(this.price);
		cloneRoom.setCapacity(this.capacity);
		cloneRoom.setNumberOfStars(this.numberOfStars);
		return cloneRoom;
	}
	@Override
	public String toString() {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("Room ");
		sBuilder.append(id);
		sBuilder.append(": price = ");
		sBuilder.append(price);
		sBuilder.append(", capacity = ");
		sBuilder.append(capacity);
		sBuilder.append(", number of stars = ");
		sBuilder.append(numberOfStars);
		sBuilder.append(", status = ");
		sBuilder.append(status);
		return  sBuilder.toString();
	}

}
