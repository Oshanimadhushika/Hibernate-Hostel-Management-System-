package lk.ijse.hybernate.dto;


import lombok.*;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentDTO {

    private String studentID;
    private String studentName;
    private String address;
    private String contactNo;
    private LocalDate dob;
    private String gender;



}
