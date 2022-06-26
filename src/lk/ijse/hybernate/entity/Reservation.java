package lk.ijse.hybernate.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Reservation implements SuperEntity{
    @Id
    private String res_id;
    @Column(columnDefinition = "DATE")
    private LocalDate date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id",referencedColumnName = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_type_id",referencedColumnName = "room_type_id")
    private Room room;

    private double key_money;
    private String status;
    private Integer qty;


}
