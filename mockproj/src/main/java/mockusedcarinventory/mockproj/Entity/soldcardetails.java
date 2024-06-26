package mockusedcarinventory.mockproj.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.sql.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class soldcardetails {
    @Id
    private Integer saleno;
    private Integer inventorynumber;
    private String model;
    private String typeofcar;
    private String color;
    private String adharnumber;
    private String ownername;
    private String email;
    private String phonenumber;
    private Date sellingdate;
}
