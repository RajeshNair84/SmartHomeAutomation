/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.homeautomation.deviceservice.controller;

import com.homeautomation.deviceservice.service.DeviceService;
import com.homeautomation.deviceservice.dto.DeviceRequest;
import com.homeautomation.deviceservice.dto.DeviceResponse;
import com.homeautomation.deviceservice.dto.DeviceTriggerRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rajesh
 */
@RestController
@RequestMapping("/devices")
@RequiredArgsConstructor
public class DeviceController {
    
    private final DeviceService deviceService;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeviceResponse createDevice(@RequestBody DeviceRequest deviceRequest)
    {
        return deviceService.createDevice(deviceRequest);
    }
    
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DeviceResponse> getAllDevices()
    {
        return deviceService.getAllDevices();
    }
    
    @GetMapping("/{deviceName}")
    public ResponseEntity getDeviceByName(@PathVariable String deviceName)
    {
        deviceService.getDeviceByName(deviceName);
        return ResponseEntity.ok(deviceService.getDeviceByName(deviceName));
    }
    
    @PatchMapping
    public ResponseEntity updateDevice(@RequestBody DeviceRequest deviceRequest)
    {
        deviceService.updateDevice(deviceRequest);
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("/{deviceName}")
    public ResponseEntity deleteDevice(@PathVariable String deviceName )
    {        
        deviceService.deleteDevice(deviceName);
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/trigger")
    public ResponseEntity triggerAction(@RequestBody DeviceTriggerRequest deviceTriggerRequest )
    {
        deviceService.triggerAction(deviceTriggerRequest);
        return ResponseEntity.ok().build();
    }
}
