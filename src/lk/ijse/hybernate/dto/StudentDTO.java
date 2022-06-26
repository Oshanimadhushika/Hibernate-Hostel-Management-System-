package lk.ijse.hybernate.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentDTO {
    private String studentID;
    private String studentName;
    private String address;
    private String contactNo;
    private String dob;
    private String gender;
}
