/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 *
 * @author hiepd
 */

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Brand implements Serializable {
    private String brandId;

    private String brandName;

    private String brandEmail;
}
