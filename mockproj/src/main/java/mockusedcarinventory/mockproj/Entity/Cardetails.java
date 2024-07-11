package mockusedcarinventory.mockproj.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="car_details")
public class Cardetails {
    @Id

    @Column(name="sale_no")
    private int saleNo;
    @Column(name="inventory_number")
    private int inventoryNumber;
    @Column(name="km_driven")
    private int kmDriven;
    @Column(name="mfd")
    private Date mfd;
    @Column(name="type_car")
    private String typeOfCar;
    @Column(name="color")
    private String color;
    @Column(name="milage")
    private int milage;
    @Column(name="make")
    private String make;
    @Column(name="model")
    private String model;
    @Column(name="pincode")
    private String pincode;
    @Column(name="record_created_time")
    private Timestamp recordCreatedTime;
}
