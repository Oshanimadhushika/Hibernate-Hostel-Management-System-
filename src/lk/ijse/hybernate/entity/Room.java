package lk.ijse.hybernate.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Entity(name = "room")
public class Room {
    @Id
    private String roomId;
    private String roomType;
    private String keyMoney;
    private int roomQty;


}
