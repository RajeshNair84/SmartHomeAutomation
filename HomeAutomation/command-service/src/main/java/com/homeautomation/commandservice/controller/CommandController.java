/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.homeautomation.commandservice.controller;

import com.homeautomation.commandservice.dto.CommandRequest;
import com.homeautomation.commandservice.dto.CommandResponse;
import com.homeautomation.commandservice.service.CommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author rajesh
 */
@RestController
@RequestMapping("/commands")
@RequiredArgsConstructor
public class CommandController {
    
    private final CommandService commandService;
    
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public CommandResponse issueCommand(@RequestBody CommandRequest commandRequest)
    {
        return commandService.issueCommand(commandRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CommandResponse> getAllCommands()
    {
        return commandService.getAllCommands();
    }
}
