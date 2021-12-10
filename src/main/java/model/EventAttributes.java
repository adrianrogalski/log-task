package model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@EqualsAndHashCode
@Entity
@Table(name = "attributes")
@AllArgsConstructor
@ToString
public class EventAttributes {
    @Id
    private UUID id;
    private String host;
    private String type;
    @OneToOne(mappedBy = "attributes")
    private EventDAO eventDAO;

    public EventAttributes() {
        this.id = UUID.randomUUID();
    }

    public EventAttributes(String host, String type) {
        this.id = UUID.randomUUID();
        this.host = host;
        this.type = type;
    }
}
