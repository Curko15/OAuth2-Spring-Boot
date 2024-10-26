package web.labos.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.UuidGenerator;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "Ticket")
public class Ticket {

    @Id
    @UuidGenerator
    private UUID id;
    @NotNull
    private Long vatin;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private Timestamp creationTime;

    public Ticket(UUID id, Timestamp creationTime, String lastName, String firstName, Long vatin) {
        this.id = id;
        this.creationTime = creationTime;
        this.lastName = lastName;
        this.firstName = firstName;
        this.vatin = vatin;
    }

    public Ticket() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public @NotNull Long getVatin() {
        return vatin;
    }

    public void setVatin(@NotNull Long vatin) {
        this.vatin = vatin;
    }

    public @NotNull String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotNull String firstName) {
        this.firstName = firstName;
    }

    public @NotNull String getLastName() {
        return lastName;
    }

    public void setLastName(@NotNull String lastName) {
        this.lastName = lastName;
    }

    public @NotNull Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(@NotNull Timestamp creationTime) {
        this.creationTime = creationTime;
    }
}
