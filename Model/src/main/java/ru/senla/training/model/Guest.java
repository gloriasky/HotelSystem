package ru.senla.training.model;

import ru.senla.training.interfaces.model.IGuest;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;



@Entity
@Table(name = "guests")
public class Guest implements  Cloneable, IGuest {
    @Id
	@GeneratedValue
	private Integer id;
    @Column(name = "first_name")
	private String firstName;
    @Column(name = "last_name")
    private String lastName;
	@Column(name = "release_date")
	@Temporal(TemporalType.DATE)
	private Date dateOfRelease;
	@Temporal(TemporalType.DATE)
	@Column(name = "arrival_date")
	private Date arrivalDate;
	@OneToMany(mappedBy = "guest", fetch = FetchType.LAZY)
	private Set<Service> services;


	public Guest(){
		this.id = null;
    }
	public Guest(String firstName, String lastName) {
		this.id = null;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public Guest(Integer id, String firstName, String lastName, Date dateOfRelease) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfRelease = dateOfRelease;
    }
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDateOfRelease() {
		return dateOfRelease;
	}
	public void setDateOfRelease(Date dateOfRelease) {
		this.dateOfRelease = dateOfRelease;
	}
public Date getArrivalDate() {
        return arrivalDate;
    }
    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }
	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
    public String toString() {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("Guest: ");
		sBuilder.append(firstName);
		sBuilder.append(" ");
		sBuilder.append(lastName);
		sBuilder.append("\nArrival date = ");
		sBuilder.append(arrivalDate.toString());
		sBuilder.append("\ndate Of Release = ");
		sBuilder.append(dateOfRelease.toString());
		return sBuilder.toString();
	}
	public boolean equals(IGuest  guest) {
		if(this.getId().equals(guest.getId())){
					return true;
				}

		else{ return false;}
	}
	public IGuest clone() {
		Guest cloneGuest = new Guest();
		cloneGuest.setFirstName(this.getFirstName());
		cloneGuest.setLastName(this.getLastName());
		cloneGuest.setDateOfRelease(this.getDateOfRelease());
		return cloneGuest;
	}

}
