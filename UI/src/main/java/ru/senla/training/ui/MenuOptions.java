package ru.senla.training.ui;

import ru.senla.training.exceptions.SomethingWentWrong;
import ru.senla.training.facade.Hotel;
import ru.senla.training.interfaces.model.*;
import ru.senla.training.model.Guest;
import ru.senla.training.model.Room;
import ru.senla.training.model.Service;
import ru.senla.training.interfaces.model.Status;
import ru.senla.training.util.PrintToConsole;
import ru.senla.training.util.DateConvertors;
import java.util.List;
import java.util.Scanner;

public class MenuOptions{
    private static Hotel hotel = Hotel.getInstance();

    private static void printListOfRooms(List<IRoom> rooms ) throws SomethingWentWrong {
        if(rooms!=null) {
            PrintToConsole.printListOfRooms(rooms);
        }
        else{
            throw new SomethingWentWrong();
        }
    }
    private static void printListOfGuests(List<IGuest> guests) throws SomethingWentWrong {
        if(guests!=null) {
            PrintToConsole.printListOfGuests(guests);
        }
        else{
            throw new SomethingWentWrong();
        }
    }
    private static void printListOfServices(List<IService> services) throws SomethingWentWrong {
        if(services!=null) {
            PrintToConsole.printListOfServices(services);
        }
        else{
            throw new SomethingWentWrong();
        }
    }

    public static IAction printRooms(){
        return new IAction() {
            @Override
            public void execute() throws SomethingWentWrong {
                printListOfRooms(hotel.getRooms());
            }
        };
    }
    public static IAction printGuests() {
        return new IAction() {
            @Override
            public void execute() throws SomethingWentWrong {
            printListOfGuests(hotel.getGuests());
            }
        };
    }
    public static IAction numberOfFreeRooms(){
        return new IAction() {
            @Override
            public void execute() throws SomethingWentWrong {
                PrintToConsole.printString("Number of free rooms: "+hotel.numberOfFreeRooms());
            }
        };
    }
    public static IAction numberOfGuests(){
        return new IAction() {
            @Override
            public void execute() throws SomethingWentWrong {
                Long numberOfGuests = hotel.numberOfGuests();
                if(numberOfGuests!=null) {
                    PrintToConsole.printString("Number of guests: " + numberOfGuests);
                }
                else {
                    throw new SomethingWentWrong();
                }
            }
        };
    }
    public static IAction freeByDate(){
        return new IAction() {
            @Override
            public void execute() throws SomethingWentWrong {
                Scanner scanner = new Scanner(System.in);
                PrintToConsole.printString("Input date");
                String data = scanner.next();
                List<IRoom> rooms = hotel.freeByTheDate(DateConvertors.stringToDate(data));
                printListOfRooms(rooms);
            }
        };
    }
    public static IAction summary() throws SomethingWentWrong  {
        return new IAction() {
            @Override
            public void execute() throws SomethingWentWrong{
                Scanner scanner = new Scanner(System.in);
                PrintToConsole.printString("Input first name:");
                String firstName = scanner.next();
                PrintToConsole.printString("Input last name");
                String lastName = scanner.next();
                Integer sum = hotel.getSum(new Guest(firstName,lastName));
                if(sum!=null) {
                    PrintToConsole.printString("Sum: " +sum);
                }else{
                    throw new SomethingWentWrong();
                }
            }
        };
    }
    public static IAction lastGuests() throws SomethingWentWrong {
        return new IAction() {
            @Override
            public void execute() throws SomethingWentWrong {
                Scanner scanner = new Scanner(System.in);
                PrintToConsole.printString("Input room number");
                int roomNumber = scanner.nextInt();
                printListOfGuests(hotel.getLastGuests(new Room(roomNumber)));
            }
        };
    }
    public static IAction showServicesOfTheGuest(){
        return new IAction() {
            @Override
            public void execute() throws SomethingWentWrong {
                Scanner scanner = new Scanner(System.in);
                PrintToConsole.printString("Input first name");
                String firstName = scanner.next();
                PrintToConsole.printString("Input last name");
                String lastName = scanner.next();
                Guest guest = new Guest(firstName,lastName);

                List<IService> services = hotel.showServices(guest);
                printListOfServices(services);
            }
        };
    }
    public static IAction printServices(){
        return new IAction() {
            @Override
            public void execute() throws SomethingWentWrong {
                List<IService> services = hotel.getServices();
                if(services!=null) {
                    PrintToConsole.printListOfServices(services);
                }
                else{
                    throw new SomethingWentWrong();
                }
            }
        };
    }
    public static IAction setGuest() throws SomethingWentWrong{
        return new IAction() {
            @Override
            public void execute() throws SomethingWentWrong {
                List<IGuest> guests = hotel.getGuests();
                printListOfGuests(guests);
                Scanner scanner = new Scanner(System.in);
                PrintToConsole.printString("Input room number");
                int roomNumber = scanner.nextInt();
                Room room = new Room();
                room.setId(roomNumber);
                PrintToConsole.printString("Input guest number");
                int guestNumber = scanner.nextInt();
                PrintToConsole.printString("Input first name");
                String firstName = scanner.next();
                PrintToConsole.printString("Input last name");
                String lastName = scanner.next();
                PrintToConsole.printString("Input date");
                String date = scanner.next();
                Guest guest = new Guest(guestNumber, firstName, lastName, DateConvertors.stringToDate(date));
                if (!hotel.setGuest(roomNumber, guest)) {
                    throw new SomethingWentWrong();
                }
                printListOfGuests(hotel.getGuests());

            }
        };
    }
    public static IAction evictGuest(){
      return new IAction() {
          @Override
          public void execute() throws SomethingWentWrong{

              printListOfGuests(hotel.getGuests());
              Scanner scanner = new Scanner(System.in);
              PrintToConsole.printString("Input room number");
              int roomNumber = scanner.nextInt();
              PrintToConsole.printString("Input guest number");
              int guestNumber = scanner.nextInt();
              PrintToConsole.printString("Input first name");
              String firstName = scanner.next();
              PrintToConsole.printString("Input last name");
              String lastName = scanner.next();
              PrintToConsole.printString("Input date");
              String date = scanner.next();
              Guest guest = new Guest(guestNumber,firstName,lastName, DateConvertors.stringToDate(date));
              if(!hotel.evictGuest(roomNumber,guest)){
                  throw new SomethingWentWrong();
              }
              printListOfGuests(hotel.getGuests());
          }
      };
    }
    public static IAction changeStatus(){
        return new IAction() {
            @Override
            public void execute() throws SomethingWentWrong{
                Scanner scanner = new Scanner(System.in);
                printListOfRooms(hotel.getRooms());
                PrintToConsole.printString("Input room number");
                int roomNumber = scanner.nextInt();
                PrintToConsole.printString("Input capacity");
                int capacity = scanner.nextInt();
                PrintToConsole.printString("Input number of stars");
                int numberOFStars = scanner.nextInt();
                PrintToConsole.printString("Input price");
                int price = scanner.nextInt();
                PrintToConsole.printString("Input new status");
                String status = scanner.next();
                Room room = new Room(roomNumber,price,capacity,numberOFStars,Status.valueOf(status.toUpperCase()));
                if(!hotel.changeStatus(room)){
                    throw new SomethingWentWrong();
                }
                printListOfRooms(hotel.getRooms());
            }
        };
    }
    public static IAction changeServicePrice(){
        return new IAction() {
            @Override
            public void execute() throws SomethingWentWrong {
                printListOfServices(hotel.getServices());
                Scanner scanner = new Scanner(System.in);
                PrintToConsole.printString("Input section");
                String section = scanner.next();
                PrintToConsole.printString("Input name");
                String name = scanner.next();
                PrintToConsole.printString("Input price");
                Integer price = scanner.nextInt();
                if(!hotel.changeServicePrice(new Service(null,section,name,price))) {
                    throw new SomethingWentWrong();
                }
                printListOfServices(hotel.getServices());
            }
        };
    }
    public static IAction changeRoomPrice(){
        return new IAction() {
            @Override
            public void execute() throws SomethingWentWrong {
                printListOfRooms(hotel.getRooms());
                Scanner scanner = new Scanner(System.in);
                PrintToConsole.printString("Input room number");
                int roomNumber = scanner.nextInt();
                PrintToConsole.printString("Input capacity");
                int capacity = scanner.nextInt();
                PrintToConsole.printString("Input number of stars");
                int numberOFStars = scanner.nextInt();
                PrintToConsole.printString("Input price");
                int newPrice = scanner.nextInt();
                PrintToConsole.printString("Input new status");
                String status = scanner.next();
                Room room = new Room(roomNumber,newPrice,capacity,numberOFStars,Status.valueOf(status.toUpperCase()));
                if(!hotel.changeRoomPrice(room)) {
                    throw new SomethingWentWrong();
                }
                printListOfRooms(hotel.getRooms());
            }
        };
    }
    public static IAction addRoom(){
        return new IAction() {
            @Override
            public void execute() throws SomethingWentWrong {
                printListOfRooms(hotel.getRooms());
                Scanner scanner = new Scanner(System.in);
                PrintToConsole.printString("Input room number");
                Integer roomNumber = scanner.nextInt();
                PrintToConsole.printString("Input price");
                Integer price = scanner.nextInt();
                PrintToConsole.printString("Input capacity");
                Integer capacity = scanner.nextInt();
                PrintToConsole.printString("Input number of stars");
                Integer numberOfStars = scanner.nextInt();
                PrintToConsole.printString("Input status");
                String status = scanner.next();
                if(!hotel.addRoom(new Room(roomNumber,price,capacity,numberOfStars,Status.valueOf(status.toUpperCase())))){
                    throw new SomethingWentWrong();
                }
                printListOfRooms(hotel.getRooms());
            }
        };
    }
    public static  IAction addGuest(){
        return new IAction() {
            @Override
            public void execute() throws SomethingWentWrong {
                printListOfGuests(hotel.getGuests());
                Scanner scanner = new Scanner(System.in);
                PrintToConsole.printString("Input first name");
                String firstName = scanner.next();
                PrintToConsole.printString("Input last name");
                String lastName = scanner.next();
                PrintToConsole.printString("Input date of arriving");
                String arriveDate = scanner.next();
                PrintToConsole.printString("Input date of releasing");
                String releaseDate = scanner.next();
                Guest guest = new Guest(firstName,lastName);
                guest.setArrivalDate(DateConvertors.stringToDate(arriveDate));
                guest.setDateOfRelease(DateConvertors.stringToDate(releaseDate));
                if(!hotel.addGuest(guest)){
                    throw new SomethingWentWrong();
                }
                printListOfGuests(hotel.getGuests());
            }
        };
    }
    public static IAction addService(){
        return new IAction() {
            @Override
            public void execute() throws SomethingWentWrong {
                printListOfServices(hotel.getServices());
                Scanner scanner = new Scanner(System.in);
                PrintToConsole.printString("Input section");
                String section = scanner.next();
                PrintToConsole.printString("Input name");
                String name = scanner.next();
                PrintToConsole.printString("Input price");
                Integer price = scanner.nextInt();
                if(!hotel.addService(new Service(null,section,name,price))){
                    throw new SomethingWentWrong();
                }
                printListOfServices(hotel.getServices());
            }
        };
    }
    public static IAction sortRoomByPrice(){
        return new IAction() {
            @Override
            public void execute() throws SomethingWentWrong {
                printListOfRooms(hotel.sortRoomByPrice());
            }
        };
    }
    public  static IAction sortServiceByPrice(){
        return new IAction() {
            @Override
            public void execute() throws SomethingWentWrong {
                printListOfServices(hotel.sortServiceByPrice());
            }
        };
    }
    public static IAction sortRoomByNumber(){
        return new IAction() {
            @Override
            public void execute() throws SomethingWentWrong {
                printListOfRooms(hotel.getRooms());
            }
        };
    }
    public  static IAction sortServiceByNumber(){
        return new IAction() {
            @Override
            public void execute() throws SomethingWentWrong {
                printListOfServices(hotel.getServices());
            }
        };
    }
    public static IAction sortByNumberOfStars(){
        return new IAction() {
            @Override
            public void execute() throws SomethingWentWrong {
                printListOfRooms(hotel.sortByNumberOfStars());
            }
        };
    }
    public static IAction sortByCapasity(){
        return new IAction() {
            @Override
            public void execute() throws SomethingWentWrong {

                printListOfRooms(hotel.sortByCapacity());
            }
        };
    }
    public static IAction sortByAlphabet(){
        return new IAction() {
            @Override
            public void execute() throws SomethingWentWrong {

                printListOfServices(hotel.sortServiceByAlphabet());
            }
        };
    }
    public static IAction sortByDate(){
        return new IAction() {
            @Override
            public void execute() throws SomethingWentWrong {
                hotel.sortByDate();
                printListOfGuests(hotel.getGuests());
            }
        };
    }
}
