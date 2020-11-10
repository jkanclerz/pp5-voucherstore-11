package pl.jkanclerz.voucherstore.crm;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue
    private Integer id;

    private String firstname;
    private String lastname;

    @Embedded
    @NotNull
    private Address address;
}
