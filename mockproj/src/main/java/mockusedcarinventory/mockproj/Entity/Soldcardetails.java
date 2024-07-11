package mockusedcarinventory.mockproj.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="soldcar_details")
public class Soldcardetails {
    @Id
    @Column(name="serial_no")
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name="sale_no")
    private Integer saleNo;
    @Column(name="inventory_number")
    private Integer inventoryNumber;
    @Column(name="model")
    private String model;
    @Column(name="type_car")
    private String typeOfCar;
    @Column(name="color")
    private String color;
    @Column(name="adhar_number")
    private String adharNumber;
    @Column(name="owner_name")
    private String ownerName;
    @Column(name="email")
    private String email;
    @Column(name="phonenumber")
    private String phoneNumber;
    @Column(name="selling_date")
    private Date sellingDate;
}
