package ru.senla.training.ui;

import ru.senla.training.exceptions.SomethingWentWrong;

public class Searcher {
    public static void toSearch(int index, MenuItem thisMenuItem) throws SomethingWentWrong {
        switch(index){
            case 0:
                thisMenuItem.setAction(MenuOptions.printRooms());
                thisMenuItem.setTitle("Print list of rooms; ");
                break;
            case 1:
                thisMenuItem.setAction(MenuOptions.printServices());
                thisMenuItem.setTitle("Print list of services; ");
                break;
            case 2:
                thisMenuItem.setAction(MenuOptions.printGuests());
                thisMenuItem.setTitle("Print list of guests; ");
                break;
            case 3:
                thisMenuItem.setAction(MenuOptions.numberOfFreeRooms());
                thisMenuItem.setTitle("Print number of free rooms; ");
                break;
            case 4:
                thisMenuItem.setAction(MenuOptions.numberOfGuests());
                thisMenuItem.setTitle("Print number of guests; ");
                break;
            case 5:
                thisMenuItem.setAction(MenuOptions.freeByDate());
                thisMenuItem.setTitle("Print list of rooms, which will be free by the date; ");
                break;
            case 6:
                thisMenuItem.setAction(MenuOptions.summary());
                thisMenuItem.setTitle("Print check; ");
                break;
            case 7:
                thisMenuItem.setAction(MenuOptions.showServicesOfTheGuest());
                thisMenuItem.setTitle("Print services of the guest; ");
                break;
            case 8:
                thisMenuItem.setAction(MenuOptions.lastGuests());
                thisMenuItem.setTitle("Print detailed information of the room; ");
                break;
            case 9:
                thisMenuItem.setAction(MenuOptions.setGuest());
                thisMenuItem.setTitle("Set guest; ");
                break;
            case 10:
                thisMenuItem.setAction(MenuOptions.evictGuest());
                thisMenuItem.setTitle("Evict guest;  ");
                break;
            case 11:
                thisMenuItem.setAction(MenuOptions.changeStatus());
                thisMenuItem.setTitle("Change status; ");
                break;
            case 12:
                thisMenuItem.setAction(MenuOptions.changeRoomPrice());
                thisMenuItem.setTitle("Change room price;  ");
                break;
            case 13:
                thisMenuItem.setAction(MenuOptions.changeServicePrice());
                thisMenuItem.setTitle("Change service price ");
                break;
            case 14:
                thisMenuItem.setAction(MenuOptions.addRoom());
                thisMenuItem.setTitle("Add room;  ");
                break;
            case 15:
                thisMenuItem.setAction(MenuOptions.addService());
                thisMenuItem.setTitle("Add service; ");
                break;
            case 16:
                thisMenuItem.setAction(MenuOptions.addGuest());
                thisMenuItem.setTitle("Add guest; ");
                break;
            case 17:
                thisMenuItem.setAction(MenuOptions.sortRoomByNumber());
                thisMenuItem.setTitle("Sort rooms by number ");
                break;
            case 18:
                thisMenuItem.setAction(MenuOptions.sortServiceByNumber());
                thisMenuItem.setTitle("Sort services by number");
                break;
            case 19:
                thisMenuItem.setAction(MenuOptions.sortByAlphabet());
                thisMenuItem.setTitle("Sort services by alphabet");
                break;
            case 20:
                thisMenuItem.setAction(MenuOptions.sortByCapasity());
                thisMenuItem.setTitle("Sort rooms by capacity ");
                break;
            case 21:
                thisMenuItem.setAction(MenuOptions.sortByDate());
                thisMenuItem.setTitle("Sort guests by date ");
                break;
            case 22:
                thisMenuItem.setAction(MenuOptions.sortByNumberOfStars());
                thisMenuItem.setTitle("Sort rooms by number of stars ");
                break;
            case 23:
                thisMenuItem.setAction(MenuOptions.sortRoomByPrice());
                thisMenuItem.setTitle("Sort rooms by price");
                break;
            case 24:
                thisMenuItem.setAction(MenuOptions.sortServiceByPrice());
                thisMenuItem.setTitle("Sort services by price ");
                break;
        }
    }
}
