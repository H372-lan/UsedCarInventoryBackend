package mockusedcarinventory.mockproj.Component;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NewInventoryDetails {
    @Pattern(regexp = "\\d{6}",message = "Enter Valid Pincode")
    @NotBlank(message = "Pincode is mandatory")
    private String pincode;
    @NotBlank(message = "Near by location  is mandatory")
    private String nearbylocation;
    @Pattern(regexp = "^\\d{10}$",message = "Enter Valid Phonenumber")
    @NotBlank(message = "Phonenumber is mandatory")
    private String phonenumber;
    @Email(message = "EmailId should be valid")
    @NotBlank(message = "Email is mandatory")
    private String email;
}
