package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InvoiceProduct implements Serializable {
    InvoiceProductKey id;

    private int amount;

    private double totalPrice;

    @JsonIgnore
    private Invoice invoice;

    private Product product;
}
