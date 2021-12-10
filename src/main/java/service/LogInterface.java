package service;

import model.EventDAO;
import model.Event;

import java.util.List;

public interface LogInterface {
    List<Event> parseJson(String path);
    List<EventDAO> saveIntoDb(List<Event> events);

}
