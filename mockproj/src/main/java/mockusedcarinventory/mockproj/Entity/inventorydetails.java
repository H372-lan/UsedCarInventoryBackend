package mockusedcarinventory.mockproj.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class inventorydetails {
    @Id
    private Integer inventorynumber;
    private String nearbylocation;
    private String phonenumber;
    private String email;
    @ManyToOne
    @JoinColumn(name = "pincode",nullable = false)
    private city city;
}
