package lk.ijse.hybernate.entity;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Entity(name = "room")
public class Room implements SuperEntity {
    @Id
    private String room_type_id;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private String key_money;
    @Column(nullable = false)
    private int qty;


    @OneToMany(mappedBy = "room")
    @Cascade(CascadeType.ALL)
   List<Reservation> roomDetails=new ArrayList<>();
}
