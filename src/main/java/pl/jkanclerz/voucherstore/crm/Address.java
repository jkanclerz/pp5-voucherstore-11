package pl.jkanclerz.voucherstore.crm;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Data
@NoArgsConstructor
public class Address {
    private String street;
    private String zip;
    private String city;
}
