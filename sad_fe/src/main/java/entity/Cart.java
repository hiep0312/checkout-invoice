package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart implements Serializable {
    private String cartId;

    private Customer customer;

    private Set<Product> products;

    private Set<CartProduct> cartProducts;


    public int getAmount(Product product) {
        for (CartProduct cartProduct : cartProducts) {
            if (cartProduct.getId().getProductId().equals(product.getProductId())) {
                return cartProduct.getAmount();
            }
        }
        return 0;
    }
    
    
    public int getTotalAmount() {
        int res = 0;
        for (Product product : products) {
            res += getAmount(product);
        }
        return res;
    }
    
    public double getTotalPrice() {
        double res = 0;
        for (Product product : products) {
            res += getAmount(product) * product.getPrice();
        }
        return res;
    }

}