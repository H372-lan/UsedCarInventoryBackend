package mockusedcarinventory.mockproj.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class cardetails {
    @Id
    private int saleno;
    private int inventorynumber;
    private int kmdriven;
    private Date mfd;
    private String typeofcar;
    private String color;
    private int milage;
    private String model;
    private String pincode;
    private Timestamp recordcreatedtime;
}
