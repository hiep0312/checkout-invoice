package entity.statistical;

import entity.Invoice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimeRecord {
    private int month;
    private int year;
    private List<Invoice> invoices;

    public double getTotalIncome() {
        double total = 0;
        for (Invoice invoice : invoices) {
            total += invoice.getTotalPrice();
        }
        return total;
    }

    public String getMonth() {
        return "M" + month + "/" + year;
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

    public int getQuarter() {
        return (month - 1) / 3 + 1;
    }
}
