/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.homeautomation.automationservice.repository;

import com.homeautomation.automationservice.model.Automation;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author rajesh
 */
public interface AutomationRepository extends JpaRepository<Automation, String>{
    Optional<Automation> findByRuleName(String ruleName);
    List<Automation> findByEnabledTrue();

    List<Automation> findByEnabledFalse();
}
