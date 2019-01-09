package ru.senla.training.ui;

import org.apache.log4j.Logger;
import ru.senla.training.exceptions.SomethingWentWrong;
import ru.senla.training.util.PrintToConsole;

import java.util.Scanner;

public class Navigator {
	private Menu currentMenu;
	public Navigator(Menu menu){
	    currentMenu = menu;
    }
	public void printMenu() {
		System.out.println("Choose what you want to do:");
		for(int i = 0; i<currentMenu.getMenuItems().length;i++){
			MenuItem currentMenuItem = currentMenu.getMenuItems()[i];
			PrintToConsole.printString((currentMenuItem.getIndex()+1) +". " + currentMenuItem.getTitle());
		}
		Scanner scanner = new Scanner(System.in);
		int menuIndex = scanner.nextInt();
		navigate(menuIndex-1);
	}
	public void navigate(Integer index) {
		for (int i = 0; i < currentMenu.getMenuItems().length; i++) {
			try {
				if (index == currentMenu.getMenuItems()[i].getIndex()) {
					PrintToConsole.printString((currentMenu.getMenuItems()[i].getIndex() + 1) + ". " + currentMenu.getMenuItems()[i].getTitle());
					currentMenu.getMenuItems()[i].doAction();
				}
			}catch (SomethingWentWrong ex){
				PrintToConsole.printString(("There is a mistake "+ex.getMessage() +" in " +(currentMenu.getMenuItems()[i].getIndex() + 1)));
			} catch (Exception ex){
					PrintToConsole.printString(("There is a mistake "+ex.getMessage() +" in " +(currentMenu.getMenuItems()[i].getIndex() + 1)));
					Logger logger = Logger.getLogger(Navigator.class);
					logger.error(ex);
                }

			}
		}

	public Menu getCurrentMenu() {
		return currentMenu;
	}
	public void setCurrentMenu(Menu currentMenu) {
		this.currentMenu = currentMenu;
	}
}
