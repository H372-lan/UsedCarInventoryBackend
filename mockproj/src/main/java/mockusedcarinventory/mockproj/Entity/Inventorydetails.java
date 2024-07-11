package mockusedcarinventory.mockproj.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="inventory_details")
public class Inventorydetails {
    @Id
    @Column(name="inventory_number")
    private Integer inventoryNumber;
    @Column(name="near_by_location")
    private String nearByLocation;
    @Column(name="phonenumber")
    private String phoneNumber;
    @Column(name="email")
    private String email;
    @ManyToOne
    @JoinColumn(name = "pincode",nullable = false)
    private City city;
}
