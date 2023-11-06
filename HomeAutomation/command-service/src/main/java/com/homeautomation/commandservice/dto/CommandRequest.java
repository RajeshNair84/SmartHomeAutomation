/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.homeautomation.commandservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author rajesh
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommandRequest {
    private String id;
    private String issuedCommand;
    private String deviceId;
}
