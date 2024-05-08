/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.statistical;

import entity.Customer;
import entity.Invoice;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author hiepd
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRecord {
    private Customer customer;
    private List<Invoice> invoices;


    public double getTotalIncome() {
        double total = 0;
        for (Invoice invoice : invoices) {
            total += invoice.getTotalPrice();
        }
        return total;
    }

    public int getTotalInvoice() {
        return invoices.size();
    }

    public int getTotalProduct() {
        int total = 0;
        for (Invoice invoice : invoices) {
            total += invoice.getTotalAmount();
        }
        return total;
    }
}
