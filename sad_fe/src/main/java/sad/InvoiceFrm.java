/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package sad;

import Util.Utils;
import com.mashape.unirest.http.JsonNode;
import entity.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;

/**
 * @author hiepd
 */
public class InvoiceFrm extends javax.swing.JFrame {

    List<Shipment> shipments;
    List<Payment> payments;
    Invoice invoice;
    
    /**
     * Creates new form DiscoveryFrm
     */
    public InvoiceFrm() {
        initComponents();

    }
    public InvoiceFrm(Cart cart) {
        initComponents();

        invoice = new Invoice();
        invoice.setCustomer(cart.getCustomer());

        Date date = new Date();
        invoice.setTime(date);

        timeText.setText(Utils.formatTime(date));

        setTableData(cart);
        setBillData(cart);

        fetchData();

        invoice.setInvoiceProducts(createInvoiceProduct(cart));
        invoice.setTotalPrice(invoice.getTotalProductPrice() + invoice.getShipment().getShipmentCost());
        System.out.println(invoice.getInvoiceProducts().toString());

        total.setText(Utils.formatMoney(invoice.getTotalProductPrice() + invoice.getShipment().getShipmentCost()));

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    public InvoiceFrm(Invoice invoice) {
        initComponents();

        this.invoice = invoice;


        setTableData(invoice);
        setBillData(invoice);

        total.setText(Utils.formatMoney(invoice.getTotalPrice()));

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InvoiceFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InvoiceFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InvoiceFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InvoiceFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InvoiceFrm().setVisible(true);
            }
        });
    }

    private Set<InvoiceProduct> createInvoiceProduct(Cart cart) {
        Set<InvoiceProduct> invoiceProducts = new HashSet<>();
        List<Product> products = List.copyOf(cart.getProducts());
        for (Product cd : products) {
            InvoiceProduct invoiceProduct = new InvoiceProduct();
            invoiceProduct.setAmount(cart.getAmount(cd));
            invoiceProduct.setTotalPrice(cart.getAmount(cd) * cd.getPrice());
            invoiceProduct.setProduct(cd);

            invoiceProducts.add(invoiceProduct);
        }
        return invoiceProducts;
    }

    private void fetchData() {
        try {
            JsonNode response = Utils.getRequestParam(Utils.host + ":8090/api/v1/shipment/all", new HashMap<>());
            System.out.println(response.toString());
            shipments = Utils.parseJsonToList(response.toString(), Shipment.class);

            JsonNode response2 = Utils.getRequestParam(Utils.host + ":8090/api/v1/payment/all", new HashMap<>());
            payments = Utils.parseJsonToList(response2.toString(), Payment.class);

            DefaultComboBoxModel<String> shipmentModel = new DefaultComboBoxModel<>();
            DefaultComboBoxModel<String> paymentModel = new DefaultComboBoxModel<>();

            shipmentModel.removeAllElements();
            paymentModel.removeAllElements();

            for (Shipment shipment : shipments) {
                shipmentModel.addElement(shipment.getShipmentName());
            }

            for (Payment payment : payments) {
                paymentModel.addElement(payment.getPaymentMethod());
            }

            shipmentCombo.setModel(shipmentModel);
            paymentCombo.setModel(paymentModel);

            Payment payment = payments.get(paymentCombo.getSelectedIndex());
            invoice.setPayment(payment);

            Shipment shipment = shipments.get(shipmentCombo.getSelectedIndex());
            invoice.setShipment(shipment);

            shipcostText.setText(Utils.formatMoney(shipment.getShipmentCost()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setTableData(Cart cart) {

        DefaultTableModel model = (DefaultTableModel) productTable.getModel();
        model.setRowCount(0);

//        DefaultTableModel model = new DefaultTableModel();
        List<Product> products = List.copyOf(cart.getProducts());
        for (Product product : cart.getProducts()) {
            int amount = cart.getAmount(product);

            Object[] row = {products.indexOf(product), product.getName(), Utils.formatMoney(product.getPrice()), amount, Utils.formatMoney(product.getPrice() * amount)};
            model.addRow(row);
        }

    }

    private void setTableData(Invoice invoice) {

        DefaultTableModel model = (DefaultTableModel) productTable.getModel();
        model.setRowCount(0);

//        DefaultTableModel model = new DefaultTableModel();
        List<InvoiceProduct> products = (List<InvoiceProduct>) invoice.getInvoiceProducts();
        for (InvoiceProduct product : products) {
            Object[] row = {products.indexOf(product),
                    product.getProduct().getName(),
                    Utils.formatMoney(product.getProduct().getPrice()),
                    product.getAmount(),
                    Utils.formatMoney(product.getTotalPrice())};
            model.addRow(row);
        }

    }

    private void setBillData(Cart cart) {
        confirmButton.setVisible(true);
        shipmentCombo.setEnabled(true);
        paymentCombo.setEnabled(true);
        addressField.setText("");


        invoice.setTotalAmount(cart.getTotalAmount());
        invoice.setTotalPrice(cart.getTotalPrice());

        totalAmount.setText(String.valueOf(cart.getTotalAmount()));
        totalProductPrice.setText(Utils.formatMoney(cart.getTotalPrice()));
    }

    private void setBillData(Invoice invoice) {
        confirmButton.setVisible(false);

        shipmentCombo.setEnabled(false);
        paymentCombo.setEnabled(false);
        addressField.setEditable(false);

        shipmentCombo.setSelectedItem(invoice.getShipment().getShipmentName());
        paymentCombo.setSelectedItem(invoice.getPayment().getPaymentMethod());
        addressField.setText(invoice.getAddress());


        shipcostText.setText(Utils.formatMoney(invoice.getTotalPrice() - invoice.getTotalProductPrice()));
        total.setText(Utils.formatMoney(invoice.getTotalPrice()));

        totalAmount.setText(String.valueOf(invoice.getTotalAmount()));
        totalProductPrice.setText(Utils.formatMoney(invoice.getTotalProductPrice()));
    }

    private void confirmInvoice() {
        String address = addressField.getText();
        if (address.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter address");
            return;
        }
        try {
//            System.out.println(invoice.toString());
            invoice.setAddress(address);
            JsonNode response = Utils.postRequest(Utils.host + ":8090/api/v1/invoice/create", new CustomerInvoiceAdapter(invoice.getCustomer(), invoice));

            Invoice newInvoice = Utils.parseJsonToObject(response.toString(), Invoice.class);
            System.out.println(newInvoice);
            if (!newInvoice.isNull()) {
                JOptionPane.showMessageDialog(this, "Invoice created successfully");
                Utils.postRequest(Utils.host + ":8090/api/v1/invoice/confirm-transaction", newInvoice);
            }
            else {
                JOptionPane.showMessageDialog(this, "Invoice created not successfully");
            }

//            this.dispose();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error while creating invoice");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        productTable = new javax.swing.JTable();
        panel1 = new java.awt.Panel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        totalAmount = new javax.swing.JTextArea();
        totalProductPrice = new javax.swing.JTextArea();
        confirmButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        shipcostText = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        shipmentCombo = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        paymentCombo = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        timeText = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        total = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        addressField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        productTable.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        productTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", "Áo dài", "126.000 đ", "5", "630.000 đ"}
            },
            new String [] {
                "STT", "Name", "Price", "Amount", "TotalPrice"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        productTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jScrollPane1.setViewportView(productTable);
        if (productTable.getColumnModel().getColumnCount() > 0) {
            productTable.getColumnModel().getColumn(0).setResizable(false);
            productTable.getColumnModel().getColumn(1).setResizable(false);
            productTable.getColumnModel().getColumn(2).setResizable(false);
            productTable.getColumnModel().getColumn(3).setResizable(false);
            productTable.getColumnModel().getColumn(4).setResizable(false);
        }

        jLabel2.setText("Product price:");

        jLabel3.setText("Total amount:");

        totalAmount.setEditable(false);
        totalAmount.setColumns(20);
        totalAmount.setRows(5);
        totalAmount.setText("5");

        totalProductPrice.setEditable(false);
        totalProductPrice.setColumns(20);
        totalProductPrice.setLineWrap(true);
        totalProductPrice.setRows(5);
        totalProductPrice.setText("630.000 đ");

        confirmButton.setText("Confirm");
        confirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Ship cost:");

        shipcostText.setEditable(false);
        shipcostText.setColumns(20);
        shipcostText.setLineWrap(true);
        shipcostText.setRows(5);
        shipcostText.setText("20.000 đ");

        jLabel4.setText("Ship method:");

        shipmentCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "GHN", "Grap" }));
        shipmentCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shipmentComboActionPerformed(evt);
            }
        });

        jLabel6.setText("Payment method:");

        paymentCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bank", "Cash" }));
        paymentCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentComboActionPerformed(evt);
            }
        });

        jLabel7.setText("Time:");

        timeText.setEditable(false);
        timeText.setColumns(20);
        timeText.setLineWrap(true);
        timeText.setRows(5);
        timeText.setText("12:26 4/14/2024");

        jLabel8.setText("Total:");

        total.setEditable(false);
        total.setColumns(20);
        total.setLineWrap(true);
        total.setRows(5);
        total.setText("650.000 đ");

        jLabel9.setText("Address:");

        addressField.setText("Ha Noi");

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(confirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(96, 96, 96))
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(shipcostText, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(totalProductPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(totalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(shipmentCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(panel1Layout.createSequentialGroup()
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel1Layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addGap(18, 18, 18)
                                    .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(paymentCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(timeText, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(addressField)))))))
                .addGap(19, 19, 19))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(totalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(totalProductPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(shipmentCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(shipcostText, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addressField))
                .addGap(15, 15, 15)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(paymentCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(timeText, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addComponent(confirmButton)
                .addGap(22, 22, 22))
        );

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Invoice");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
                    .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(55, 55, 55))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void confirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmButtonActionPerformed
        // TODO add your handling code here:

        confirmInvoice();
    }//GEN-LAST:event_confirmButtonActionPerformed

    private void shipmentComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shipmentComboActionPerformed
        // TODO add your handling code here:

        int index = shipmentCombo.getSelectedIndex();
        try {
            Shipment shipment = shipments.get(index);
            invoice.setShipment(shipment);
            shipcostText.setText(Utils.formatMoney(shipment.getShipmentCost()));
            invoice.setTotalPrice(invoice.getTotalProductPrice() + invoice.getShipment().getShipmentCost());
            total.setText(Utils.formatMoney(invoice.getTotalPrice()));
        } catch (Exception e) {
//            e.printStackTrace();
        }
//        invoice.setTotal(invoice.getTotalPrice() + shipment.getShipmentCost());

    }//GEN-LAST:event_shipmentComboActionPerformed

    private void paymentComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentComboActionPerformed
        // TODO add your handling code here:
        int index = paymentCombo.getSelectedIndex();
        try {
            Payment payment = payments.get(index);
            invoice.setPayment(payment);
        }
        catch (Exception e) {
            
        }
    }//GEN-LAST:event_paymentComboActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField addressField;
    private javax.swing.JButton confirmButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private java.awt.Panel panel1;
    private javax.swing.JComboBox<String> paymentCombo;
    private javax.swing.JTable productTable;
    private javax.swing.JTextArea shipcostText;
    private javax.swing.JComboBox<String> shipmentCombo;
    private javax.swing.JTextArea timeText;
    private javax.swing.JTextArea total;
    private javax.swing.JTextArea totalAmount;
    private javax.swing.JTextArea totalProductPrice;
    // End of variables declaration//GEN-END:variables
}
