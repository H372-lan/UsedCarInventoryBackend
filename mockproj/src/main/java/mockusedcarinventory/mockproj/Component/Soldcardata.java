package mockusedcarinventory.mockproj.Component;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Soldcardata {
    @NotNull(message = "Inventory Number Field is Mandatory")
    private Integer inventorynumber;
    @NotBlank(message = "Model Field is Mandatory")
    private String model;
    @NotBlank(message = "Type of Car Field is Mandatory")
    private String typeofcar;
    @NotBlank(message = "Color of Car is Mandatory")
    private String color;
    @Pattern(regexp = "\\d{12}",message = "Enter Valid Adhar Number")
    @NotBlank(message = "Adhar number Field is Mandatory")
    private String adharnumber;
    @NotBlank(message = "Owner Name is Mandatory")
    private String ownername;
    @Email(message = "Enter Valid Email Id")
    @NotBlank(message = "Email Id field Can't be null")
    private String email;
    @Pattern(regexp = "^\\d{10}$",message = "Enter Valid Phone number")
    @NotBlank(message = "Phone number is Mandatory")
    private String phonenumber;
    @NotNull(message = "Selling Date is Mandatory")
    private Date sellingdate;
}
