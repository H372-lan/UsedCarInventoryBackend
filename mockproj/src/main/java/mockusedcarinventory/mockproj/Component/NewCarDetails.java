package mockusedcarinventory.mockproj.Component;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.sql.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NewCarDetails {
    @NotBlank(message = "Inventory number Fiels is mandatory")
    private String inventoryNumber;
    @NotNull(message = "KM Driven Field is Mandatory")
    @Min(value = 0,message = "Km Driven Can't be less than 0")
    private int kmDriven;
    @NotNull(message = "Manufacture Date is Mandatory")
    private Date mfd;
    @NotBlank(message = "Type Of Car Fiels is mandatory")
    private String typeOfCar;
    @NotBlank(message = "Enter color of Car")
    private String color;
    @NotNull(message = "Enter milage of Car")
    @Min(value = 1,message = "Milage Can't less than 1")
    private int milage;
    @NotBlank(message = "Enter make of Car")
    private String make;
    @NotBlank(message = "Enter model of Car")
    private String model;
    @Pattern(regexp = "\\d{6}",message = "Enter Valid Pincode")
    @NotBlank(message = "Pincode is Mandatory")
    private String pincode;
}
