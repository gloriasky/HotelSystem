package ru.senla.training.ui;

public class Menu {
	private String name;
	private MenuItem[] menuItems;
	
	public MenuItem[] getMenuItems() {
		return menuItems;
	}
	public void setMenuItems(MenuItem[] menuItems) {
		this.menuItems = menuItems;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
