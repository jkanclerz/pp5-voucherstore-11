package pl.jkanclerz.voucherstore.sales.ordering;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Embeddable
public class ClientData {
    String firstname;
    String lastname;
    String email;

}
