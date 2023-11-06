/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.homeautomation.commandservice.service;

import com.homeautomation.commandservice.dto.CommandRequest;
import com.homeautomation.commandservice.dto.CommandResponse;
import com.homeautomation.commandservice.model.Command;
import com.homeautomation.commandservice.repository.CommandRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
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
public class CommandService {
    
    private final CommandRepository commandRepository;
    private final WebClient.Builder webClientBuilder;
    
    public CommandResponse issueCommand(CommandRequest commandRequest)
    {
        Command command = new Command();
        command.setId(UUID.randomUUID().toString());
        command.setDeviceId(commandRequest.getDeviceId());
        command.setIssuedCommand(commandRequest.getIssuedCommand());
        Command savedCommand = commandRepository.save(command);
        
        //Trigger command to Device .
        String jsonString = "{\n"
                + "    \"issuedCommand\" : \"" + commandRequest.getIssuedCommand()+ "\",\n"
                + "    \"deviceId\" : \"" + commandRequest.getDeviceId() + "\"\n"
                + "}";
        
        webClientBuilder.build().put()
                .uri("http://device-service/devices/trigger")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(jsonString))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return mapToCommandResponse(savedCommand);
    }

    private CommandResponse mapToCommandResponse(Command command) {
        return CommandResponse.builder()
                .id(command.getId())
                .deviceId(command.getDeviceId())
                .issuedCommand(command.getIssuedCommand())
                .build();
    }

    public List<CommandResponse> getAllCommands() {
        List<Command> commands = commandRepository.findAll();
        return commands.stream().map(this::mapToCommandResponse).collect(Collectors.toList());
    }
}
