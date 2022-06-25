package lk.ijse.hybernate.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "student")
public class Student {
    @Id
    private String id;
    private String name;
    private  String address;
    private String contactNo;
    @Column(columnDefinition = "DATE")
    private LocalDate dob;
    private String gender;
}
