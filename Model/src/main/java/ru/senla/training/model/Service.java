package ru.senla.training.model;


import ru.senla.training.interfaces.model.IGuest;
import ru.senla.training.interfaces.model.IService;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "services")
public class Service implements Serializable,Cloneable,IService {
	@Id
	@GeneratedValue
	private Integer id;
	@Column
	private String section;
	@Column
	private String name;
	@Column
	private Integer price;
	@ManyToOne(fetch = FetchType.LAZY,optional=true)
	@JoinTable(name = "records", joinColumns = @JoinColumn(name = "service_id"), inverseJoinColumns = @JoinColumn(name = "guest_id"))
	private Guest guest;


	public Service(){
		this.id = null;
    }
    public Service(Integer id){
		this.id = id;
	}
	public Service(Integer id, String section, String name, Integer price){
		this.id = id;
		this.section = section;
		this.name = name;
		this.price = price;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	@Override
	public String toString() {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("Service: section = ");
		sBuilder.append(section);
		sBuilder.append(", name = ");
		sBuilder.append(name);
		sBuilder.append(", price = ");
		sBuilder.append(price);
		return sBuilder.toString();
	}
	public boolean equals(IService  service) {
		if(this.getId().equals(service.getId())){
			return true;
		}

		else{ return false;}
	}
	@Override
	public IService clone() throws CloneNotSupportedException {
		Service cloneService = new Service();
		cloneService.setPrice(this.price);
		cloneService.setName(this.name);
		cloneService.setSection(this.section);
		return cloneService;
	}
}
