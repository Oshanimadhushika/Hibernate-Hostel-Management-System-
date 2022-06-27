package lk.ijse.hybernate.dto;


import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentDTO {

    private String studentID;
    private String studentName;
    private String address;
    private String contactNo;
    private String dob;
    private String gender;


}
