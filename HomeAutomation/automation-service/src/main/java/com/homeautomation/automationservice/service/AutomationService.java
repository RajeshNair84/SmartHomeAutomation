/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.homeautomation.automationservice.service;

import com.homeautomation.automationservice.dto.AutomationRequest;
import com.homeautomation.automationservice.dto.AutomationResponse;
import com.homeautomation.automationservice.model.Automation;
import com.homeautomation.automationservice.repository.AutomationRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

/**
 *
 * @author rajesh
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AutomationService {

    private final AutomationRepository automationRepository;
    private final WebClient.Builder webClientBuilder;
    
    public AutomationResponse createAutomation(AutomationRequest automationRequest) {
        Automation automation = Automation.builder()
                .ruleName(automationRequest.getRuleName())
                .deviceId(automationRequest.getDeviceId())
                .actionType(automationRequest.getActionType())
                .conditionType(automationRequest.getConditionType())
                .enabled(automationRequest.getEnabled())
                .build();
        Automation savedAutomation = automationRepository.save(automation);
        log.info("Automation {} created", automation.getId());
        return mapToAutomationResponse(savedAutomation);
    }

    public void triggerAutomation(String ruleName)
    {
        Automation savedAutomation = automationRepository.findByRuleName(ruleName)
                .orElseThrow(() -> new RuntimeException(
                String.format( "Cannot find Automation by RuleName: %s", ruleName)));    
        
        //Call Command Service
        String jsonString = "{\n"
                + "    \"issuedCommand\" : \"turn_on\",\n"
                + "    \"deviceId\" : \"" + savedAutomation.getDeviceId() + "\"\n"
                + "}";
        
        webClientBuilder.build().post()
                .uri("http://command-service/commands")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(jsonString))
                .retrieve()
                .bodyToMono(String.class)
                .block();        
    }

    public void deleteAutomation(String ruleName) {
        Automation savedAutomation = automationRepository.findByRuleName(ruleName)
                .orElseThrow(() -> new RuntimeException(
                String.format( "Cannot find Automation by RuleName: %s", ruleName)));
        automationRepository.delete(savedAutomation);
    }
    
    private AutomationResponse mapToAutomationResponse(Automation automation) {
        return AutomationResponse.builder()
                .id(automation.getId())
                .ruleName(automation.getRuleName())
                .deviceId(automation.getDeviceId())
                .conditionType(automation.getConditionType())
                .actionType(automation.getActionType())
                .enabled(automation.getEnabled())
                .build();
    }

    public void updateAutomation(AutomationRequest automationRequest) {
        Automation savedAutomation = automationRepository.findById( automationRequest.getId())
                .orElseThrow(() -> new RuntimeException(
                String.format( "Cannot find Automation by Id: %s", automationRequest.getId())));
        savedAutomation.setDeviceId(automationRequest.getId());
        savedAutomation.setRuleName(automationRequest.getRuleName());
        savedAutomation.setConditionType(automationRequest.getConditionType());
        savedAutomation.setActionType(automationRequest.getActionType());
        savedAutomation.setEnabled(automationRequest.getEnabled());
        automationRepository.save(savedAutomation);
    }

    public List<AutomationResponse> getAutomations(Boolean enabled) {
        List<Automation> automations = null;
        if(enabled == null)
        {
            automations = automationRepository.findAll();
        }
        else if (enabled)
        {
            automations = automationRepository.findByEnabledTrue();
        }
        else
        {
            automations = automationRepository.findByEnabledFalse();
        }
        return automations.stream().map(this::mapToAutomationResponse).collect(Collectors.toList());
    }

    public AutomationResponse getAutomation(String ruleName) {
        Automation savedAutomation = automationRepository.findByRuleName(ruleName)
                .orElseThrow(() -> new RuntimeException(
                        String.format( "Cannot find Automation by RuleName: %s", ruleName)));
        return mapToAutomationResponse(savedAutomation);
    }
}
