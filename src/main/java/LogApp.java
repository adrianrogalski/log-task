import model.Event;
import model.EventDAO;
import org.hibernate.SessionFactory;
import service.LogInterface;
import service.LogInterfaceApi;
import util.HibernateUtil;
import java.util.List;

public class LogApp {
    public static void main(String[] args) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        LogInterface logInterface = new LogInterfaceApi(factory);
        String path = args[0];
        List<Event> extendedEvents = logInterface.parseJson(path);
        System.out.println("Events loaded from logfile.txt: ");
        extendedEvents.forEach(System.out::println);
        System.out.println("Events saved into the database: ");
        List<EventDAO> eventDAOS = logInterface.saveIntoDb(extendedEvents);
        eventDAOS.forEach(System.out::println);
        System.out.println("events with an alert: ");
        eventDAOS.stream().filter(EventDAO::isAlert).forEach(System.out::println);

    }
}
