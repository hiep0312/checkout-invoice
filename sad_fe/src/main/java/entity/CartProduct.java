package entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CartProduct {
    CartProductKey id;
    private int amount;
}
