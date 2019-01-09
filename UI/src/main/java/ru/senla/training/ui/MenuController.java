package ru.senla.training.ui;

import ru.senla.training.exceptions.SomethingWentWrong;
import ru.senla.training.util.PrintToConsole;

public class MenuController {
	private Builder builder;
	private Navigator navigator;

	public MenuController(){
		try {
			builder = new Builder();
			builder.buildMenu();
			navigator = new Navigator(builder.getRootMenu());
		}catch (SomethingWentWrong ex){
			PrintToConsole.printString("Something went wrong during the method work");
		}
	}
	
	public void run() {
		try {
			navigator.printMenu();
			builder.exitProgram();
		}catch (SomethingWentWrong ex){
			PrintToConsole.printString("Something went wrong during the exit");
		}
	}

}
