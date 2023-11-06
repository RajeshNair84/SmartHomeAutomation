/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.homeautomation.deviceservice.service;

import com.homeautomation.deviceservice.model.Device;
import com.homeautomation.deviceservice.repository.DeviceRepository;
import com.homeautomation.deviceservice.dto.DeviceRequest;
import com.homeautomation.deviceservice.dto.DeviceResponse;
import com.homeautomation.deviceservice.dto.DeviceTriggerRequest;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 *
 * @author rajesh
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class DeviceService {
    
    private final DeviceRepository deviceRepository;
    
    public DeviceResponse createDevice(DeviceRequest deviceRequest) {
        Device device = Device.builder()
                .name(deviceRequest.getName())
                .description(deviceRequest.getDescription())
                .build();
        
        Device savedDevice = deviceRepository.save(device);
        log.info("Device {} is saved", device.getId());
        return mapToDeviceResponse(savedDevice);
    }

    public List<DeviceResponse> getAllDevices() {
        List<Device> devices = deviceRepository.findAll();
        return devices.stream().map(this::mapToDeviceResponse).collect(Collectors.toList());
    }
    
    private DeviceResponse mapToDeviceResponse(Device device) {
        return DeviceResponse.builder()
                .id(device.getId())
                .name(device.getName())
                .description(device.getDescription())
                .switchedOn(device.getSwitchedOn())
                .build();
    }
    
    public void updateDevice(DeviceRequest deviceRequest) {
        Device savedDevice = deviceRepository.findById(deviceRequest.getId())
                .orElseThrow(() -> new RuntimeException(
                String.format("Cannot find Device by ID %s", deviceRequest.getId())));
        savedDevice.setName(deviceRequest.getName());
        savedDevice.setDescription(deviceRequest.getDescription());
        deviceRepository.save(savedDevice);
    }
    
    public Device getDeviceByName(String name) {
        Device savedDevice  = deviceRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException(
                String.format("Cannot find Device by Name %s", name)));
        return savedDevice;
    }
    
    public void deleteDevice(String name) {
        Device savedDevice  = deviceRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException(
                String.format("Cannot find Device by Name %s", name)));
        deviceRepository.deleteById(savedDevice.getId());
    }

    public void triggerAction(DeviceTriggerRequest deviceTriggerRequest) {
        Device savedDevice = deviceRepository.findById(deviceTriggerRequest.getDeviceId())
                .orElseThrow(() -> new RuntimeException(
                String.format("Cannot find Device by ID %s", deviceTriggerRequest.getDeviceId())));
        if(deviceTriggerRequest.getIssuedCommand().equalsIgnoreCase("turn_on"))
        {
            savedDevice.setSwitchedOn(Boolean.TRUE);
        }
        else if (deviceTriggerRequest.getIssuedCommand().equalsIgnoreCase("turn_off"))
        {
            savedDevice.setSwitchedOn(Boolean.FALSE);
        }
        deviceRepository.save(savedDevice);
    }
}
