package lk.ijse.hybernate.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "reservation")
public class Reservation implements SuperEntity{
    @Id
    private String res_id;
    @Column(columnDefinition = "DATE")
    private LocalDate date;

    @ManyToMany(cascade = CascadeType.ALL)
    @Column(nullable = false)
    @JoinColumn(name = "student_id")
    private String student_id;

    @ManyToMany(cascade = CascadeType.ALL)
    @Column(nullable = false)
    @JoinColumn(name = "room_type_id")
    private String room_type_id;

    private double key_money;
    private String status;
    private Integer qty;


}
