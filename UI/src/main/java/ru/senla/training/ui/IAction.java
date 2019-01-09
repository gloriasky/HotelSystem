package ru.senla.training.ui;

import ru.senla.training.exceptions.SomethingWentWrong;

public interface IAction {
	public void execute() throws SomethingWentWrong;
}
