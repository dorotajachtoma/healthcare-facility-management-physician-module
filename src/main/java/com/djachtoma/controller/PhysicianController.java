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

import java.security.Principal;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/physician")
public class PhysicianController {


    private final PhysicianService physicianService;

    @GetMapping
    public Flux<PhysicianDTO> getPhysicians(Principal principal) {
        log.info("%s is invoked by: %s", this.getClass().getName() + ".getPhysicians", principal.getName());
        return physicianService.getPhysicians();
    }

    @GetMapping("/{id}")
    public Mono<PhysicianDTO> getPhysician(@PathVariable String id, Principal principal) {
        log.info("%s is invoked by: %s", this.getClass().getName() + ".getPhysician", principal.getName());
        return physicianService.getPhysician(id);
    }

    @PostMapping
    public Mono<PhysicianDTO> createPhysician(@RequestBody PhysicianDTO physicianDTO, Principal principal) {
        log.info("%s is invoked by: %s", this.getClass().getName() + ".createPhysician", principal.getName());
        return physicianService.createPhysician(physicianDTO);
    }

    @PatchMapping("/{id}")
    public Mono<PhysicianDTO> updatePhysician(@PathVariable String id, @RequestBody PhysicianDTO physicianDTO,
                                              Principal principal) {
        log.info("%s is invoked by: %s", this.getClass().getName() + ".updatePhysician", principal.getName());
        return physicianService.updatePhysician(id, physicianDTO);
    }

    @DeleteMapping("/{id}")
    public void deletePhysician(@PathVariable String id, Principal principal) {
        log.info("%s is invoked by: %s", this.getClass().getName() + ".deletePhysician", principal.getName());
        physicianService.deletePhysician(id);
    }
}
