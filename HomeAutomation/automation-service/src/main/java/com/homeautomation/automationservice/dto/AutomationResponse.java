/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.homeautomation.automationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author rajesh
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AutomationResponse {
    private String id;
    private String deviceId;
    private String ruleName;
    private String conditionType;
    private String actionType;
    private Boolean enabled;
}
