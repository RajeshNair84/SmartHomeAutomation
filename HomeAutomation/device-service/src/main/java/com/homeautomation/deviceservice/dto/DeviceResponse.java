/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.homeautomation.deviceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author rajesh
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeviceResponse {
    private String id;
    private String name;
    private String description;
    private Boolean switchedOn;
}
