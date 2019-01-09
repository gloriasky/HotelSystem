package ru.senla.training.ui;

import ru.senla.training.exceptions.SomethingWentWrong;
import ru.senla.training.facade.Hotel;

public class Builder {
	private Menu rootMenu;
	
	public void buildMenu() throws SomethingWentWrong {
	    createItems();
		Hotel.getInstance().start();
	}

    public Menu getRootMenu() {
        return rootMenu;
    }

	private void createItems()throws  SomethingWentWrong{
        rootMenu = new Menu();
        rootMenu.setMenuItems(new MenuItem[25]);
        for (int i = 0; i <  rootMenu.getMenuItems().length; i++) {
            rootMenu.getMenuItems()[i] = new MenuItem(i);
            Searcher.toSearch(i,rootMenu.getMenuItems()[i]);
        }
    }
	public void exitProgram()throws SomethingWentWrong{
	    Hotel.getInstance().end();
    }


}
