/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author hiepd
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Invoice implements Serializable {
    
    private String invoiceId;

    private Customer customer;

    private Payment payment;

    private Shipment shipment;

    private String address;

    private Collection<InvoiceProduct> invoiceProducts;

    private int totalAmount;

    private double totalPrice;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date time;


    public int getAmount(Product product) {
        for (InvoiceProduct invoiceProduct : invoiceProducts) {
            if (invoiceProduct.getId().getProductId().equals(product.getProductId())) {
                return invoiceProduct.getAmount();
            }
        }
        return 0;
    }
   
    
    public boolean isNull() {
        return invoiceId == null || invoiceId.equals("null");
    }
    
    public double getTotalProductPrice() {
        double price = 0;
        for (InvoiceProduct invoiceProduct : invoiceProducts) {
            price += invoiceProduct.getTotalPrice();
        }
        return price;
    }
}