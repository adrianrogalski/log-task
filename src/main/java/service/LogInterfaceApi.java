package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.EventAttributes;
import model.EventDAO;
import model.Event;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class LogInterfaceApi implements LogInterface {
    private final SessionFactory factory;

    public LogInterfaceApi(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<Event> parseJson(String path) {
        ObjectMapper mapper = new ObjectMapper();
        FileInputStream inputStream = null;
        Scanner sc = null;
        List<Event> events = new LinkedList<>();

        try {
            inputStream = new FileInputStream(path);
            sc = new Scanner(inputStream, StandardCharsets.UTF_8);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                events.add(mapper.readValue(line, Event.class));
            }
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
            return events;
        }
        catch (IOException e) {
            System.err.println();
        }
        finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (sc != null) {
                sc.close();
            }
        }
        return Collections.emptyList();
    }

    @Override
    public List<EventDAO> saveIntoDb(List<Event> events) {
        List<EventDAO> eventDAOS = new ArrayList<>();
        for (Event event : events) {
            EventDAO eventDAO = new EventDAO(event.getId(), eventsDuration(events).get(event.getId()));
            if (!event.getState().isEmpty() || !event.getHost().isEmpty()){
                EventAttributes attributes = new EventAttributes(event.getHost(), event.getType());
                eventDAO.addAttribute(attributes);
            }
            eventDAOS.add(eventDAO);
        }
        save(eventDAOS);
        return eventDAOS;
    }

    private void save(List<EventDAO> eventDAOS) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        for (EventDAO eventDAO : eventDAOS) {
            session.save(eventDAO);
        }
        transaction.commit();
        session.close();
    }

    private static Map<String, Long> eventsDuration(List<Event> events) {
        Map<String, Long> duration = new HashMap<>();
        for (Event event : events) {
            long deltaTime = 0;
            if (event.getState().equals("STARTED")) {
                deltaTime = -Long.parseLong(event.getTimestamp());
                for (Event lastEvent : events) {
                    if (lastEvent.getId().equals(event.getId()) && lastEvent.getState().equals("FINISHED")) {
                        deltaTime += Long.parseLong(lastEvent.getTimestamp());
                        duration.put(event.getId(), deltaTime);
                    }
                }
            }
        }
        return duration;
    }
}

