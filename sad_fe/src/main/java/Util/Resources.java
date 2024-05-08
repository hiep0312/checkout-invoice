/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import entity.Customer;

/**
 *
 * @author hiepd
 */

public class Resources {
    private static Resources instance;

    public static Resources getInstance() {
        if (instance == null) {
            instance = new Resources();
        }
        return instance;
    }

    private Customer loginCustomer;
    
    public Customer getCurrentCustomer() {
        return loginCustomer;
    }
    
    public void SetCurrentCustomer(Customer customer) {
        this.loginCustomer = customer;
    }
}
