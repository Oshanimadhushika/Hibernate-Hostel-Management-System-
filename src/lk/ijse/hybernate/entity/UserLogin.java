package lk.ijse.hybernate.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "userlogin")
public class UserLogin {
    @Id
    private String user_id;
    @Column(nullable = false)
    private String user_name;
    @Column(nullable = false)
    private String password;

}
