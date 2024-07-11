package mockusedcarinventory.mockproj.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="City")
public class City {
    @Id
    @Column(name="pincode")
    @Pattern(regexp = "\\d{6}",message = "Enter Valid Pincode")
    private String pincode;
    @Column(name="city_name")
    @NotBlank(message = "Cityname  is mandatory")
    private String cityName;
    @Column(name="state_name")
    @NotBlank(message = "Statename  is mandatory")
    private String stateName;
    @Column(name="country")
    @NotBlank(message = "Country  is mandatory")
    private String country;


}
