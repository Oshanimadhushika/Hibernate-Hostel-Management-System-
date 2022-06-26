package lk.ijse.hybernate.view.tdm;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentTM {
    private String studentID;
    private String studentName;
    private String address;
    private String contactNo;
    private String dob;
    private String gender;
}
