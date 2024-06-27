package mockusedcarinventory.mockproj.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class city {
    @NotBlank(message = "Cityname  is mandatory")
    private String cityname;
    @NotBlank(message = "Statename  is mandatory")
    private String statename;
    @NotBlank(message = "Country  is mandatory")
    private String country;
    @Id
    @Pattern(regexp = "\\d{6}",message = "Enter Valid Pincode")
    private String pincode;

}
