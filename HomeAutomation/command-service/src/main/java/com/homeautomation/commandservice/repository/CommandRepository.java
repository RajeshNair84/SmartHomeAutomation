/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.homeautomation.commandservice.repository;

import com.homeautomation.commandservice.model.Command;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author rajesh
 */
public interface CommandRepository extends JpaRepository<Command, String>{
    
}
