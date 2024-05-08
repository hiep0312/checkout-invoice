package entity;

import entity.statistical.TimeRecord;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimeRecordGroup {
    private String time;
    private List<TimeRecord> timeRecords;

    public double getTotalIncome() {
        double total = 0;
        for (TimeRecord timeRecord : timeRecords) {
            total += timeRecord.getTotalIncome();
        }
        return total;
    }

    public int getTotalProduct() {
        int total = 0;
        for (TimeRecord timeRecord : timeRecords) {
            total += timeRecord.getTotalProduct();
        }
        return total;
    }

    public int getTotalInvoice() {
        int total = 0;
        for (TimeRecord timeRecord : timeRecords) {
            total += timeRecord.getTotalInvoice();
        }
        return total;
    }

    public List<Invoice> getInvoices() {
        List<Invoice> invoices = new ArrayList<>();
        for (TimeRecord timeRecord : timeRecords) {
            invoices.addAll(timeRecord.getInvoices());
        }
        return invoices;
    }
}
