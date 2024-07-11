package mockusedcarinventory.mockproj.Component;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;


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
    private String nearByLocation;
    @Pattern(regexp = "^\\d{10}$",message = "Enter Valid Phonenumber")
    @NotBlank(message = "Phonenumber is mandatory")
    private String phoneNumber;
    @Email(message = "EmailId should be valid")
    @NotBlank(message = "Email is mandatory")
    private String email;

}
