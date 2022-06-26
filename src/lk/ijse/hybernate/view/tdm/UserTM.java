package lk.ijse.hybernate.view.tdm;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserTM {
    private String userID;
    private String userName;
    private String password;

}
