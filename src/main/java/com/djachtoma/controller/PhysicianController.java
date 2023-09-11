package com.djachtoma.controller;

import com.djachtoma.model.dto.PhysicianDTO;
import com.djachtoma.service.PhysicianService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/physician")
public class PhysicianController {


    private final PhysicianService physicianService;

    @GetMapping
    public Flux<PhysicianDTO> getPhysicians() {
        log.info("%s is invoked by: %s", this.getClass().getName() + ".getPhysicians");
        return physicianService.getPhysicians();
    }

    @GetMapping("/{id}")
    public Mono<PhysicianDTO> getPhysician(@PathVariable String id) {
        log.info("%s is invoked by: %s", this.getClass().getName() + ".getPhysician");
        return physicianService.getPhysician(id);
    }

    @PostMapping
    public Mono<PhysicianDTO> createPhysician(@RequestBody PhysicianDTO physicianDTO) {
        log.info("%s is invoked by: %s", this.getClass().getName() + ".createPhysician");
        return physicianService.createPhysician(physicianDTO);
    }

    @PatchMapping("/{id}")
    public Mono<PhysicianDTO> updatePhysician(@PathVariable String id, @RequestBody PhysicianDTO physicianDTO) {
        log.info("%s is invoked by: %s", this.getClass().getName() + ".updatePhysician");
        return physicianService.updatePhysician(id, physicianDTO);
    }

    @DeleteMapping("/{id}")
    public void deletePhysician(@PathVariable String id) {
        log.info("%s is invoked by: %s", this.getClass().getName() + ".deletePhysician");
        physicianService.deletePhysician(id);
    }
}
