package model;
import lombok.*;
import javax.persistence.*;
import java.util.UUID;
@Builder
@AllArgsConstructor
@Getter
@Entity
@Table(name = "events")
public class EventDAO {
    @Id
    private UUID id;
    private String eventId;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "attributes_id")
    private EventAttributes attributes;
    private long eventDuration;
    private boolean alert;

    public EventDAO() {
        this.id = UUID.randomUUID();
    }

    public EventDAO(String eventId, long eventDuration) {
        this.id = UUID.randomUUID();
        this.eventId = eventId;
        this.eventDuration = eventDuration;
        if (eventDuration > 4) {
            alert = true;
        }
    }

    public void addAttribute(EventAttributes eventAttribute) {
            attributes = eventAttribute;
    }

    @Override
    public String toString() {
        String report =  "eventId: '" + eventId +
                ", eventDuration: " + eventDuration +
                ", alert: " + alert;
        if (!(attributes.getType() == null || attributes.getHost() == null)) {
            return report.concat(", host name: " + attributes.getHost() + ", type: " + attributes.getType());
        }
        return report;
    }
}
