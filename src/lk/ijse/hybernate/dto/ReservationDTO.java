package lk.ijse.hybernate.dto;


import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReservationDTO {
    private String res_id;
    private LocalDate date;
    private String studentID;
    private String roomID;
    private double key_money;
    private String status;
    private Integer qty;

}
