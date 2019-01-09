package ru.senla.training.interfaces.managers;

import ru.senla.training.exceptions.SomethingWentWrong;

public interface IManager {
     Long getCount() throws SomethingWentWrong;
}
