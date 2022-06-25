package lk.ijse.hybernate.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

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
    private String dob;
    private String gender;
}
