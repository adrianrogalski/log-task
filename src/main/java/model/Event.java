package model;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Event {
    private String id;
    private String state;
    private String timestamp;
    private String type;
    private String host;
}
