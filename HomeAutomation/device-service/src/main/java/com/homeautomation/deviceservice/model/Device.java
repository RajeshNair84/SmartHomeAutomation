/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.homeautomation.deviceservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author rajesh
 */
@Document(value = "device")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Device {
    @Id
    private String id;
    private String name;
    private String description;
    private Boolean switchedOn;
}
