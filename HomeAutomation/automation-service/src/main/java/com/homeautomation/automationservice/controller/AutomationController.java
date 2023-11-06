/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.homeautomation.automationservice.controller;

import com.homeautomation.automationservice.dto.AutomationRequest;
import com.homeautomation.automationservice.dto.AutomationResponse;
import com.homeautomation.automationservice.service.AutomationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author rajesh
 */
@RestController
@RequestMapping("automationRules")
@RequiredArgsConstructor
public class AutomationController {
    
    private final AutomationService automationService;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AutomationResponse createAutomation(@RequestBody AutomationRequest automationRequest)
    {
        return automationService.createAutomation(automationRequest);
    }
    
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AutomationResponse> getAutomations(@RequestParam(name = "enabled", required = false) Boolean enabled)
    {
        return automationService.getAutomations(enabled);
    }

    @GetMapping("/{ruleName}")
    public AutomationResponse getAutomationByName(@PathVariable String ruleName )
    {
        return automationService.getAutomation(ruleName);
    }
    
    @PatchMapping
    public ResponseEntity updateAutomation(@RequestBody AutomationRequest automationRequest)
    {
        automationService.updateAutomation(automationRequest);
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("/{ruleName}")
    public ResponseEntity deleteAutomation(@PathVariable String ruleName )
    {
        automationService.deleteAutomation(ruleName);
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/trigger/{ruleName}")
    public ResponseEntity triggerAutomation(@PathVariable String ruleName)
    {
        automationService.triggerAutomation(ruleName);
        return ResponseEntity.ok().build();
    }
}
