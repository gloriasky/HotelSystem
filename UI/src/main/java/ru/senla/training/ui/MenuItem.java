package ru.senla.training.ui;

import ru.senla.training.exceptions.SomethingWentWrong;


public class MenuItem {
	private final int index;
	private String title;
	private IAction action;
	private Menu nextMenu;

	public MenuItem(int index){
		this.index = index;
	}

	public void doAction() throws SomethingWentWrong {
		action.execute();
	}
	public int getIndex(){
		return index;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public IAction getAction() {
		return action;
	}

	public void setAction(IAction action) {
		this.action = action;
	}


}
