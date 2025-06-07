package org.example.healthproject.doctor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DoctorDTO {
    @NotBlank
    @Size(max = 50)
    private String firstName;

    @NotBlank
    @Size(max = 50)
    private String lastName;

    @NotBlank
    @Size(max = 100)
    private String specialty;

    @NotBlank
    @Email
    @Size(max = 100)
    private String email;

    @NotNull
    private Long clinicId;
}
