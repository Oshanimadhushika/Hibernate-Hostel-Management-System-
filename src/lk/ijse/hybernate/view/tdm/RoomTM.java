package lk.ijse.hybernate.view.tdm;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoomTM {
    private String roomID;
    private String roomType;
    private String keyMoney;
    private String roomQty;

}
