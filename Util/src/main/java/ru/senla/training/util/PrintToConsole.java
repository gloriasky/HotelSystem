package ru.senla.training.util;
import java.util.List;

import ru.senla.training.interfaces.model.IGuest;
import ru.senla.training.interfaces.model.IRoom;
import ru.senla.training.interfaces.model.IService;
import ru.senla.training.interfaces.model.Status;

public class PrintToConsole {
	public static void printString(String text) {
		System.out.println(text);
	}

	public static void printList(List<Object> arrayList) {
		for(int i = 0; i<arrayList.size();i++) {
			if(arrayList.get(i)!=null){
				printString(arrayList.get(i).toString());
			}
		}
	}
	public static void printListOfGuests(List<IGuest> arrayList) {
		for(int i = 0; i<arrayList.size();i++) {
			if(arrayList.get(i)!=null){
				printString(arrayList.get(i).toString());
			}
		}
	}
	public static void printListOfRooms(List<IRoom> arrayList) {
		for(int i = 0; i<arrayList.size();i++) {
			try {
				if (arrayList.get(i) != null) {
					printString(arrayList.get(i).toString());
				}
			}catch(Exception ex){

			}
		}
	}
	public static void printListOfServices(List<IService> arrayList) {
		for(int i = 0; i<arrayList.size();i++) {
			if(arrayList.get(i)!=null){
				printString(arrayList.get(i).toString());
			}
		}
	}
	public static void printRoom(IRoom room, Integer numberOfLastGuests){
		System.out.println("Room № " + room.getId());
		System.out.println("Price: "+room.getPrice()+"				Capacity: "+ room.getCapacity());
		System.out.println("Number of stars: "+ room.getNumberOfStars());
		System.out.println("Status: " + room.getStatus().toString().toLowerCase());
//		if(room.getStatus()== Status.BUSY&&room.getGuest()!=null){
//			System.out.println("Guest right now: "+ room.getGuest().getFirstName() +" "+room.getGuest().getLastName());
//			System.out.println("Busy till: "+ DateConvertors.dateToString(room.getGuest().getDateOfRelease()));
//		}
//		System.out.println("Last guests: ");
//		int i = 1;
//		while(i<numberOfLastGuests&&i<=room.getLastGuests().size()){
//			System.out.println(room.getLastGuests().get(room.getLastGuests().size()-i).getFirstName()+
//					room.getLastGuests().get(room.getLastGuests().size()-i).getLastName()+
//					DateConvertors.dateToString(room.getLastGuests().get(room.getLastGuests().size()-i).getDateOfRelease()));
//			i++;
//		}
	}
	private static void printGuest(IGuest guest){
		printString("Guest: "+ guest.getLastName() + " " + guest.getFirstName());
		printString( "            Date of release: "+ DateConvertors.dateToString(guest.getDateOfRelease()));
		printString("Services: ");
//		printListOfServices(guest.getService());
	}
}
