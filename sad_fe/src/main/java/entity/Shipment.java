/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

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
@AllArgsConstructor
@NoArgsConstructor
public class Shipment {
    private String shipmentId;

    private String shipmentName;

    private double shipmentCost;
}
