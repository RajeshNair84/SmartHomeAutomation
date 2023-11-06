/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.homeautomation.automationservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author rajesh
 */
@Entity
@Table(name="t_automations")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Automation {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    
    // A user-friendly name for the rule to identify it easily.
    private String ruleName;
    
    // The ID of the device to which the rule applies.
    private String deviceId;
    
    // The type of condition for the rule, simplified to a single string, e.g., "motion_detected" or "set_time".
    private String conditionType;
    
    //The type of action to perform, simplified to a single string, e.g., "turn_on" or "turn_off."
    private String actionType;
    
    // A boolean flag to enable or disable the rule. If disabled, the rule won't trigger even if its condition is met.
    private Boolean enabled;
}
