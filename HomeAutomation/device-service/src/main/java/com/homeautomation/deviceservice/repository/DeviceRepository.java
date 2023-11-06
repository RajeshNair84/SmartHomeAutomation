/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.homeautomation.deviceservice.repository;

import com.homeautomation.deviceservice.model.Device;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 *
 * @author rajesh
 */
public interface DeviceRepository extends MongoRepository<Device, String>{
    @Query("{'name': ?0}")
    Optional<Device> findByName(String name);
}
