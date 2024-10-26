package web.labos.project.dto;


import jakarta.validation.constraints.NotNull;

public class CreateTicketDTO {

    @NotNull(message = "VATIN (OIB) is required")
    private Long vatin;
    @NotNull(message = "First name is required")
    private String firstName;
    @NotNull(message = "Last name is required")
    private String lastName;

    public Long getVatin() {
        return vatin;
    }

    public void setVatin(Long vatin) {
        this.vatin = vatin;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
