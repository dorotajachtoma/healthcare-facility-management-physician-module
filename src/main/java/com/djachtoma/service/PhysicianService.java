package com.djachtoma.service;

import com.djachtoma.repository.PhysicianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PhysicianService {

    private final PhysicianRepository physicianRepository;

}
