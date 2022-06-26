package lk.ijse.hybernate.view.tdm;

import javafx.scene.control.Button;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReservationTM {
    private String reserveID;
    private String roomID;
    private String roomType;
    private String studentQty;
    private String keyMoney;
    private String status;
    private Button delete;


}
